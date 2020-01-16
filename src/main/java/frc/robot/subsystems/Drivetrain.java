/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Drivetrain extends SubsystemBase {
  
  public final WPI_TalonSRX LeftFront = new WPI_TalonSRX(Constants.kLeftFrontID);
  public final WPI_VictorSPX LeftRear = new WPI_VictorSPX(Constants.kLeftRearID);
  public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  public final WPI_VictorSPX RightRear = new WPI_VictorSPX(Constants.kRightRearID);

  public final SpeedControllerGroup Right = new SpeedControllerGroup(LeftFront, LeftRear);
  public final SpeedControllerGroup Left = new SpeedControllerGroup(RightFront, RightRear);

  public final DifferentialDrive Drive = new DifferentialDrive(Right, Left);

  public static AHRS Gyro = new AHRS(SPI.Port.kMXP);

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
		double TurnSpeedMulti = 1;
		double Speed = 0.0;
		
		if(stick.getRawAxis(3) > 0) {
			Speed = stick.getRawAxis(3) * -SpeedMulti;
		} else if(stick.getRawAxis(2) > 0) {
			Speed = stick.getRawAxis(2)  * SpeedMulti;
		}
		
		if(Speed > 0) {
		  Drive.arcadeDrive(Speed, stick.getRawAxis(0) * -TurnSpeedMulti);
		}
		else {
			Drive.arcadeDrive(Speed, stick.getRawAxis(0) * -TurnSpeedMulti);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    MarioDrive(RobotContainer.OpStick);
  }
}
