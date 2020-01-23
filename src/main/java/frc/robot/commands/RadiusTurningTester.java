/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RadiusTurningTester extends SequentialCommandGroup {
  /**
   * Creates a new RadiusTurningTester.
   */
  public RadiusTurningTester(Drivetrain pDT) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    // super(new RadiusTurnCommand(pDT, 45, .1, 3.0, "Right"), 
    // new RadiusTurnCommand(pDT, 45, .1, 3.0, "Left"));
    addCommands(new RadiusTurnCommand(pDT, 45, 0.1, 3.0, "Right"), 
    new RadiusTurningCommandLeft(pDT, 45, 0.1, 3.0, "Left"));
  }
}
