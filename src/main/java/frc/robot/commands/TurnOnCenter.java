/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnOnCenter extends CommandBase {
  private final Drivetrain DT;
  private boolean Turned;
  // public final WPI_TalonSRX RightFront = new WPI_TalonSRX(Constants.kRightFrontID);
  // public static AHRS DrivetrainGyro = new AHRS(SPI.Port.kMXP);
  /**
   * Creates a new TurnOnCenter.
   */
  public TurnOnCenter(Drivetrain p_DT) {
    DT = p_DT;

    addRequirements(DT);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("ENDED", false);
    DT.DrivetrainGyro.reset(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DT.TurnOnCenter(-50, 0.4);
    Turned = DT.TurnOnCenter(-50, 0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Turned){
      return true;
    } else {
    return false;
    }
  }
}
