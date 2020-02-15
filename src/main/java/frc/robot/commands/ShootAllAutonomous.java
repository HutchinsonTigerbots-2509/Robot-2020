/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Conveyor;

public class ShootAllAutonomous extends CommandBase {
  private Conveyor sConveyor;
  private Shooter sShooter;
  private Boolean ReadyToShoot;
  private int RPM;
  /**
   * Creates a new ShootAllAutonomous.
   */
  public ShootAllAutonomous(Shooter pShooter, Conveyor pConveyor, int pRPM) {
    sShooter = pShooter;
    sConveyor = pConveyor;
    RPM = pRPM;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sShooter);
    addRequirements(sConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sShooter.ShooterForward(0.8);
    ReadyToShoot = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.abs(sShooter.GetRPM()) > RPM){
      ReadyToShoot = true;
    }

    if(ReadyToShoot){
      sConveyor.FullConveyorForward(0.9, 0.8);
    }

    sShooter.ShooterForward(0.8);//.9
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
