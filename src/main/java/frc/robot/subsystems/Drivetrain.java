/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Drivetrain extends SubsystemBase {
  
  public final WPI_TalonSRX LeftFront = new WPI_TalonSRX(Constants.kLeftFrontID);
  public final WPI_VictorSPX LeftRear = new WPI_VictorSPX(Constants.kLeftRearID);
  public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  public final WPI_VictorSPX RightRear = new WPI_VictorSPX(Constants.kRightRearID);

  // public final WPI_TalonFX LeftFront = new WPI_TalonFX(Constants.kLeftFrontID);
  // public final WPI_TalonFX LeftRear = new WPI_TalonFX(Constants.kLeftRearID);
  // public final WPI_TalonFX RightFront = new WPI_TalonFX(Constants.kRightFrontID);
  // public final WPI_TalonFX RightRear = new WPI_TalonFX(Constants.kRightRearID);

  public final SpeedControllerGroup Right = new SpeedControllerGroup(LeftFront, LeftRear);
  public final SpeedControllerGroup Left = new SpeedControllerGroup(RightFront, RightRear);

  public final DifferentialDrive Drive = new DifferentialDrive(Right, Left);

  public static AHRS Gyro = new AHRS(SPI.Port.kMXP);

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    LeftFront.setNeutralMode(NeutralMode.Coast);
    LeftFront.setInverted(true);
    RightFront.setNeutralMode(NeutralMode.Coast);
    RightFront.setInverted(true);
    LeftRear.setNeutralMode(NeutralMode.Coast);
    LeftRear.setInverted(true);
    RightRear.setNeutralMode(NeutralMode.Coast);
    RightRear.setInverted(true);
  }

  public void SetSpeed(double pSpeed){
    Drive.tankDrive(pSpeed, pSpeed);
  }

  public void TurnLeft(double pSpeed) {
    Drive.tankDrive(-pSpeed, pSpeed);
  }

  public void TurnRight(double pSpeed) {
    Drive.tankDrive(pSpeed, -pSpeed);
  }

  public void StopMotors() {
    Drive.tankDrive(0, 0);
  }

  /**
   * MarioDrive. LET'S A-GOOOOOOO!
   * 
   * @param stick
   * @param stick_2
   */
  public void MarioDrive(Joystick stick) {
		double SpeedMulti = .75;
		double TurnSpeedMulti = 0.4;
		double Speed = 0.0;
		
		// if(stick.getRawAxis(3) > 0) {
		// 	Speed = stick.getRawAxis(3) * -SpeedMulti;
		// } else if(stick.getRawAxis(2) > 0) {
		// 	Speed = stick.getRawAxis(2)  * SpeedMulti;
    // }

    if(stick.getRawAxis(1) != 0){
      Speed = stick.getRawAxis(1) * SpeedMulti;
    }

		if(Speed > 0.05) {
		  Drive.arcadeDrive(Speed, stick.getRawAxis(4) * -TurnSpeedMulti);
		}
		else if (Speed < -0.05) {
      Drive.arcadeDrive(Speed, stick.getRawAxis(4) * -TurnSpeedMulti);
    }
    else {
      Drive.arcadeDrive(0, stick.getRawAxis(4) * -TurnSpeedMulti);
    }
  }

  private double PreviousValue = 0;
  private double CurrentValue = 0;
  private double PreviousTurnValue = 0;
  private double CurrentTurnValue = 0;
  private double Change = 0;
  private double TurnChange = 0;

  public void MarioDriveRamp(Joystick stick){
    double SpeedMulti = 0.8;
    double TurnSpeedMulti = 0.8;
    double ForwardGain = 0.028; //0.007
    double ReverseGain = -0.7;
    double ReverseTurnGain = -0.5;
    double Target = (stick.getRawAxis(1) * SpeedMulti);
    double TurnTarget = -(stick.getRawAxis(4) * TurnSpeedMulti);

    // Sets the target to zero if the target is too low
    if(Math.abs(Target) < Constants.kDrivetrainMinVoltage) {
      Target = 0.0;
    }
    if(Math.abs(TurnTarget) < Constants.kDrivetrainMinVoltage) {
      TurnTarget = 0.0;
    }

    // Determines how much to add/subtract from the current voltage
    if (Target == 0.0) {
      Change = (ReverseGain * PreviousValue);
    } else {
      Change = -(ForwardGain * (Target - PreviousValue));
    }
    if (TurnTarget == 0.0) {
      TurnChange = (ReverseTurnGain * PreviousTurnValue);
    } else {
      TurnChange = -(ForwardGain * (TurnTarget - PreviousTurnValue));
    }

    // Updates the values to give to the motors
    CurrentValue = CurrentValue + Change;
    CurrentTurnValue = CurrentTurnValue + TurnChange;

    // Sets the maximum output voltage to 1
    if (CurrentValue > 1){
      CurrentValue = 1;
    } else if (CurrentValue < -1){
      CurrentValue = -1;
    }
    if (CurrentTurnValue > 1){
      CurrentTurnValue = 1;
    } else if (CurrentTurnValue < -1){
      CurrentTurnValue = -1;
    }

    // Drives the motors
    if(Math.abs(CurrentValue) > Constants.kDrivetrainMinVoltage){
      Drive.arcadeDrive(CurrentValue, CurrentTurnValue);
    } else if (Math.abs(CurrentTurnValue) > Constants.kDrivetrainMinVoltage){
      Drive.arcadeDrive(0, CurrentTurnValue);
    } else {
      Drive.arcadeDrive(0, 0);
    }

    PreviousValue = CurrentValue;
    PreviousTurnValue = CurrentTurnValue;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    MarioDriveRamp(RobotContainer.OpStick);
    // if(RobotContainer.OpStick.getRawAxis(3) >= 0.2){
    //   SetSpeed(0.5);
    // }
    // else if(RobotContainer.OpStick.getRawAxis(2) >= 0.2){
    //   SetSpeed(-0.5);
    // } else {
    //   StopMotors();
    // }
    // SmartDashboard.putNumber("Gyro Roll", Gyro.getRoll());
    // SmartDashboard.putNumber("Gyro Yaw", Gyro.getYaw());
    // SmartDashboard.putNumber("Gyro Pitch", Gyro.getPitch());
  }
}
