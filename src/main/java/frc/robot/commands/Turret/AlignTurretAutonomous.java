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
  private double Speed;
  private boolean Aligned;
  private double TargetDegrees;
  private double Offset;


  /**
   * Creates a new AlignTurret.
   */
  // (From the perspective of the camera)
  // A positive offset turns the turret right
  // A negative offset turns the turret left
  public AlignTurretAutonomous(Vision pVision, Turret pTurret, double pOffset) {
    sVision = pVision;
    sTurret = pTurret;
    Offset = pOffset;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sTurret);
    addRequirements(sVision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Align Target", true);
    TargetX = sVision.getTargetX();
    Aligned = false;
    TargetDegrees = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    TargetX = sVision.getTargetX();
    // Speed = Math.max(TargetX * 0.08, Constants.kTurretMinVoltage);
    if(TargetX > TargetDegrees + (7 + Offset)){
      Speed = Math.max(TargetX * 0.35, Constants.kTurretMinVoltage);//.01
      sTurret.TurnLeft(Speed);
      Aligned = false;
    } else if(TargetX < TargetDegrees - (7 + Offset)){
      Speed = Math.max(TargetX * 0.35, Constants.kTurretMinVoltage);
      sTurret.TurnRight(Speed);
      Aligned = false;
    } else if(TargetX > TargetDegrees + (2 + Offset)){
      Speed = Math.max(TargetX * 0.12, Constants.kTurretMinVoltage);
      sTurret.TurnRight(Speed);
      Aligned = false;
    } else if(TargetX < TargetDegrees - (2 + Offset)){ //-2
      Speed = Math.max(TargetX * 0.12, Constants.kTurretMinVoltage);
      sTurret.TurnRight(Speed);
      Aligned = false;
    } else if(TargetX > TargetDegrees + (1.1 + Offset)){ //2
      Speed = Math.max(TargetX * 0.1, Constants.kTurretMinVoltage);//.08
      sTurret.TurnLeft(Speed);
      Aligned = false;
    } else if(TargetX < TargetDegrees - (1.1 + Offset)){
      Speed = Math.max(TargetX * 0.1, Constants.kTurretMinVoltage);//.08
      sTurret.TurnLeft(Speed);
      Aligned = false;
    } else {
      sTurret.StopTurretMotor();
      Aligned = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sTurret.StopTurretMotor();
    SmartDashboard.putBoolean("Align Target", false);
    sTurret.Aligned = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return sTurret.Aligned;
    return false;
  }
}
