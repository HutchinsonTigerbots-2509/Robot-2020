/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.commands.Turret.AlignTurret;
import frc.robot.subsystems.Conveyor;

public class ShootAllAutonomous extends CommandBase {
  private Conveyor sConveyor;
  private Shooter sShooter;
  private Boolean ReadyToShoot;
  private double TargetVoltage;
  private double Voltage = 0.2;
  private int TargetRPM;
  private double CurrentVoltage;
  private double P;
  /**
   * Creates a new ShootAllAutonomous.
   */
  public ShootAllAutonomous(Shooter pShooter, Conveyor pConveyor, int pRPM, double pTargetVolts) {
    sShooter = pShooter;
    sConveyor = pConveyor;
    TargetRPM = pRPM;
    TargetVoltage = pTargetVolts;
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

    // int rpm = sShooter.GetRPM();

    // int error = TargetRPM - Math.abs(rpm);

    // double newVoltage = 0.0002 * error;


    // if(Math.abs(rpm) < Math.abs(TargetRPM)){
    //   if (newVoltage - CurrentVoltage > 0.05){
    //   newVoltage += 0.05;
    //   }if(newVoltage < Constants.kShooterMinVoltage){
    //     newVoltage = Constants.kShooterMinVoltage;
    //   }
    // }else{
    //  newVoltage = Math.abs(CurrentVoltage);
    // }

    if(Math.abs(sShooter.GetRPM()) > TargetRPM){
      ReadyToShoot = true;
      // if(sShooter.GetRPM() > TargetRPM){
      //   Voltage = Voltage - 0.05;
      // }
    } else {
      ReadyToShoot = false;
    }

    // if(ReadyToShoot && sShooter.GetRPM() < RPM - 300){
    //   Voltage = Voltage + 0.01;
    // }

    if(ReadyToShoot){
      sConveyor.CanSensorMove = false;
      sConveyor.FullConveyorForward(1, 1);
    }

    // if(Voltage < TargetVoltage){
    //   Voltage = Voltage + 0.01;
    // }

    // SmartDashboard.putNumber("Current Volts", CurrentVoltage);

    // if(rpm > TargetRPM && rpm < TargetRPM + 100){
    //   sConveyor.CanSensorMove = false;
    //   sConveyor.FullConveyorForward(1, 0.8);
    // }else{
    //   sShooter.ShooterForward(newVoltage, newVoltage);//.9
    // }

    // CurrentVoltage = newVoltage;
    sShooter.ShooterForward(Voltage, Voltage);
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
