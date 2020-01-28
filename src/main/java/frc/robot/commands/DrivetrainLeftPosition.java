/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.VariableVault;
import frc.robot.RobotContainer;

// THIS COMMAND SHOULD ONLY BE RUN BY THE DriveToDistance COMMAND GROUP!!!
// DO NOT RUN IT ON ITS OWN!!!
public class DrivetrainLeftPosition extends CommandBase {
  private int Ticks;
  /**
   * Creates a new DrivetrainFrontLeftPosition.
   */
  public DrivetrainLeftPosition(int pTicks) {
    Ticks = pTicks;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.sDrivetrain.ResetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.sDrivetrain.LeftFront.set(ControlMode.Position, Ticks);
    RobotContainer.sDrivetrain.LeftRear.set(ControlMode.Follower, VariableVault.kLeftFrontID);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.sDrivetrain.StopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Ticks > 0){
      if(RobotContainer.sDrivetrain.LeftFront.getSelectedSensorPosition() >= Ticks){
        return true;
      } else {
        return false;
      }
    } else {
      if(RobotContainer.sDrivetrain.LeftFront.getSelectedSensorPosition() <= Ticks){
        return true;
      } else {
        return false;
      }
    }
  }
}
