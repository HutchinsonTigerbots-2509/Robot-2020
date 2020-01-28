/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.ControlMode;

// THIS COMMAND SHOULD ONLY BE RUN BY THE DriveToDistance COMMAND GROUP!!!
// DO NOT RUN IT ON ITS OWN!!!
public class DrivetrainRightPosition extends CommandBase {
  private static int Ticks;
  /**
   * Creates a new DrivetrainFrontRightPosition.
   */
  public DrivetrainRightPosition(int pTicks) {
    Ticks = pTicks;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Encoders are reset in the DrivetrainFrontLeftPosition command
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.sDrivetrain.RightFront.set(ControlMode.Position, Ticks);
    RobotContainer.sDrivetrain.RightRear.set(ControlMode.Follower, Constants.kRightFrontID);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Ticks > 0){
      if(RobotContainer.sDrivetrain.RightFront.getSelectedSensorPosition() >= Ticks){
        return true;
      } else {
        return false;
      }
    } else {
      if(RobotContainer.sDrivetrain.RightFront.getSelectedSensorPosition() <= Ticks){
        return true;
      } else {
        return false;
      }
    }
  }
}
