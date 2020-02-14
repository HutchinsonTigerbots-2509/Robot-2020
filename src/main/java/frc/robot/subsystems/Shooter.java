/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  public static WPI_TalonSRX ShooterMotorMaster = new WPI_TalonSRX(Constants.kShooterMotorMasterID);
  private static WPI_TalonSRX ShooterMotorSlave = new WPI_TalonSRX(Constants.kShooterMotorSlaveID);
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    ShooterMotorMaster.setNeutralMode(NeutralMode.Coast);
    ShooterMotorSlave.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Velocity? (1)", ShooterMotorMaster.getSelectedSensorVelocity());
    // SmartDashboard.putNumber("Velocity? (2)", ShooterMotor2.getActiveTrajectoryVelocity());
    SmartDashboard.putNumber("RPM? (1)", (ShooterMotorMaster.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
    SmartDashboard.putNumber("Encoder", ShooterMotorMaster.getSelectedSensorPosition());
    // SmartDashboard.putNumber("RPM? (2)", (ShooterMotor2.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) * 600);
    // SmartDashboard.putNumber("RPM? (Average)", ((ShooterMotor1.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) + (ShooterMotor2.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation)) * 300);
    // This method will be called once per scheduler run
  }

  public int GetRPM(){
    return (ShooterMotorMaster.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation;
  }

  public void ShooterForward(double pSpeed){
    ShooterMotorMaster.set(-pSpeed);
    ShooterMotorSlave.set(pSpeed);
  }

  public void ShooterReverse(){
    ShooterMotorMaster.set(1);
    ShooterMotorSlave.set(-1);
  }

  public void StopShooter(){
    ShooterMotorMaster.set(0);
    ShooterMotorSlave.set(0);
  }

}
