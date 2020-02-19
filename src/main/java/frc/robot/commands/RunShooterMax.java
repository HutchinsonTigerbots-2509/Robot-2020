/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RunShooterMax extends CommandBase {
  private static Shooter sShooter;
  private double BottomSpeed;
  private double TopSpeed;
  /**
   * Creates a new RunShooterMax.
   */
  public RunShooterMax(Shooter pShooter) {
    sShooter = pShooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    BottomSpeed = 1; //0.8
    TopSpeed = 1; //0.8
    sShooter.ShooterForward(BottomSpeed, TopSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(BottomSpeed < TargetBottomSpeed){
    //   BottomSpeed = BottomSpeed + 0.02;
    // }
    // if(TopSpeed < TargetTopSpeed){
    //   TopSpeed = TopSpeed + 0.02;
    // }
    sShooter.ShooterForward(BottomSpeed, TopSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sShooter.StopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
