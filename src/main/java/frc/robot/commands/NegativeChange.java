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
import frc.robot.subsystems.Colorwheel;

public class NegativeChange extends CommandBase {
  static Colorwheel sColorwheel = RobotContainer.sColorwheel;
  static Constants constants = new Constants();
  /**
   * Creates a new MicroChangeNegative.
   */
  public NegativeChange() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sColorwheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (sColorwheel.IsMicro()){
      sColorwheel.runMotors(-constants.kColorWheelMotorMicroSpeed);
    } else {
        sColorwheel.runMotors(-constants.kColorWheelMotorMacroSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sColorwheel.runMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
