/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoVision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FoundContour;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.USBVision;

public class ProcessAndGrab extends CommandBase {
  private USBVision sUsbVision;
  private Intake sIntake;
  private Drivetrain sDrivetrain;

  private FoundContour[] contours;
  private double leastY = 480;
  private double leastX = 640;
  /**
   * Creates a new ProcessAndGrab.
   */
  public ProcessAndGrab(USBVision pUsbVision, Intake pIntake, Drivetrain pDrivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    sUsbVision = pUsbVision;
    sIntake = pIntake;
    sDrivetrain = pDrivetrain;

    // addRequirements(sIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    contours = sUsbVision.getContours();

    for (FoundContour Contour : contours) {
      if (Contour.CenterY < leastY && Contour.CenterY > 0) {
        leastY = Contour.CenterY;
        if (Contour.CenterX < leastX && Contour.CenterX  > 0) {
          leastX = Contour.CenterX;
        }
      }
    }

    for (FoundContour Contour : contours) {
      if (Contour.CenterX == leastX) {
        Contour.IsTargeted = true;
      } else {
        Contour.IsTargeted = false;
      }
    }

    sIntake.IntakeIn();
    // new RunCommand(() -> sIntake.IntakeIn());
    for (FoundContour contour : contours) {
      if (contour.IsTargeted) {
        if (contour.CenterX < (640 / 2) - 20) {
          sDrivetrain.Drive.tankDrive(0.5, 0.6);
        } else if (contour.CenterX > (640 / 2) + 20) {
          sDrivetrain.Drive.tankDrive(0.6, 0.5);
        } else {
          sDrivetrain.Drive.tankDrive(0.5,0.5);
        }
      }
    }

    SmartDashboard.putNumber("Contours 0 X", contours[0].CenterX);
    SmartDashboard.putNumber("Contours 0 Y", contours[0].CenterY);
    SmartDashboard.putBoolean("Contours 0 IsTargeted", contours[0].IsTargeted);

    SmartDashboard.putNumber("Contours 1 X", contours[1].CenterX);
    SmartDashboard.putNumber("Contours 1 Y", contours[1].CenterY);
    SmartDashboard.putBoolean("Contours 1 IsTargeted", contours[1].IsTargeted);

    SmartDashboard.putNumber("Contours 2 X", contours[2].CenterX);
    SmartDashboard.putNumber("Contours 2 Y", contours[2].CenterY);
    SmartDashboard.putBoolean("Contours 2 IsTargeted", contours[2].IsTargeted);

    SmartDashboard.putNumber("Contours 3 X", contours[3].CenterX);
    SmartDashboard.putNumber("Contours 3 Y", contours[3].CenterY);
    SmartDashboard.putBoolean("Contours 3 IsTargeted", contours[3].IsTargeted);

    SmartDashboard.putNumber("Contours 4 X", contours[4].CenterX);
    SmartDashboard.putNumber("Contours 4 Y", contours[4].CenterY);
    SmartDashboard.putBoolean("Contours 4 IsTargeted", contours[4].IsTargeted);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    contours = sUsbVision.getContours();

    leastX = 640;
    leastY = 480;

    for (FoundContour Contour : contours) {
      if (Contour.CenterY < leastY && Contour.CenterY > 0) {
        leastY = Contour.CenterY;
        if (Contour.CenterX < leastX && Contour.CenterX  > 0) {
          leastX = Contour.CenterX;
        }
      }
    }

    for (FoundContour Contour : contours) {
      if (Contour.CenterX == leastX) {
        Contour.IsTargeted = true;
      } else {
        Contour.IsTargeted = false;
      }
    }

    sIntake.IntakeIn();
    // new RunCommand(() -> sIntake.IntakeIn());
    for (FoundContour contour : contours) {
      if (contour.IsTargeted) {
        if (contour.CenterX < (640 / 2) - 20) {
          sDrivetrain.Drive.tankDrive(0.3, 0.4);
        } else if (contour.CenterX > (640 / 2) + 20) {
          sDrivetrain.Drive.tankDrive(0.4, 0.3);
        } else {
          sDrivetrain.Drive.tankDrive(0.3,0.3);
        }
      }
    }

    SmartDashboard.putNumber("Contours 0 X", contours[0].CenterX);
    SmartDashboard.putNumber("Contours 0 Y", contours[0].CenterY);
    SmartDashboard.putBoolean("Contours 0 IsTargeted", contours[0].IsTargeted);

    SmartDashboard.putNumber("Contours 1 X", contours[1].CenterX);
    SmartDashboard.putNumber("Contours 1 Y", contours[1].CenterY);
    SmartDashboard.putBoolean("Contours 1 IsTargeted", contours[1].IsTargeted);

    SmartDashboard.putNumber("Contours 2 X", contours[2].CenterX);
    SmartDashboard.putNumber("Contours 2 Y", contours[2].CenterY);
    SmartDashboard.putBoolean("Contours 2 IsTargeted", contours[2].IsTargeted);

    SmartDashboard.putNumber("Contours 3 X", contours[3].CenterX);
    SmartDashboard.putNumber("Contours 3 Y", contours[3].CenterY);
    SmartDashboard.putBoolean("Contours 3 IsTargeted", contours[3].IsTargeted);

    SmartDashboard.putNumber("Contours 4 X", contours[4].CenterX);
    SmartDashboard.putNumber("Contours 4 Y", contours[4].CenterY);
    SmartDashboard.putBoolean("Contours 4 IsTargeted", contours[4].IsTargeted);
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
