/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriveAutoTime extends CommandBase {

  private Drivetrain sDrivetrain;
  
  // Time Constants
  public static double TIME_init = Timer.getFPGATimestamp();
  public static double TIME_total;

  // Voltage Constants
  public static double VOLTS_max = 0.8;
  public static double VOLTS_min = -0.3;

  // Created Constants
  public static double TIME_half = TIME_total / 2;
  public static double gain = (VOLTS_min - VOLTS_max) / Math.pow((TIME_half), 2);
  public double TIME_curr;
  public double VOLTS_curr = 0;


  private DifferentialDrive drive;

  /**
   * Creates a new DriveAutoTime.
   */
  public DriveAutoTime(Drivetrain pDrivetrain, double pSpeed, double pTime) {
    sDrivetrain = pDrivetrain;
    drive = sDrivetrain.Drive;
    TIME_total = pTime;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sDrivetrain);
  }

  public double getNewVoltCurr(double TIME_curr) {
    return (gain * (Math.pow(TIME_curr - TIME_half, 2))) + VOLTS_max;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    TIME_init = Timer.getFPGATimestamp();
    TIME_curr = Timer.getFPGATimestamp() - TIME_init;
    VOLTS_curr = (gain * (Math.pow(TIME_curr - TIME_half, 2))) + VOLTS_max;
    
    // drive.arcadeDrive(VOLTS_curr, 0);
    drive.tankDrive(-VOLTS_curr, -VOLTS_curr);

    

    // SmartDashboard.putNumber("Gain", gain);
    // SmartDashboard.putNumber("Raw Output",getNewVoltCurr(TIME_curr));
    // SmartDashboard.putNumber("TIME_curr - TIME_half", (TIME_curr - TIME_half));
    // SmartDashboard.putNumber("2", (Math.pow(TIME_curr - TIME_half, 2)));
    // SmartDashboard.putNumber("3", (gain * (Math.pow(TIME_curr - TIME_half, 2))));
    
    SmartDashboard.putNumber("Current Volts", VOLTS_curr);
    SmartDashboard.putNumber("Time_curr", TIME_curr);
    SmartDashboard.putNumber("Gain", gain);
    SmartDashboard.putNumber("Time_init", TIME_init);
    SmartDashboard.putNumber("FPGA Time Stamp", Timer.getFPGATimestamp());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
    // TIME_init = Timer.getFPGATimestamp();
    // TIME_curr = Timer.getFPGATimestamp() - TIME_init;
    // VOLTS_curr = (gain * (Math.pow(TIME_curr - TIME_half, 2))) + VOLTS_max;
    
    // drive.arcadeDrive(VOLTS_curr, 0);

    if (TIME_curr <= 10){
      TIME_curr = Timer.getFPGATimestamp() - TIME_init;
      VOLTS_curr = getNewVoltCurr(TIME_curr);
      drive.tankDrive(-VOLTS_curr, -VOLTS_curr);
    }

    if (Math.abs(VOLTS_curr) < Math.abs(VOLTS_min)){
      drive.tankDrive(-VOLTS_curr, -VOLTS_curr);
    } 

    // SmartDashboard.putNumber("Gain", gain);
    // SmartDashboard.putNumber("Raw Output",getNewVoltCurr(TIME_curr));
    // SmartDashboard.putNumber("TIME_curr - TIME_half", (TIME_curr - TIME_half));
    // SmartDashboard.putNumber("2", (Math.pow(TIME_curr - TIME_half, 2)));
    // SmartDashboard.putNumber("3", (gain * (Math.pow(TIME_curr - TIME_half, 2))));
    
    SmartDashboard.putNumber("Current Volts", VOLTS_curr);
    SmartDashboard.putNumber("Time_curr", TIME_curr);
    SmartDashboard.putNumber("Gain", gain);
    SmartDashboard.putNumber("Time_init", TIME_init);
    SmartDashboard.putNumber("FPGA Time Stamp", Timer.getFPGATimestamp());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() > TIME_init + TIME_total) {
      return true;
    } else {
      return false;
    }
  }
}
