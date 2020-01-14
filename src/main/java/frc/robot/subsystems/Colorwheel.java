/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Colorwheel extends SubsystemBase {

  static boolean activateChangeColor = false;
  static boolean activateRotateWheel = false;
  static Color _currentColor;
  static Constants constants;
  static ColorSensorV3 colorSensor;
  static Color _expectedColor;
  static WPI_TalonSRX colorWheelMotor;
  static int _currentHalfRevolutions;
  static Color previousColor;
  /**
   * Creates a new Colorwheel.
   */
  public Colorwheel() {
   constants = new Constants();
   colorSensor = new ColorSensorV3(constants.kColorSensorID);

    activateChangeColor = false;
    activateRotateWheel = false;
    _expectedColor = Color.kBlue;

    colorWheelMotor = new WPI_TalonSRX(constants.kcolorWheelMotorID);
    _currentHalfRevolutions = 0;
    
  }
 
  @Override
  public void periodic() {
    _currentColor = colorSensor.getColor();
    if(activateChangeColor == true){
      changeColor(_currentColor, _expectedColor);
    } else if(activateRotateWheel == true){
       rotateWheel(4);
    }else{}
    previousColor = _currentColor;
    // This method will be called once per scheduler run
  }
  
  public void changeColor(Color currentColor, Color expectedColor){
    if(currentColor == expectedColor){
      colorWheelMotor.set(0);
      activateChangeColor = false;
    }else{
      colorWheelMotor.set(.5);
    }
  }
  

  public void rotateWheel(double fullRotations){
    if(rotationChecker(fullRotations)){
      colorWheelMotor.set(0);
      activateRotateWheel = false;
      _currentHalfRevolutions = 0;
    }else{
      if(_currentColor != previousColor){
        colorWheelMotor.set(0.5);
        _currentHalfRevolutions++;
      }
    }
  }

  public boolean rotationChecker(double fullRotations){
    if(_currentHalfRevolutions == fullRotations * 2){
      return true;
    }else{
      return false;
    }
  }
}
