/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.VariableVault;

public class Colorwheel extends SubsystemBase {

  // Variables
  public static VariableVault vV;

  public static VictorSP colorWheelMotor;

  public static ColorSensorV3 colorSensor;

  public static boolean activateChangeColor = false;
  public static boolean activateRotateWheel = false;

  public static String _currentColor;
  public static String _expectedColor;
  public static String previousColor;
  public static String targetColor;
  private static String colorString;

  private final ColorMatch colorMatcher = new ColorMatch();
  
  public static int _currentHalfRevolutions;

  /**
   * Creates a new Colorwheel subsystem.
   */
  public Colorwheel() {
   vV = new VariableVault();

   colorWheelMotor = new VictorSP(vV.kcolorWheelMotorID);

   colorSensor = new ColorSensorV3(vV.kColorSensorID);

    activateChangeColor = false;
    activateRotateWheel = false;

    _expectedColor = getColor(Color.kBlue);

    _currentHalfRevolutions = 0;

    targetColor = "Unknown";

    colorMatcher.addColorMatch(vV.kBlue);
    colorMatcher.addColorMatch(vV.kGreen);
    colorMatcher.addColorMatch(vV.kYellow);
    colorMatcher.addColorMatch(vV.kRed);
  }
 
  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
    ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
    _currentColor = getColor(match, colorSensor.getColor());
    
    if(activateChangeColor == true){
      changeColor(_currentColor, _expectedColor);

    } else if(activateRotateWheel == true){
       rotateWheel(4);

    }else{}

    previousColor = _currentColor;

    SmartDashboard.putBoolean("Changing Color", activateChangeColor);
    SmartDashboard.putBoolean("Rotating Wheel", activateRotateWheel);
    SmartDashboard.putString("Color", _currentColor);
    SmartDashboard.putString("Expected Color", _expectedColor);
    SmartDashboard.putString("Color Under Sensor", colorSensor.toString());
    SmartDashboard.putString("Previous Color", previousColor);
    SmartDashboard.putString("Target Color", targetColor);
    SmartDashboard.putNumber("Current Half Revolutions", _currentHalfRevolutions);
  }
  
  /**
   * rotates the wheel to the expected color
   * @param currentColor (color under our sensor)
   * @param expectedColor (color under their sensor - reference {@link Constants#kBlue}) 
   */
  public void changeColor(String currentColor, String expectedColor){

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

  public String getColor(ColorMatchResult result, Color color){

    if (result.color == color) {
      colorString = "Blue";

    } else if (result.color == color) {
      colorString = "Red";

    } else if (result.color == color) {
      colorString = "Green";

    } else if (result.color == color) {
      colorString = "Yellow";

    } else {
      colorString = "Unknown";
      
    }
    return colorString;
  }

  public String getColor(Color color){

    if (vV.kBlue == color) {
      colorString = "Blue";

    } else if (vV.kRed == color) {
      colorString = "Red";

    } else if (vV.kGreen == color) {
      colorString = "Green";

    } else if (vV.kYellow == color) {
      colorString = "Yellow";

    } else {
      colorString = "Unknown";
      
    }
    return colorString;
  }
}
