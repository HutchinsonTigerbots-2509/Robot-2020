/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.Turret.AlignTurret;
import frc.robot.subsystems.Conveyor;

public class ShootAllAutonomous extends CommandBase {
  private Conveyor sConveyor;
  private Shooter sShooter;
  private Boolean ReadyToShoot;
  private double TargetVoltage;
  private double Voltage = 0.2;
  private int RPM;
  /**
   * Creates a new ShootAllAutonomous.
   */
  public ShootAllAutonomous(Shooter pShooter, Conveyor pConveyor, int pRPM, double pTargetVolts) {
    sShooter = pShooter;
    sConveyor = pConveyor;
    RPM = pRPM;
    // TargetVoltage = pTargetVolts;
    Voltage = pTargetVolts;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
    addRequirements(sConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sShooter.ShooterForward(Voltage, Voltage);
    ReadyToShoot = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(sShooter.GetRPM()) > RPM){
      ReadyToShoot = true;
      // if(sShooter.GetRPM() > RPM){
      //   Voltage = Voltage - 0.02;
      // }
    }

    // if(ReadyToShoot && sShooter.GetRPM() < RPM - 300){
    //   Voltage = Voltage + 0.01;
    // }

    if(ReadyToShoot){
      sConveyor.CanSensorMove = false;
      sConveyor.FullConveyorForward(1, 0.8);
    }

    // if(Voltage < TargetVoltage){
    //   Voltage = Voltage + 0.01;
    // }

    sShooter.ShooterForward(Voltage, Voltage);//.9
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sConveyor.CanSensorMove = true;
    sShooter.StopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
