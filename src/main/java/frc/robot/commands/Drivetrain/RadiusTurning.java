/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.VariableVault;
import frc.robot.subsystems.Drivetrain;

public class RadiusTurning extends CommandBase {
  private final Drivetrain DT;
  public final WPI_TalonSRX RightFront = new WPI_TalonSRX(VariableVault.kRightFrontID);
  public static AHRS DrivetrainGyro = new AHRS(SPI.Port.kMXP);


  /**
   * Creates a new RadiusTurning.
   */
  public RadiusTurning(Drivetrain p_DT) {
    DT = p_DT;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DT);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DT.RadiusTurningFinally(45, .1, 2, "Left");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("IM HERE", "FIRST MOVE");
    DT.RadiusTurningFinally(45, .1, 3, "Left");
    // Timer.delay(1);
    // DrivetrainGyro.reset();
    // SmartDashboard.putString("IM HERE", "SECOND MOVE");
    // DT.RadiusTurningFinally(45, .1, 2, "Right");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putString("IM HERE", "DONE");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
