/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ConveyorReverse extends CommandBase {
  private static Intake sIntake;

  /**
   * Creates a new ConveyorReverse.
   */
  public ConveyorReverse(Intake pIntake) {
    sIntake = pIntake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sIntake.ConveyorReverse();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sIntake.ConveyorReverse();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sIntake.StopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
