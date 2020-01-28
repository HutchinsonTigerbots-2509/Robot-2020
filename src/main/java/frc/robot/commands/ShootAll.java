/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Conveyor;

public class ShootAll extends CommandBase {
  private static Shooter sShooter;
  private static Conveyor sConveyor;
  /**
   * Creates a new ShootAll.
   */
  public ShootAll(Shooter pShooter, Conveyor pConveyor) {
    sShooter = pShooter;
    sConveyor = pConveyor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
    addRequirements(sConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sShooter.ShooterForward(1);
    sConveyor.ConveyorForward(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sShooter.ShooterForward(1);
    sConveyor.ConveyorForward(1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sShooter.StopShooter();
    sConveyor.StopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
