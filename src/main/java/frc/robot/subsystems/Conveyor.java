/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  private static AnalogInput BottomLightSensor = new AnalogInput(Constants.kBottomLightSensorID);
  private static AnalogInput TopLightSensor = new AnalogInput(Constants.kTopLightSensorID);
  // private static VictorSPX ConveyorMotor = new VictorSPX(4);
  private static VictorSP ConveyorMotor = new VictorSP(Constants.kConveyorMotorID);

  /**
   * Creates a new Intake.
   */
  public Conveyor() {

  }

  @Override
  public void periodic() {
    if(GetTopSensorValue() == false){
      if(GetBottomSensorValue() == true) {
        ConveyorForward(0.5);
      }else {
        StopMotor();
      }
    } else{
      StopMotor();
    }

  }

  private boolean GetBottomSensorValue(){
    if(BottomLightSensor.getVoltage() < 0.5) {
      SmartDashboard.putBoolean("Detecting a Thing", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Detecting a Thing", false);
      return false;
    }
  } 

  private boolean GetTopSensorValue(){
    if(TopLightSensor.getVoltage() < 0.5){
      SmartDashboard.putBoolean("Top Sensor", true);
      return true;
    } else{
      SmartDashboard.putBoolean("Top Sensor", false);
      return false;
    }
  }

  public void ConveyorForward(double pSpeed){
    ConveyorMotor.set(1);
  }

  public void ConveyorReverse(){
    ConveyorMotor.set(-1);
  }

  public void StopMotor(){
    ConveyorMotor.set(0);
  }
}
