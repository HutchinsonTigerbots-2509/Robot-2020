/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoVision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FoundContour;
import frc.robot.subsystems.USBVision;

public class ProcessVision extends CommandBase {
  private USBVision sUsbVision;

  private FoundContour[] contours;
  /**
   * Creates a new ProcessVision.
   */
  public ProcessVision(USBVision pUsbVision) {
    // Use addRequirements() here to declare subsystem dependencies.
    sUsbVision = pUsbVision;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    contours = sUsbVision.getContours();

    SmartDashboard.putNumber("Contours 0", contours[0].CenterX);
    SmartDashboard.putNumber("Contours 1", contours[1].CenterX);
    SmartDashboard.putNumber("Contours 2", contours[2].CenterX);
    SmartDashboard.putNumber("Contours 3", contours[3].CenterX);
    SmartDashboard.putNumber("Contours 4", contours[4].CenterX);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    contours = sUsbVision.getContours();
    
    SmartDashboard.putNumber("Contours 0", contours[0].CenterX);
    SmartDashboard.putNumber("Contours 1", contours[1].CenterX);
    SmartDashboard.putNumber("Contours 2", contours[2].CenterX);
    SmartDashboard.putNumber("Contours 3", contours[3].CenterX);
    SmartDashboard.putNumber("Contours 4", contours[4].CenterX);
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
