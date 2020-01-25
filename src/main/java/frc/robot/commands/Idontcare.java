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
public class Idontcare extends SequentialCommandGroup {
  public Drivetrain DT;
  /**
   * Creates a new Idontcare.
   */
  public Idontcare(Drivetrain pDT) {
    super();
    DT = pDT;
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addCommands(new RadiusTurnCommand(pDT, 25, .1, 3.0, "Right"));
    addCommands(new RadiusTurnCommand(DT, 25, .1, 3.0, "Left"));
  }
}
