/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class MoveDistanceDJ extends CommandBase {
  double Distance;
  double Speed;
  boolean Finished;
  TalonFXSensorCollection TF_LF_SC = RobotContainer.sDrivetrain.LFSC;
  /**
   * Creates a new MoveDistanceDJ.
   */
  public MoveDistanceDJ(int pInchDistance, double pSpeed) {
    Distance = pInchDistance;
    Speed = pSpeed;
    Finished = false;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TF_LF_SC.setIntegratedSensorPosition(0, 0);
    SmartDashboard.putNumber("DONE", 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Right Front Encoder", RobotContainer.sDrivetrain.getAvgValue());
    if(Distance > 0){
      if (RobotContainer.sDrivetrain.getAvgValue() < Distance * Constants.kDrivetrainTicksPerInch){//Distance * Constants.kDrivetrainTicksPerInch){ //(256*Math.PI * 12.76*6) / 10)
       RobotContainer.sDrivetrain.Drive.tankDrive(Speed, Speed);
       Finished = false;
      }else{
        RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
        Finished = true;

      }
    }else if(Distance < 0){
      if (RobotContainer.sDrivetrain.getAvgValue() > Distance * Constants.kDrivetrainTicksPerInch){//Distance * Constants.kDrivetrainTicksPerInch){
        RobotContainer.sDrivetrain.Drive.tankDrive(-Speed, -Speed);
        Finished = false;
      }else{
        RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
        Finished = true;
      }
    }else{
      RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
      Finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putNumber("DONE", RobotContainer.sDrivetrain.getAvgValue());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Finished){
      return true;
    }else{
      return false;
    }
  }
}