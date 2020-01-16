/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class VariableVault {

    /* ID NUMBERS */
    // Joystick
    public static int kOpStickID = 0;
    // Drivetrain
    public static int kLeftFrontID = 0;
    public static int kLeftRearID = 1;
    public static int kRightFrontID = 2;
    public static int kRightRearID = 3;
    //Intake
    public static int kIntakeMotor0ID = 9; //NOT CORRECT - DJ
    public static int kIntakeMotor1ID =8; //NOT CORRECT - DJ
    // Colorwheel
    public final Port kColorSensorID = Port.kOnboard;
    public final int kcolorWheelMotorID = 4; 
    // Colors
    public static Color kBlue = ColorMatch.makeColor(0.143, 0.427, 0.429); // when red is under our sensor, blue is under theirs
    public static Color kYellow = ColorMatch.makeColor(0.361, 0.524, 0.113); // when green is under our sensor, yellow is under theirs
    public static Color kGreen = ColorMatch.makeColor(0.197, 0.561, 0.240); // when yellow is under our sensor, green is under theirs
    public static Color kRed = ColorMatch.makeColor(0.561, 0.232, 0.114); // when blue is under our sensor, red is under theirs


    /* Vision Constants */

    // Network
    public static String kLimelightIP = "10.25.9.11";
    public static String kLimelightNetworkID = "limelight";

    // Settings
    public static int kLimelightLED = 3; // Sets LED. 0 = Set by Pipline, 1 = Force off, 2 = Force blink, 3 = Force on
    public static int kLimelightMode = 0; // Sets camera mode. 0 = Vision processor, 1 = Driver Camera
    public static int kLimelightStream = 0; // Sets streaming mode. 0 = Side-by-Side, 1 = PiP main, 2 = PiP secondary
    public static int kLimelightStartingPipeline = 2; // The default pipeline to stream
    
    // Table IDs (for getting values from the Network Table)
    public static String kLimelightLatencyID = "tl"; // Pipeline latency in milliseconds
    public static String kLimelightTargetID = "tv"; // Whether or not a valid target is found (0 or 1)
    public static String kLimelightTargetXID = "tx"; // Horizontal offset from crosshair to target (+/- 27 degrees)
    public static String kLimelightTargetYID = "ty"; // Vertical offset from crosshair to target (+/- 20.5 degrees)
    public static String kLimelightTargetAreaID = "ta"; // Target area (0-100 % of image)
    public static String kLimelightTargetSkewID = "ts"; // Target skew/rotation (-90 to 0 degrees)
    public static String kLimelightTargetVertID = "tvert"; // Vertical sidelength of bounding box (0-320 pixels)
    public static String kLimelightTargetHorID = "thor"; // Horizontal sidelength of bounding box (0-320 pixels)


}
