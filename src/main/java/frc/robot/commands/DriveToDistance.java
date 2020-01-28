/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveToDistance extends ParallelRaceGroup {
  /**
   * Creates a new DriveToDistance.
   */

   // Moves the robot a distance in inches
  public DriveToDistance(Drivetrain pDrivetrain, double pDistance) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new DrivetrainLeftPosition((int) (pDistance * Constants.kDrivetrainTicksPerInch)),
    new DrivetrainRightPosition((int)(pDistance * Constants.kDrivetrainTicksPerInch)));
  }
}
