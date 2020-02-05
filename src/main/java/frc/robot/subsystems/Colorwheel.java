package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Colorwheel extends SubsystemBase {
  public static Constants constant = new Constants();
  public static VictorSP colorWheelMotor = new VictorSP(0);
  // public static TalonSRX colorWheelMotor = new TalonSRX(constant.kColorWheelMotorID);
  private static boolean isMicro = true;
  /**
   * Creates a new ExampleSubsystem.
   */
  public Colorwheel() {
    

  }

  @Override
  public void periodic() {
    UpdateDashboard();
    // This method will be called once per scheduler run
  }


  public void runMotors(double speed){
    colorWheelMotor.set(speed);
  }

  public void switchIsMicro(){
    if(isMicro == false){
      isMicro = true;
    } else {
      isMicro = false;
    }
  }

  public boolean IsMicro(){
   return isMicro; 
  }

  public void UpdateDashboard(){
    SmartDashboard.putBoolean("IsMicro", isMicro);
  }

  public void RotateWheel(){
    runMotors(1);
    Timer.delay(3);
    runMotors(0);
  }
}