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
import edu.wpi.first.wpilibj.VictorSP;

public class Intake extends SubsystemBase {
  private static AnalogInput LightSensor = new AnalogInput(0);
  private static VictorSP ConveyorMotor = new VictorSP(0);

  /**
   * Creates a new Intake.
   */
  public Intake() {

  }

  @Override
  public void periodic() {
    if(GetSensorValue() == true) {
      ConveyorForward();
    }else {
      StopMotor();
    }
  }

  private boolean GetSensorValue(){
    if(LightSensor.getVoltage() > 0.5) {
      SmartDashboard.putBoolean("Detecting a Thing", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Detecting a Thing", false);
      return false;
    }
  } 

  public void ConveyorForward(){
    ConveyorMotor.set(1);
  }

  public void ConveyorReverse(){
    ConveyorMotor.set(-1);
  }

  public void StopMotor(){
    ConveyorMotor.set(0);
  }
}
