/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class OPDrive extends CommandBase {
  private final Drivetrain DT;
  private final Intake IN;
  private final Joystick DriveStick;
  /**
   * You pass the DT in RobotContainer
   */
  public OPDrive(Drivetrain p_DT, Intake p_IN, Joystick p_DriveStick) {
    DT = p_DT;
    DriveStick = p_DriveStick;
    IN = p_IN;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DT);
    addRequirements(IN);
  }

  // Called when the command is initially zscheduled.
  @Override
  public void initialize() {
    DT.MarioDrive(DriveStick);
    IN.IntakeControl(DriveStick);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DT.MarioDrive(DriveStick);
    IN.IntakeControl(DriveStick);

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
