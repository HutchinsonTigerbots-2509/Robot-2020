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
import frc.robot.VariableVault;

public class Colorwheel extends SubsystemBase {

  // Variables
  public static VariableVault vV;

  public static WPI_TalonSRX colorWheelMotor;

  public static ColorSensorV3 colorSensor;

  public static boolean activateChangeColor = false;
  public static boolean activateRotateWheel = false;

  public static Color _currentColor;
  public static Color _expectedColor;
  public static Color previousColor;
  public static Color targetColor;

  
  public static int _currentHalfRevolutions;

  /**
   * Creates a new Colorwheel subsystem.
   */
  public Colorwheel() {
   vV = new VariableVault();

   colorWheelMotor = new WPI_TalonSRX(vV.kcolorWheelMotorID);

   colorSensor = new ColorSensorV3(vV.kColorSensorID);

    activateChangeColor = false;
    activateRotateWheel = false;

    _expectedColor = Color.kBlue;

    _currentHalfRevolutions = 0;
  }
 
  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
    _currentColor = colorSensor.getColor();
    
    if(activateChangeColor == true){
      changeColor(_currentColor, _expectedColor);

    } else if(activateRotateWheel == true){
       rotateWheel(4);

    }else{}

    previousColor = _currentColor;
  }
  
  /**
   * rotates the wheel to the expected color
   * @param currentColor (color under our sensor)
   * @param expectedColor (color under their sensor - reference {@link Constants#kBlue}) 
   */
  public void changeColor(Color currentColor, Color expectedColor){
    if(currentColor == expectedColor){
      colorWheelMotor.set(0);
      activateChangeColor = false;
    }else{
      colorWheelMotor.set(.5);
    }
  }
  
  /**
   * Rotates wheel a set amount of rotations and uses the color sensor
   * @param fullRotations
   */
  public void rotateWheel(double fullRotations){
    if(rotationChecker(fullRotations)){
      colorWheelMotor.set(0);
      activateRotateWheel = false;
      _currentHalfRevolutions = 0;
    }else{
      if(_currentColor != previousColor && _currentColor == targetColor){ //Checks to make sure that the current color is not equal to the previous color
        _currentHalfRevolutions++;
      }
      colorWheelMotor.set(0.5);
    }
  }

  /**
   * Returns true if current revolutions is equal to specified revolutions
   * @param fullRotations
   * @return true/false
   */
  public boolean rotationChecker(double fullRotations){
    if(_currentHalfRevolutions == fullRotations * 2){
      return true;
    }else{
      return false;
    }
  }

  public Color getColor(){
    return colorSensor.getColor();
  }
}
