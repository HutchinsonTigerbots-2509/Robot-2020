/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class AlignTurret extends CommandBase {
  private final Vision sVision;
  private final Shooter sShooter;
  private double TargetX;
  private double Speed;
  private boolean Aligned;


  /**
   * Creates a new AlignTurret.
   */
  public AlignTurret(Vision pVision, Shooter pShooter) {
    sVision = pVision;
    sShooter = pShooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sVision);
    addRequirements(sShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TargetX = sVision.getTargetX();
    Aligned = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TargetX = sVision.getTargetX();
    // Speed = Math.max(TargetX * 0.0002, Constants.kTurretMinVoltage);
    if(TargetX < 4 && TargetX > -4){
      Speed = 0.01;
    } else if (TargetX < 6 && TargetX > -6){
      Speed = 0.03;
    } else if (TargetX < 13 && TargetX > -13) {
      Speed = 0.07;
    } else {
      Speed = 0.12;
    }

    if(TargetX > 2.5){
      sShooter.TurnLeft(Speed);
      Aligned = false;
    } else if(TargetX < -2.5){
      sShooter.TurnRight(Speed);
      Aligned = false;
    } else {
      sShooter.StopTurretMotor();
      Aligned = true;

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sShooter.StopTurretMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Aligned = true){
      // return true;
      return false;
    } else {
      return false;
    }
  }
}
