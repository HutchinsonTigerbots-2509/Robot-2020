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

  }

  /**
   * This method will be called once per scheduler run
   */
  @Override
  public void periodic() {

    prePeriodic();
    // Periodic Method Start

    if (activateChangeColor == true && activateRotateWheel == true){

      activateChangeColor = false;
      activateRotateWheel = false;

    } else if (activateChangeColor == true){

      changeColor();

    } else if (activateRotateWheel == true){

      rotateWheel();

    } else {}

    // Periodic Method End
    postPeriodic();

  }

  /**
   * Changes the current color to the color that our sensor should have to match
   * the one under theirs
   */
  private void changeColor() {
    if (colorMatchsGame()){
      colorWheelMotor.stopMotor();
      activateChangeColor = false;
    } else {
      colorWheelMotor.set(vV.kColorWheelMotorSpeed);
    }
  }

  /**
   * Checks to make sure that the color under their sensor matchs the color that should
   * be under our sensor
   */
  private boolean colorMatchsGame(){
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
   * Rotates the wheel a set number of times
   */
  private void rotateWheel() {
    if (currentRevolutions <= fullRevolutions){
      if (colorCurrentColor != colorPreviousColor){
        currentRevolutions += 0.125;
      }
      colorWheelMotor.set(vV.kColorWheelMotorSpeed);
    } else {
      colorWheelMotor.stopMotor();
      activateRotateWheel = false;
    }
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
    return colorCurrentColor;
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
   * Sets activateChangeColor to true
   */
  public void activateChangeColor(){
    activateChangeColor = true;
  }

  /**
   * Sets activateRotateWheel to true
   */
  public void activateRotateWheel(){
    activateRotateWheel = true;
  }

  //#endregion
}