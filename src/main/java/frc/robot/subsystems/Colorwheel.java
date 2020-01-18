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
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Colorwheel extends SubsystemBase {

  // Variables
  public final I2C.Port iPort = Port.kOnboard;

  public static VictorSP colorWheelMotor;

  public static ColorSensorV3 colorSensor;

  public static boolean activateChangeColor = false;
  public static boolean activateRotateWheel = false;

  public static Color currentColor;
  public static String currentColorString;
  public static String expectedColor;
  public static String previousColor;
  public static String targetColor;
  public static String colorString;

  public static ColorMatch colorMatcher;
  
  public static int currentHalfRevolutions;

  /**
   * Creates a new Colorwheel subsystem.
   */
  public Colorwheel() {

   colorWheelMotor = new VictorSP(Constants.kcolorWheelMotorID);

   colorSensor = new ColorSensorV3(iPort);

    activateChangeColor = false;
    activateRotateWheel = false;

    currentHalfRevolutions = 0;

    targetColor = "Unknown";

    colorMatcher = new ColorMatch();

    currentColor = colorSensor.getColor();

    colorMatcher.addColorMatch(Constants.kBlue);
    colorMatcher.addColorMatch(Constants.kGreen);
    colorMatcher.addColorMatch(Constants.kYellow);
    colorMatcher.addColorMatch(Constants.kRed);
  }
 
  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {
    currentColor = colorSensor.getColor();
    ColorMatchResult match = colorMatcher.matchClosestColor(currentColor);
    currentColorString = getColor(match);
    
    if(activateChangeColor == true){
      changeColor(currentColorString, expectedColor);

    } else if(activateRotateWheel == true){
       rotateWheel(4);

    }else{}

    previousColor = currentColorString;

    SmartDashboard.putBoolean("Changing Color", activateChangeColor);
    SmartDashboard.putBoolean("Rotating Wheel", activateRotateWheel);
    SmartDashboard.putString("Color", currentColorString);
    SmartDashboard.putString("Expected Color", expectedColor);
    SmartDashboard.putString("Color Under Sensor", colorSensor.toString());
    SmartDashboard.putString("Previous Color", previousColor);
    SmartDashboard.putString("Target Color", targetColor);
    SmartDashboard.putNumber("Current Half Revolutions", currentHalfRevolutions);
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
      currentHalfRevolutions = 0;

    }else{
      if(currentColorString != previousColor && currentColorString == targetColor){ //Checks to make sure that the current color is not equal to the previous color
        currentHalfRevolutions++;

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
    if(currentHalfRevolutions == fullRotations * 2){
      return true;

    }else{
      return false;

    }
  }

  public Color getColor(){
    return colorSensor.getColor();
  }

  public String getColor(ColorMatchResult match){

    if (match.color == Constants.kBlue) {
      colorString = "Blue";

    } else if (match.color == Constants.kRed) {
      colorString = "Red";

    } else if (match.color == Constants.kGreen) {
      colorString = "Green";

    } else if (match.color == Constants.kYellow) {
      colorString = "Yellow";

    } else {
      colorString = "Pina Colada";
      
    }
    return colorString;
  }

  public String getColor2(Color color){

    if (Constants.kBlue == color) {
      colorString = "Blue";

    } else if (Constants.kRed == color) {
      colorString = "Red";

    } else if (Constants.kGreen == color) {
      colorString = "Green";

    } else if (Constants.kYellow == color) {
      colorString = "Yellow";

    } else {
      colorString = "Unknown";
      
    }
    return colorString;
  }

  public void setTargetColor(){
    ColorMatchResult match = colorMatcher.matchClosestColor(currentColor);
    targetColor = getColor(match);
  }

  public void activateCW(){
    activateChangeColor = true;
  }

  public void activateRW(){
    activateRotateWheel = true;
  }
}