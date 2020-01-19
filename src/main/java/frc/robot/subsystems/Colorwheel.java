/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.VariableVault;;

/**
 * The colorwheel controls the motor that interacts with the color wheel in order to rotate a set amount
 * of revolutions, or to set the color to the one given to us by the game.
 * @author Cole G.
 * @author Teagan Y.
 */
public class Colorwheel extends SubsystemBase {
  //#region Public Variables

  // Variable Vault
  private static VariableVault vV;

  // Color Variables
  private static Color colorPlaceHolderColor;

  private static Color colorCurrentColor; // Color under our sensor
  private static Color colorExpectedColor; // Color under their sensor
  private static Color colorPreviousColor; // Previous color

  private static int colorTracker;

  // Revolution Variables
  private static double fullRevolutions;
  private static double currentRevolutions;

  // Activator Variables
  private static boolean activateChangeColor;
  private static boolean activateRotateWheel;


  //#endregion
  // -----Divider Line-----
  //#region Public Variables

  // Motors
  public static VictorSP colorWheelMotor;

  // Color Sensor Variables
  public static ColorSensorV3 colorSensor;
  public static ColorMatch colorMatcher;

  //#endregion

  /**
   * Creates a new Colorwheel subsystem
   */
  public Colorwheel() {

    // Initializing Variables
    vV = new VariableVault();
    colorCurrentColor = colorPlaceHolderColor;
    colorExpectedColor = colorPlaceHolderColor;
    colorPreviousColor = colorPlaceHolderColor;
    colorTracker = 0;
    fullRevolutions = vV.kRevolutionsWanted;
    currentRevolutions = 0.0;
    activateChangeColor = false;
    activateRotateWheel = false;
    
    colorWheelMotor = new VictorSP(vV.kColorWheelMotorID);
    colorSensor = new ColorSensorV3(vV.iPort);
    colorMatcher = new ColorMatch();

    colorMatcher.addColorMatch(vV.kBlue);
    colorMatcher.addColorMatch(vV.kGreen);
    colorMatcher.addColorMatch(vV.kRed);
    colorMatcher.addColorMatch(vV.kYellow);
    colorCurrentColor = colorMatcher.matchClosestColor(colorSensor.getColor()).color;
  }

