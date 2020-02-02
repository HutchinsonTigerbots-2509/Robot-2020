/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Conveyor extends SubsystemBase {
  private static AnalogInput BottomLightSensor = new AnalogInput(Constants.kBottomLightSensorID);
  private static AnalogInput TopLightSensor = new AnalogInput(Constants.kTopLightSensorID);
  // private static VictorSPX ConveyorMotor = new VictorSPX(4);
  private static WPI_VictorSPX TopConveyorMotor = new WPI_VictorSPX(Constants.kTopConveyorMotorID);
  private static WPI_VictorSPX BottomConveyorMotor = new WPI_VictorSPX(Constants.kBottomConveyorMotorID);

  /**
   * Creates a new Intake.
   */
  public Conveyor() {

  }

  @Override
  public void periodic() {
    GetTopSensorValue();
    GetBottomSensorValue();
    SmartDashboard.putNumber("Top Sensor int", TopLightSensor.getVoltage());
    SmartDashboard.putNumber("Bottom sensor int", BottomLightSensor.getVoltage());
    // if(GetTopSensorValue() == false){
    //   if(GetBottomSensorValue() == true) {
    //     FullConveyorForward(0.5);
    //   }else {
    //     StopMotors();
    //   }
    // } else {
    //   if(GetBottomSensorValue() == true) {
    //     BottomConveyorForward(0.5);
    //   }else {
    //     StopMotors();
    //   }
    // }
  }

  private boolean GetBottomSensorValue(){
    if(BottomLightSensor.getVoltage() < 0.5) {
      SmartDashboard.putBoolean("Bottom Sensor", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Bottom Sensor", false);
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

  public void BottomConveyorForward(double pSpeed){
    BottomConveyorMotor.set(pSpeed);
  }

  public void FullConveyorForward(double pTopSpeed, double pBottomSpeed){
    TopConveyorMotor.set(pTopSpeed);
    BottomConveyorMotor.set(pBottomSpeed);
  }

  public void ConveyorReverse(){
    TopConveyorMotor.set(-1);
    BottomConveyorMotor.set(-1);
  }

  public void StopMotors(){
    TopConveyorMotor.set(0);
    BottomConveyorMotor.set(0);
  }
}
