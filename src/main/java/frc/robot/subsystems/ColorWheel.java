/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheel extends SubsystemBase {
  private static WPI_VictorSPX ColorWheelMotor = new WPI_VictorSPX(Constants.kColorMotorID);
  private String GameData;
  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    GetGameData();
  }

  public void ColorWheelForward(){
    ColorWheelMotor.set(0.5);
  }

  public void ColorWheelReverse(){
    ColorWheelMotor.set(-0.5);
  }

  public void StopColorMotor(){
    ColorWheelMotor.set(0);
  }

  public void GetGameData(){
    GameData = DriverStation.getInstance().getGameSpecificMessage();
    if(GameData.length() > 0){
      switch (GameData.charAt(0)){
        case 'B' :
          SmartDashboard.putString("Color", "BLUE");
          break;
        case 'G' :
          SmartDashboard.putString("Color", "GREEN");
          break;
        case 'R' :
          SmartDashboard.putString("Color", "RED");
          break;
        case 'Y' :
          SmartDashboard.putString("Color", "YELLOW");
          break;
        default :
          SmartDashboard.putString("Color", "ERROR");
          break;
      }
    }
  }
}
