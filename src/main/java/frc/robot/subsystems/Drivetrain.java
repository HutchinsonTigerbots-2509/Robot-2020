/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Drivetrain extends SubsystemBase {
  
  // public final WPI_TalonSRX LeftFront = new WPI_TalonSRX(Constants.kLeftFrontID);
  // public final WPI_VictorSPX LeftRear = new WPI_VictorSPX(Constants.kLeftRearID);
  // public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  // public final WPI_VictorSPX RightRear = new WPI_VictorSPX(Constants.kRightRearID);

  public final WPI_TalonFX LeftFront = new WPI_TalonFX(Constants.kLeftFrontID);
  public final WPI_TalonFX LeftRear = new WPI_TalonFX(Constants.kLeftRearID);
  public final WPI_TalonFX RightFront = new WPI_TalonFX(Constants.kRightFrontID);
  public final WPI_TalonFX RightRear = new WPI_TalonFX(Constants.kRightRearID);

  public final TalonFXSensorCollection LFSC = LeftFront.getSensorCollection();
  public final TalonFXSensorCollection LRSC = LeftRear.getSensorCollection();
  public final TalonFXSensorCollection RFSC = RightFront.getSensorCollection();
  public final TalonFXSensorCollection RRSC = RightRear.getSensorCollection();


  public final SpeedControllerGroup Right = new SpeedControllerGroup(LeftFront, LeftRear);
  public final SpeedControllerGroup Left = new SpeedControllerGroup(RightFront, RightRear);

  public final DifferentialDrive Drive = new DifferentialDrive(Right, Left);

  public static AHRS DrivetrainGyro = new AHRS(SPI.Port.kMXP);
  // DrivetrainGyro.setSubsystem("Drivetrain");

  boolean EncoderNotReset = true;

  public double getAvgValue() {
    double total = -RFSC.getIntegratedSensorPosition();
    return total;
  }

  /**
   * Creates a new Drivetrain.
   */
  
  public Drivetrain() {
    // Sets the PID values for the motors
    LeftFront.config_kP(0,Constants.kDrivetrainPGain);
    LeftFront.config_kI(0, Constants.kDrivetrainIGain);
    LeftFront.config_kD(0, Constants.kDrivetrainDGain);
    LeftFront.setNeutralMode(NeutralMode.Coast);

    LeftRear.config_kP(0,Constants.kDrivetrainPGain);
    LeftRear.config_kI(0, Constants.kDrivetrainIGain);
    LeftRear.config_kD(0, Constants.kDrivetrainDGain);
    LeftRear.setNeutralMode(NeutralMode.Coast);

    RightFront.config_kP(0,Constants.kDrivetrainPGain);
    RightFront.config_kI(0, Constants.kDrivetrainIGain);
    RightFront.config_kD(0, Constants.kDrivetrainDGain);
    RightFront.setNeutralMode(NeutralMode.Coast);

    RightRear.config_kP(0,Constants.kDrivetrainPGain);
    RightRear.config_kI(0, Constants.kDrivetrainIGain);
    RightRear.config_kD(0, Constants.kDrivetrainDGain);
    RightRear.setNeutralMode(NeutralMode.Coast);
  
    ResetEncoders();
  }

  public void MoveDrivetrain(double pSpeed){
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
		double SpeedMulti = .5;
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
  private double ChangeMax = 0.008;
  private double TurnChangeMax = 0.008;
  // private double Target = 0;

  public void MarioDriveRamp(Joystick stick){
    double SpeedMulti = 0.9;
    double TurnSpeedMulti = 0.9;
    double ForwardGain = 0.048; //0.038
    double ReverseGain = 0.04; // 0.01
    double ReverseTurnGain = 0.1;
    double Target = (stick.getRawAxis(1) * SpeedMulti);
    double TurnTarget = -(stick.getRawAxis(4) * TurnSpeedMulti);

    // if(stick.getRawAxis(3) > 0.2){
    //   Target = stick.getRawAxis(3);
    // } else if(stick.getRawAxis(2) > 0.2){
    //   Target = -stick.getRawAxis(2);
    // } else {
    //   Target = 0;
    // }

    // Sets the target to zero if the target is too low
    if(Math.abs(Target) < Constants.kDrivetrainMinVoltage) {
      Target = 0.0;
    }
    if(Math.abs(TurnTarget) < Constants.kDrivetrainMinVoltage) {
      TurnTarget = 0.0;
    }

    // Change = -(ForwardGain * (Target -PreviousValue));
    // TurnChange = -(ForwardGain * (TurnTarget - PreviousTurnValue));


    // Determines how much to add/subtract from the current voltage
    if (Target == 0.0) {
      Change = -(ReverseGain * (Target - PreviousValue));
    } else {
      Change = -(ForwardGain * (Target - PreviousValue));
    }
    if((Target < 0 && PreviousValue > 0) || (Target > 0 && PreviousValue < 0)){
      if(Change > ChangeMax){
        Change = ChangeMax;
      } else if (Change < -ChangeMax){
        Change = -ChangeMax;
      }
    }

    if (TurnTarget == 0.0) {
      TurnChange = -(ReverseTurnGain * (TurnTarget - PreviousTurnValue));
    } else {
      TurnChange = -(ForwardGain * (TurnTarget - PreviousTurnValue));
    }
    if((TurnTarget < 0 && PreviousTurnValue > 0) || (TurnTarget > 0 && PreviousTurnValue < 0)){
      if(TurnChange > TurnChangeMax){
        TurnChange = TurnChangeMax;
      } else if (TurnChange < -TurnChangeMax){
        TurnChange = -TurnChangeMax;
      }
    }

    // Updates the values to give to the motors
    
    CurrentValue = CurrentValue - Change;
    CurrentTurnValue = CurrentTurnValue - TurnChange;

    // Sets the maximum output voltage to 1
    // if(Target > 0 && CurrentValue < -Target){
    //   CurrentValue = -Target;
    // } else if (Target < 0 && CurrentValue > -Target){
    //   CurrentValue = -Target;
    // }
    // if(TurnTarget > 0 && CurrentTurnValue < -TurnTarget){
    //   CurrentTurnValue = -TurnTarget;
    // } else if (TurnTarget < 0 && CurrentTurnValue > -TurnTarget){
    //   CurrentTurnValue = -TurnTarget;
    // }
    // if (CurrentValue > 1){
    //   CurrentValue = 1;
    // } else if (CurrentValue < -1){
    //   CurrentValue = -1;
    // }
    // if (CurrentTurnValue > 1){
    //   CurrentTurnValue = 1;
    // } else if (CurrentTurnValue < -1){
    //   CurrentTurnValue = -1;
    // }

    // Drives the motors
    // if(Math.abs(CurrentValue) > Constants.kDrivetrainMinVoltage){
    //   Drive.arcadeDrive(-CurrentValue, -CurrentTurnValue);
    // } else if (Math.abs(CurrentTurnValue) > Constants.kDrivetrainMinVoltage){
    //   Drive.arcadeDrive(0, -CurrentTurnValue);
    // } else {
    //   Drive.arcadeDrive(0, 0);
    // }

    //    <<<<<READY TO TEST>>>>>

    if(stick.getRawAxis(1) > 0.1){
      if(Math.abs(CurrentValue) > Constants.kDrivetrainMinVoltage){
        Drive.arcadeDrive(-CurrentValue, -CurrentTurnValue);
      }else{
        Drive.arcadeDrive(-Constants.kDrivetrainMinVoltage, -CurrentTurnValue);
      }
    }else if(stick.getRawAxis(1)  < -0.1){
      if(Math.abs(CurrentValue) > Constants.kDrivetrainMinVoltage){
        Drive.arcadeDrive(-CurrentValue, -CurrentTurnValue);
      }else{
        Drive.arcadeDrive(Constants.kDrivetrainMinVoltage, -CurrentTurnValue);
      }
    }else if (stick.getRawAxis(4) > 0.1){
      if(Math.abs(CurrentTurnValue) > Constants.kDrivetrainMinVoltage){
        Drive.arcadeDrive(0, -CurrentTurnValue);
      }else{
        Drive.arcadeDrive(0, Constants.kDrivetrainMinVoltage);
      }
    }else if(stick.getRawAxis(4) < -0.1){
      if(Math.abs(CurrentTurnValue) > Constants.kDrivetrainMinVoltage){
        Drive.arcadeDrive(0, -CurrentTurnValue);
      }else{
        Drive.arcadeDrive(0, -Constants.kDrivetrainMinVoltage);
      }
    }else{
      Drive.arcadeDrive(0, 0);
    }

    // SmartDashboard.putNumber("Current Value", CurrentValue);
    // SmartDashboard.putNumber("Target", Target);
    // SmartDashboard.putNumber("Stick Value", stick.getRawAxis(1));
    SmartDashboard.putNumber("Change", Change);
    // SmartDashboard.putNumber("Prev Value", PreviousValue);
    
    PreviousValue = CurrentValue;
    PreviousTurnValue = CurrentTurnValue;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    MarioDriveRamp(RobotContainer.OpStick);

    // SmartDashboard.putNumber("Left Front", LFSC.getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Left Rear", LRSC.getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Right Front", RFSC.getIntegratedSensorPosition());
    // SmartDashboard.putNumber("Right Rear", RRSC.getIntegratedSensorPosition());

    // SmartDashboard.putNumber("Gyro Roll", Gyro.getRoll());
    // SmartDashboard.putNumber("Gyro Yaw", Gyro.getYaw());
    // SmartDashboard.putNumber("Gyro Pitch", Gyro.getPitch());
  }

  public void ResetEncoders(){
    LeftFront.setSelectedSensorPosition(0);
    LeftRear.setSelectedSensorPosition(0);
    RightFront.setSelectedSensorPosition(0);
    RightRear.setSelectedSensorPosition(0);

    RFSC.setIntegratedSensorPosition(0, 0);
    RRSC.setIntegratedSensorPosition(0, 0);
    LFSC.setIntegratedSensorPosition(0, 0);
    LRSC.setIntegratedSensorPosition(0, 0);
  }

}
