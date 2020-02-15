/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Turret;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Vision;
import frc.robot.Constants;
import frc.robot.subsystems.Turret;

public class AlignTurretAutonomous extends CommandBase {
  private final Vision sVision;
  private final Turret sTurret;
  private double TargetX;
  private double TargetFound;
  private double Speed;
  private double TargetDegrees;


  /**
   * Creates a new AlignTurret.
   */
  public AlignTurretAutonomous(Vision pVision, Turret pTurret) {
    sVision = pVision;
    sTurret = pTurret;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sTurret);
    addRequirements(sVision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Align Target", true);
    TargetX = sVision.getTargetX();
    TargetFound = sVision.getTargetFound();
    sTurret.Aligned = false;
    TargetDegrees = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TargetX = sVision.getTargetX();
    TargetFound = sVision.getTargetFound();
    // Speed = Math.max(TargetX * 0.08, Constants.kTurretMinVoltage);
    if(TargetFound == 1){
      if(TargetX > TargetDegrees + 6){
        Speed = Math.max(TargetX * 0.2, Constants.kTurretMinVoltage);
        sTurret.TurnLeft(Speed);
        sTurret.Aligned = false;
      } else if(TargetX < TargetDegrees - 6){
        Speed = Math.max(TargetX * 0.2, Constants.kTurretMinVoltage);
        sTurret.TurnRight(Speed);
        sTurret.Aligned = false;
      } else if(TargetX > TargetDegrees + 1.5){ //2
        Speed = Math.max(TargetX * 0.08, Constants.kTurretMinVoltage);
        sTurret.TurnLeft(Speed);
        sTurret.Aligned = false;
      } else if(TargetX < TargetDegrees - 1.5){ //-2
        Speed = Math.max(TargetX * 0.08, Constants.kTurretMinVoltage);
        sTurret.TurnRight(Speed);
        sTurret.Aligned = false;
      } else {
        sTurret.StopTurretMotor();
        sTurret.Aligned = true;
      }
    } else {
      sTurret.TurnRight(0.9);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sTurret.StopTurretMotor();
    SmartDashboard.putBoolean("Align Target", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (sTurret.Aligned == true){
      // return true;
      return false;
    } else {
      return false;
    }
  }
}
