/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveDistanceDJ extends CommandBase {
  double Distance;
  double Speed;
  TalonFXSensorCollection TF_LF_SC = RobotContainer.sDrivetrain.LFSC;
  /**
   * Creates a new MoveDistanceDJ.
   */
  public MoveDistanceDJ(int pInchDistance, double pSpeed) {
    Distance = pInchDistance;
    Speed = pSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TF_LF_SC.setIntegratedSensorPosition(0, 0);
    // RobotContainer.sDrivetrain.Drive.tankDrive(Speed, Speed);
    // Timer.delay(5);
    // RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Right Front Encoder", RobotContainer.sDrivetrain.getAvgValue());
    if(Distance > 0){
      if (RobotContainer.sDrivetrain.getAvgValue() < Distance * (256*Math.PI * 12.76*6) / 10){
       RobotContainer.sDrivetrain.Drive.tankDrive(Speed, Speed);
      }else{
        RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
      }
    }else if(Distance < 0){
      if (RobotContainer.sDrivetrain.getAvgValue() > Distance * (256*Math.PI * 12.76*6) / 10){
        RobotContainer.sDrivetrain.Drive.tankDrive(-Speed, -Speed);
      }else{
        RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
      }
    }else{
      RobotContainer.sDrivetrain.Drive.tankDrive(0, 0);
      end(false); 
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
