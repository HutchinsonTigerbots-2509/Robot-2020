/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  // public static WPI_TalonSRX ShooterMotorMaster = new WPI_TalonSRX(Constants.kShooterMotorMasterID);
  // private static WPI_TalonSRX ShooterMotorSlave = new WPI_TalonSRX(Constants.kShooterMotorSlaveID);
  private static WPI_TalonSRX BottomShooterMotor = new WPI_TalonSRX(Constants.kBottomShooterMotorID);
  private static WPI_TalonSRX TopShooterMotor = new WPI_TalonSRX(Constants.kTopShooterMotorID);
  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    BottomShooterMotor.setNeutralMode(NeutralMode.Coast);
    TopShooterMotor.setNeutralMode(NeutralMode.Coast);

    BottomShooterMotor.config_kP(0, Constants.kShooterPGain);
    BottomShooterMotor.config_kI(0, Constants.kShooterIGain);
    BottomShooterMotor.config_kD(0, Constants.kShooterDGain);

    TopShooterMotor.config_kP(0, Constants.kShooterPGain);
    TopShooterMotor.config_kI(0, Constants.kShooterIGain);
    TopShooterMotor.config_kD(0, Constants.kShooterDGain);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Velocity? (1)", ShooterMotorMaster.getSelectedSensorVelocity());
    // SmartDashboard.putNumber("Velocity? (2)", ShooterMotor2.getActiveTrajectoryVelocity());
    SmartDashboard.putNumber("RPM Bottom", (BottomShooterMotor.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
    SmartDashboard.putNumber("RPM", (BottomShooterMotor.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
    // SmartDashboard.putNumber("RPM Top", (TopShooterMotor.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
    SmartDashboard.putNumber("Temp Bottom", (BottomShooterMotor.getTemperature() * (1.8)) + 32);
    SmartDashboard.putNumber("Temp Top", (TopShooterMotor.getTemperature() * (1.8)) + 32);
    // SmartDashboard.putNumber("Encoder", BottomShooterMotor.getSelectedSensorPosition());
    // SmartDashboard.putNumber("Encoder", ShooterMotorMaster.getSelectedSensorPosition());
    // SmartDashboard.putNumber("RPM? (2)", (ShooterMotor2.getActiveTrajectoryVelocity() / Constants.kShooterTicksPerRotation) * 600);
    // This method will be called once per scheduler run
  }

  public int GetRPM(){
    return Math.abs((BottomShooterMotor.getSelectedSensorVelocity() * 600) / Constants.kShooterTicksPerRotation);
  }

  public void ShooterForward(double pBottomSpeed, double pTopSpeed){
    BottomShooterMotor.set(ControlMode.PercentOutput, -pBottomSpeed);
    TopShooterMotor.set(ControlMode.PercentOutput, pTopSpeed);
  }

  public void ShooterReverse(){
    BottomShooterMotor.set(ControlMode.PercentOutput, 1);
    TopShooterMotor.set(ControlMode.PercentOutput, -1);
  }

  public void StopShooter(){
    BottomShooterMotor.set(0);
    TopShooterMotor.set(0);
  }

  // kinda works
  public void SetShooterVelocity(int RPM){
    BottomShooterMotor.set(ControlMode.Velocity, -(RPM * Constants.kShooterTicksPerRotation) / 600);
    TopShooterMotor.set(ControlMode.Follower, Constants.kBottomShooterMotorID);
  }

}
