/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class RampUpShooter extends CommandBase {
  private static Shooter sShooter;
  private double Speed = 0.6;
  private double TargetSpeed = 0.8;
  private int TargetRPM = 3000;
  private boolean Finished = false;
  /**
   * Creates a new RampUpShooter.
   */
  public RampUpShooter(Shooter pShooter) {
    sShooter = pShooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sShooter.ShooterForward(Speed, Speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Speed < TargetSpeed){
      Speed = Speed + 0.02;
    }

    // if(TargetRPM > Math.abs(sShooter.GetRPM())){
    //   sShooter.ShooterForward(Speed, Speed);
    // } else {
    //   Finished = true;
    // }
    sShooter.ShooterForward(Speed, Speed);
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
