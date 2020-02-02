/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private static WPI_TalonSRX ShooterMotor1 = new WPI_TalonSRX(Constants.kShooterMotor1ID);
  private static WPI_TalonSRX ShooterMotor2 = new WPI_TalonSRX(Constants.kShooterMotor2ID);
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    ShooterMotor2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Velocity? (1)", ShooterMotor1.getActiveTrajectoryVelocity());
    // SmartDashboard.putNumber("Velocity? (2)", ShooterMotor2.getActiveTrajectoryVelocity());
    SmartDashboard.putNumber("RPM? (1)", (ShooterMotor1.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) * 600);
    SmartDashboard.putNumber("Encoder", ShooterMotor2.getSelectedSensorPosition());
    // SmartDashboard.putNumber("RPM? (2)", (ShooterMotor2.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) * 600);
    // SmartDashboard.putNumber("RPM? (Average)", ((ShooterMotor1.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) + (ShooterMotor2.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation)) * 300);
    // This method will be called once per scheduler run
  }

  public void ShooterForward(double pSpeed){
    ShooterMotor1.set(pSpeed);
    ShooterMotor2.set(pSpeed);
  }

  public void ShooterReverse(){
    ShooterMotor1.set(-1);
    ShooterMotor2.set(-1);
  }

  public void StopShooter(){
    ShooterMotor1.set(0);
    ShooterMotor2.set(0);
  }

}