  /**
   * Initiates the color wheel rotating process
   * 
   * @apiNote *1: Blue *2: Yellow *3: Red *4: Green
   * @implNote Because of Yellow and Blue making Green the process will do a quick jolt to get an accurate reading of the color
   */
  public void initGetStartingColor() {
    if (getCurrentColor() == vV.kGreen){
      colorWheelMotor.set(vV.kColorWheelMotorSpeedSlow);
        Timer.delay(vV.kJoltDelay);
        colorWheelMotor.stopMotor();
    }
    Timer.delay(vV.kDelay);
    colorCurrentColor = colorMatcher.matchClosestColor(colorSensor.getColor()).color;
    if (getCurrentColor() == vV.kBlue){
      colorTracker = 1;
    } else if (getCurrentColor() == vV.kYellow){
      colorTracker = 2;
    } else if (getCurrentColor() == vV.kRed){
      colorTracker = 3;
    } else if (getCurrentColor() == vV.kGreen){
      colorTracker = 4;
    }
  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {

    prePeriodic();
    // Periodic Method Start

    if (activateChangeColor == true){

      changeColor();

    } else {}

    // Periodic Method End
    postPeriodic();

  }

  /**
   * Starts rotating the wheel for a set amount of time
   */
  public void rotateWheel(){
    colorWheelMotor.set(vV.kColorWheelMotorSpeedFast);
    Timer.delay(vV.kRotateDelay);
    colorWheelMotor.stopMotor();
  }

  /**
   * Changes the current color to the color that our sensor should have to match
   * the one under theirs
   */
  private void changeColor() {
    if (colorTracker == 1){ // If color should be blue
      if (colorCurrentColor == vV.kYellow){
        if (colorMatchsGame()){
          colorWheelMotor.set(0.2);
          colorWheelMotor.stopMotor();
          activateChangeColor = false;
        }
        colorTracker++;
      }
    } else if (colorTracker == 2){ // If color should be Yellow
      if (colorCurrentColor == vV.kRed){
        if (colorMatchsGame()){
          colorWheelMotor.set(0.2);
          colorWheelMotor.stopMotor();
          activateChangeColor = false;
        }
        colorTracker++;      
      }
    } else if (colorTracker == 3){ // If color should be Red
      if (colorCurrentColor == vV.kGreen){
        if (colorMatchsGame()){
          colorWheelMotor.set(0.2);
          colorWheelMotor.stopMotor();
          activateChangeColor = false;
        }
        colorTracker++;     
      }
    } else if (colorTracker == 4){ // If color should be Green
      if (colorCurrentColor == vV.kBlue){
        if (colorMatchsGame()){
          colorWheelMotor.set(0.2);
          colorWheelMotor.stopMotor();
          activateChangeColor = false;
        }
        colorTracker = 1;        
      }
    }
  }

  /**
   * Checks to make sure that the color under their sensor matchs the color that should
   * be under our sensor
   */
  private boolean colorMatchsGame(){
    getGameColor();
    if (colorExpectedColor == vV.kBlue){

      if (colorCurrentColor == vV.kRed){
        return true;
      } else { return false; }

    } else if (colorExpectedColor == vV.kGreen){

      if (colorCurrentColor == vV.kYellow){
        return true;
      } else { return false; }

    } else if (colorExpectedColor == vV.kRed){

      if (colorCurrentColor == vV.kBlue){
        return true;
      } else { return false; }

    } else if (colorExpectedColor == vV.kYellow){

      if (colorCurrentColor == vV.kGreen){
        return true;
      } else { return false; }

    } else { return false; }
  }

  /**
   * Converts a color from a Color object to a String object
   * (Color needs to already be matched to a closest color)
   * @param color
   */
  private String colorToString(Color color){
    String finalColor;

    if (color == vV.kBlue){
      finalColor = "Blue";
    } else if (color == vV.kGreen){
      finalColor = "Green";
    } else if (color == vV.kRed){
      finalColor = "Red";
    } else if (color == vV.kYellow){
      finalColor = "Yellow";
    } else {
      finalColor = "Not Recongized";
    }

    return finalColor;
  }

  /**
   * Items to initialize before periodic
   */
  private void prePeriodic(){
    colorCurrentColor = colorMatcher.matchClosestColor(colorSensor.getColor()).color;
  }

  /**
   * Items to initialize after periodic
   */
  private void postPeriodic(){
    colorPreviousColor = colorCurrentColor;

    updateDashboard();
  }

  /**
   * Updates the dashboard for the Colorwheel subsystem
   */
  private void updateDashboard(){
    SmartDashboard.putBoolean("Change Color", activateChangeColor);
    SmartDashboard.putBoolean("Rotate Wheel", activateRotateWheel);

    if (colorCurrentColor != colorPlaceHolderColor){
      SmartDashboard.putString("CurrentColor", colorToString(colorCurrentColor));
    } else {
      SmartDashboard.putString("CurrentColor", "Not Initialized");
    }
    if (colorPreviousColor != colorPlaceHolderColor){
      SmartDashboard.putString("Previous Color", colorToString(colorPreviousColor));
    } else {
      SmartDashboard.putString("Previous Color", "Not Initialized");
    }
    if (colorExpectedColor != colorPlaceHolderColor){
      SmartDashboard.putString("Expected Color", colorToString(colorExpectedColor));
    } else {
      SmartDashboard.putString("Expected Color", "Not Initialized");
    }

    SmartDashboard.putNumber("Motor Speed", colorWheelMotor.get());
    SmartDashboard.putNumber("Current Revolutions", currentRevolutions);
    SmartDashboard.putNumber("Wanted Revolutions", fullRevolutions);

    SmartDashboard.putNumber("Color Tracker", colorTracker);
  }

  /**
   * Will recive the color code provided by the game and set it as expected color
   */
  public void getGameColor(){
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          //Blue case code
          setExpectedColor(vV.kBlue);
          break;
        case 'G' :
          //Green case code
          setExpectedColor(vV.kGreen);
          break;
        case 'R' :
          //Red case code
          setExpectedColor(vV.kRed);
          break;
        case 'Y' :
          //Yellow case code
          setExpectedColor(vV.kYellow);
          break;
        default :
          //This is corrupt data
          setExpectedColor(colorPlaceHolderColor);
          break;
      }
    } else {
      //Code for no data received yet
    }
  }

  //#region get and set functions
  /**
   * Returns the current color as a Color object
   */
  public Color getCurrentColor() {
    return colorMatcher.matchClosestColor(colorCurrentColor).color;
  }

  /**
   * Returns the previous color as a Color object
   */
  public Color getPreviousColor() {
    return colorPreviousColor;
  }

  /**
   * Returns the expected color as a Color object
   */
  public Color getExpectedColor() {
    return colorExpectedColor;
  }

  /**
   * Returns the full revolutions wanted
   */
  public double getFullRevolutions(){
    return fullRevolutions;
  }

  /**
   * Returns current revolutions
   */
  public double getCurrentRevolutions(){
    return currentRevolutions;
  }

  /**
   * Sets the motors at a specified speed
   * @param speed
   */
  public void setMotorSpeed(double speed){
    colorWheelMotor.set(speed);
  }

  /**
   * Sets the current color equall to the closest color on the sensor
   */
  public void setCurrentColor() {
    colorCurrentColor = colorMatcher.matchClosestColor(colorSensor.getColor()).color;
  }

  /**
   * Sets the current color to a specified color
   * (Will match specified color to the closest color)
   * @param color
   */
  public void setCurrentColor(Color color) {
    colorCurrentColor = colorMatcher.matchClosestColor(color).color;
  }

  /**
   * Sets the previous color to a specified color
   * (Will match specified color to the closest color)
   * @param color
   */
  public void setPreviousColor(Color color) {
    colorCurrentColor = colorMatcher.matchClosestColor(color).color;
  }

  /**
   * Sets the expected color to a specified color
   * (Will match specified color to the closest color)
   * @param color
   */
  public void setExpectedColor(Color color){
    colorExpectedColor = colorMatcher.matchClosestColor(color).color;
  }

  /**
   * Sets the revolutions wanted
   * @param revolutions
   */
  public void setRevolutionsWanted(double revolutions){
    fullRevolutions = revolutions;
  }

  /**
   * Sets the current revolutions
   * @param revolutions
   */
  public void setCurrentRevolutions(double revolutions){
    currentRevolutions = revolutions;
  }

  /**
   * Set the color tracker to a specified integer
   * @param number
   */
  public void setColorTracker(int number){
    colorTracker = number;
  }

  /**
   * Sets activateChangeColor to true/false
   */
  public void activateChangeColorSwitch(){
    if (activateChangeColor == true){
      activateChangeColor = false;
      colorWheelMotor.stopMotor();
    } else {
      activateChangeColor = true;
    }
  }

  /**
   * Sets activateRotateWheel to true/false
   */
  public void activateRotateWheelSwitch(){
    if (activateRotateWheel == true){
      activateRotateWheel = false;
      currentRevolutions = 0;
      colorWheelMotor.stopMotor();
    } else {
      activateRotateWheel = true;
    }
  }

  //#endregion
}