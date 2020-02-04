/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.VariableVault;
import frc.robot.subsystems.Colorwheel;

public class MicroChangeNegative extends CommandBase {
  static Colorwheel sColorWheel = RobotContainer.sColorWheel;
  static VariableVault vV = new VariableVault();
  /**
   * Creates a new MicroChangeNegative.
   */
  public MicroChangeNegative() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sColorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sColorWheel.setMotorSpeed(-vV.kColorWheelMotorMicroSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sColorWheel.setMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
