/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

public class ShootAllProp extends CommandBase {
  private Shooter sShooter;
  private Conveyor sConveyor;
  private int TargetRPM;
  private double CurrentVoltage;
  private double NewVoltage = 0;
  private double previousError;
  private double error;
  private double totalError;
  private double errorDiff;
  private double VoltageIncrease;
  private int Counter;
  private boolean RunConveyor;
  private double p = 0.00001; //0.00001
  private double i = 0.0000;
  private double d = 0;
  /**
   * Creates a new ShootAllProp.
   */
  public ShootAllProp(Shooter pShooter, Conveyor pConveyor, int pRPM) {
    sShooter = pShooter;
    sConveyor = pConveyor;
    TargetRPM = pRPM;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
    addRequirements(sConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RunConveyor = false;
    Counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    previousError = error;
    error = TargetRPM - sShooter.GetRPM();
    totalError += error;
    errorDiff = error - previousError;
    SmartDashboard.putNumber("error", error);
    
    VoltageIncrease = (error * p) + (totalError * i) + (errorDiff * d);

    NewVoltage += VoltageIncrease;

    if(NewVoltage < Constants.kShooterMinVoltage){
      NewVoltage = Constants.kShooterMinVoltage;
    }
    if(NewVoltage > 1){
      NewVoltage = 1;
    }

    sShooter.ShooterForward(NewVoltage, NewVoltage);

    if(sShooter.GetRPM() > TargetRPM && sShooter.GetRPM() < TargetRPM + 100){
      Counter += 1;
      if(Counter > 50){
        sConveyor.FullConveyorForward(1, 1);
        RunConveyor = true;
      }
    }else{
      Counter = 1;
    }

    if(RunConveyor){
      sConveyor.FullConveyorForward(1, 0.8);
    }

    SmartDashboard.putNumber("NewVoltage", NewVoltage);

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sShooter.StopShooter();
    sConveyor.StopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
