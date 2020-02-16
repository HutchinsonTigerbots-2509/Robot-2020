/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /* ID NUMBERS */
    // Joystick
    public static int kOpStickID = 0;
    public static int kCoOpStickID = 1;
    // Drivetrain
    public static int kLeftFrontID = 0;
    public static int kLeftRearID = 1;
    public static int kRightFrontID = 2;
    public static int kRightRearID = 3;
    // Climber
    public static int kClimbMotorID = 11;
    public static int kClimbMoverID = 12;
    public static int kClimbPistonID;
    public static int kClimbLimitID = 3;

    public static double kDrivetrainPGain = 0.15;
    public static double kDrivetrainIGain = 0.0;
    public static double kDrivetrainDGain = 4.0;
    public static double kDrivetrainMinVoltage = 0.4;//0.2

    //RobotThings
    public static double kWheelGap = 22;
    public static double kWheelDiameter = 6;

    //EncoderTicks
    public static double kTicksPerRevolution = 256;
    public static double kDrivetrainTicksPerRevolution = 2048 * 12.70654297; // Using Talon FX motor controllers / encoders
    public static double kDrivetrainTicksPerInch = kDrivetrainTicksPerRevolution / (kWheelDiameter * Math.PI);

    // Shooter
    public static int kTurretMotorID = 8;
    public static int kTurretLimitID = 2;
    public static int kTopConveyorMotorID = 6;
    public static int kBottomConveyorMotorID = 7;
    public static int kIntakeMotorID = 9;
    public static int kIntakeDropMotorID = 13;
    public static double kTurretMinVoltage = 0.15;
    public static int kShooterMotorID = 4;
    public static int kTopLightSensorID = 0;
    public static int kBottomLightSensorID = 1;
    public static int kColorMotorID = 10;

    public static int kShooterTicksPerRotation = 2048; // or 4096
    /* Vision Constants */

    // Network
    // public static String kLimelightIP = "10.25.9.1";
    public static String kLimelightNetworkID = "limelight";

    // Settings
    public static int kLimelightLED = 0; // Sets LED. 0 = Set by Pipline, 1 = Force off, 2 = Force blink, 3 = Force on
    public static int kLimelightMode = 0; // Sets camera mode. 0 = Vision processor, 1 = Driver Camera
    public static int kLimelightStream = 2; // Sets streaming mode. 0 = Side-by-Side, 1 = PiP main, 2 = PiP secondary
    public static int kLimelightStartingPipeline = 0; // The default pipeline to stream
    public static int kLimelightDriverPipeline = 6; // The pipeline to be used by the driver for the colorwheel
    
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
