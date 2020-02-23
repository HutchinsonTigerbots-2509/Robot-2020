/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {

  private static WPI_TalonSRX ClimbMotor = new WPI_TalonSRX(Constants.kClimbMotorID);
  private static WPI_TalonSRX ClimbMover = new WPI_TalonSRX(Constants.kClimbMoverID);
  private static Relay ClimbPiston = new Relay(Constants.kClimbPistonID);
  private static AnalogInput ClimbLimit = new AnalogInput(Constants.kClimbLimitID);
  
  /**
   * Creates a new Climb.
   */
  public Climb() {
  }

  public void Creep(double speed) {
    ClimbMover.set(speed);
  }

  public boolean getClimbLimit(){
    if(ClimbLimit.getVoltage() > 0.25){
      return true;
    } else {
      return false;
    }
  }

  public void ClimbExtend() {
    ClimbMotor.set(-1);
  }

  public void ClimbRetract(){
    if(!getClimbLimit()){
      ClimbMotor.set(1);
    } else {
      ClimbMotor.set(0);
    }
  }

  public void StopClimb(){
    ClimbMotor.set(0);
  }

  public void ExtendClimbPiston(){
    ClimbPiston.set(Value.kForward);
  }

  public void RetractClimbPiston(){
    ClimbPiston.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Climb Limit", getClimbLimit());
    // SmartDashboard.putNumber("Climb volts", ClimbLimit.getVoltage());
  }
}
