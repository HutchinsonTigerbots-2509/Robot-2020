/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Conveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootAllDeadline extends ParallelDeadlineGroup {
  /**
   * Creates a new ShootAllDeadline.
   */
  public ShootAllDeadline(Shooter pShooter, Conveyor pConveyor, double pTime) {
    // Add your commands in the super() call.  Add the deadline first.
    super(
        new WaitCommand(pTime),
        new RunShooterMax(pShooter),
        new RunConveyorMax(pConveyor)
    );
  }
}