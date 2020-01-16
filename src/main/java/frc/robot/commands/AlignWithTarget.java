/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Drivetrain;

public class AlignWithTarget extends CommandBase {
  private final Vision sVision;
  private final Drivetrain sDrivetrain;
  private double TargetX;
  private boolean Aligned;
  private double Speed;
  /**
   * Creates a new AlignWithTarget.
   */
  public AlignWithTarget(Vision pVision, Drivetrain pDrivetrain) {
    sVision = pVision;
    sDrivetrain = pDrivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sVision);
    addRequirements(sDrivetrain);
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
    Speed = Math.max(TargetX * 0.1, 0.75);

    if(TargetX > 2){
      sDrivetrain.TurnLeft(Speed);
    } else if (TargetX < -2) {
      sDrivetrain.TurnRight(Speed);
    } else {
      sDrivetrain.StopMotors();
      Aligned = true;
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sDrivetrain.StopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Aligned == true){
      return true;
    } else {
      return false;
    }
  }
}
