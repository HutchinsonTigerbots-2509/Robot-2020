/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class RadiusTurningGood extends CommandBase {
  public static WPI_TalonSRX LeftFront = new WPI_TalonSRX(Constants.kLeftFrontID);
  public final WPI_VictorSPX LeftRear = new WPI_VictorSPX(Constants.kLeftRearID);
  public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  public final WPI_VictorSPX RightRear = new WPI_VictorSPX(Constants.kRightRearID);

  public final SpeedControllerGroup Right = new SpeedControllerGroup(LeftFront, LeftRear);
  public final SpeedControllerGroup Left = new SpeedControllerGroup(RightFront, RightRear);

  public final DifferentialDrive Drive = new DifferentialDrive(Right, Left);

  public static AHRS DrivetrainGyro = new AHRS(SPI.Port.kMXP);


  double Speed;
  double Radius;
  String Direction;
  int Angle;


  /**
   * Creates a new RadiusTurningGood.
   */
  public RadiusTurningGood() {


    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // public double Speed = .1;
    // public double Radius;
    // public String Direction;
    // public int Angle = ;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Gyro",DrivetrainGyro.getAngle());
    double LongSpeed = (Speed * (Math.PI *(Radius + 22)*2))/(Math.PI*Radius*2);
    if(Direction == "Right"){
      if(DrivetrainGyro.getAngle() < Angle){
        Right.set(-LongSpeed);
        Left.set(Speed);
      }else{
        Right.set(0);
        Left.set(0);
      }
    }else if(Direction == "Left"){
      if(DrivetrainGyro.getAngle() > -Angle){
        Left.set(LongSpeed);
        Right.set(-Speed);
      }else{
        Right.set(0);
        Left.set(0);
        
      }
    }else{
      Right.set(0);
      Left.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
