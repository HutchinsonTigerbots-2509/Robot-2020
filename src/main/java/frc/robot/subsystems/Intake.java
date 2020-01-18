/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends SubsystemBase {
  private static AnalogInput LightSensor = new AnalogInput(0);
  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  @Override
  public void periodic() {
    if(LightSensor.getVoltage() > 0.5) {
      SmartDashboard.putBoolean("Detecting a Thing", true);
    } else {
      SmartDashboard.putBoolean("Detecting a Thing", false);
    }
    // This method will be called once per scheduler run
  }
}
