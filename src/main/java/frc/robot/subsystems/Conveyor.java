/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Conveyor extends SubsystemBase {
  private static AnalogInput LightSensor = new AnalogInput(0);
  // private static VictorSPX ConveyorMotor = new VictorSPX(4);
  private static WPI_VictorSPX ConveyorMotor = new WPI_VictorSPX(4);

  /**
   * Creates a new Intake.
   */
  public Conveyor() {

  }

  @Override
  public void periodic() {

    if(GetSensorValue() == true) {
      ConveyorForward(0.5);
    }else {
      StopMotor();
    }

  }

  private boolean GetSensorValue(){
    SmartDashboard.putNumber("Analog value", LightSensor.getVoltage());
    if(LightSensor.getVoltage() < 0.5) {
      SmartDashboard.putBoolean("Detecting a Thing", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Detecting a Thing", false);
      return false;
    }
  } 

  public void ConveyorForward(double pSpeed){
    ConveyorMotor.set(pSpeed);
  }

  public void ConveyorReverse(){
    ConveyorMotor.set(-1);
  }

  public void StopMotor(){
    ConveyorMotor.set(0);
  }
}
