/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
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
  
  public static WPI_TalonSRX LeftFront = new WPI_TalonSRX(Constants.kLeftFrontID);
  public final WPI_VictorSPX LeftRear = new WPI_VictorSPX(Constants.kLeftRearID);
  public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  public final WPI_VictorSPX RightRear = new WPI_VictorSPX(Constants.kRightRearID);

  public final SpeedControllerGroup Right = new SpeedControllerGroup(LeftFront, LeftRear);
  public final SpeedControllerGroup Left = new SpeedControllerGroup(RightFront, RightRear);

  public final DifferentialDrive Drive = new DifferentialDrive(Right, Left);

  public static AHRS DrivetrainGyro = new AHRS(SPI.Port.kMXP);
  // DrivetrainGyro.setSubsystem("Drivetrain");

  boolean EncoderNotReset = true;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {

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
    double SpeedMulti = 1;
		double TurnSpeedMulti = .75;
    double Speed = 0.0;
    
		if(stick.getRawAxis(3) > 0) {
			Speed = stick.getRawAxis(3) * -SpeedMulti;
		} else if(stick.getRawAxis(2) > 0) {
			Speed = stick.getRawAxis(2)  * SpeedMulti;
		}
		
		if(Speed > 0) {
		  Drive.arcadeDrive(Speed, stick.getRawAxis(0) * TurnSpeedMulti);
		}
		else {
			Drive.arcadeDrive(Speed, stick.getRawAxis(0) * TurnSpeedMulti);
    }
  }
		// Drive.arcadeDrive(stick.getRawAxis(1)*SpeedMulti, stick.getRawAxis(2) * -TurnSpeedMulti);

  public void MoveWithTicks(double ticks){
    if(RightFront.getSelectedSensorPosition() > -ticks){
      SmartDashboard.putNumber("EncoderTicks", RightFront.getSelectedSensorPosition());
      Right.set(0.-6);
      Left.set(0.6);

    }else{
      Right.set(0);
      Left.set(0);
      DrivetrainGyro.getAngle();
    }
  }


  public void RadiusTurning(double Angle, double Radius, double Speed, String Direction){

    double ShortMove = Math.PI*Radius*2;
    double LongMove = Math.PI*(Radius+22)*2;

    double LongSpeed = (Speed*LongMove)/ShortMove;
    double ShortSpeed = Speed;

    double CircumferencePercent = Angle/360;

    double ShortAngleMove = ShortMove * CircumferencePercent; //inches
    double LongAngleMove = LongMove * CircumferencePercent; //inches

    double RightCurrentInches = RightFront.getSelectedSensorPosition() / -299.4011976; // inch per tick

    SmartDashboard.putNumber("Postion", RightCurrentInches);

    if(Direction == "Right"){
      SmartDashboard.putString("Going", "Right");
        if(RightCurrentInches < ShortAngleMove){
          SmartDashboard.putNumber("Target in Inches", ShortAngleMove);
          Right.set(-LongSpeed);
          Left.set(ShortSpeed);
        }else{
          Right.set(0);
          Left.set(0);
        }
    }else if(Direction == "Left"){
      SmartDashboard.putString("Going", "Left");
      if(RightCurrentInches < LongAngleMove){
        Left.set(ShortSpeed);
        Right.set(LongSpeed);
      }else{
        Left.set(0);
        Right.set(0);
      }
    }else{
      SmartDashboard.putString("Going", "ERROR");
      //User did something wrong
      Drive.arcadeDrive(0, 0);
    }


  }

  public void Move(){
    SmartDashboard.putNumber("Gyro",DrivetrainGyro.getAngle());
    //double TargetTicks = (Math.PI*.5*2)*299.4011976*77;
    int TargetAngle = 90;
    if(DrivetrainGyro.getAngle() < TargetAngle){
      //SmartDashboard.putNumber("TargetTicksREAL", TargetTicks);
      //SmartDashboard.putNumber("CurrentTicksREAL", -RightFront.getSelectedSensorPosition());
      Right.set(CalcLongSpeed(-.1));
      Left.set(.1);
    }else{
      Right.set(0);
      Left.set(0);
    }
    // if(-RightFront.getSelectedSensorPosition() < TargetTicks){
    //   SmartDashboard.putNumber("TargetTicksREAL", TargetTicks);
    //   SmartDashboard.putNumber("CurrentTicksREAL", -RightFront.getSelectedSensorPosition());
    //   Right.set(CalcLongSpeed(-.1));
    //   Left.set(.1);
    // }else{
    //   Right.set(0);
    //   Left.set(0);
    // }
  }

  public double CalcLongSpeed(double ShortSpeed){
    double r = 5;//.5
    return (ShortSpeed * (Math.PI *(r + 22)*2))/(Math.PI*r*2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    MarioDrive(RobotContainer.OpStick);
  }

  public void RadiusTurningFinally(int Angle, double Speed, double Radius, String Direction){
    SmartDashboard.putNumber("Gyro",DrivetrainGyro.getAngle());
    double LongSpeed = (Speed * (Math.PI *(Radius + 22)*2))/(Math.PI*Radius*2);
    if(Direction == "Right"){
      if(DrivetrainGyro.getAngle() < Angle){
        Right.set(-LongSpeed);
        Left.set(Speed);
      }else{
        Right.set(0);
        Left.set(0);
        return;
      }
    }else if(Direction == "Left"){
      if(DrivetrainGyro.getAngle() > -Angle){
        Left.set(LongSpeed);
        Right.set(-Speed);
      }else{
        Right.set(0);
        Left.set(0);
        return;
      }
    }else{
      Right.set(0);
      Left.set(0);
      return;
    }

  }

}
