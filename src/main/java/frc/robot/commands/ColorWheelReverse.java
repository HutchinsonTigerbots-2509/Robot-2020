/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class ColorWheelReverse extends CommandBase {
  private static ColorWheel sColorWheel;
  /**
   * Creates a new ColorWheelReverse.
   */
  public ColorWheelReverse(ColorWheel pColorWheel) {
    sColorWheel = pColorWheel;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sColorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sColorWheel.ColorWheelReverse();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sColorWheel.ColorWheelReverse();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sColorWheel.StopColorMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
