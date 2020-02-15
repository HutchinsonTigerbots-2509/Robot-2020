/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {

  private static WPI_TalonSRX ClimbMotor = new WPI_TalonSRX(Constants.kClimbMotorID);
  private static WPI_TalonSRX ClimbMover = new WPI_TalonSRX(Constants.kClimbMoverID);
  private static Relay ClimbPiston = new Relay(Constants.kClimbPistonID);
  
  /**
   * Creates a new Climb.
   */
  public Climb() {
  }

  public void Creep(double speed) {
    ClimbMover.set(speed);
  }

  public void climb(double speed) {
    ClimbMotor.set(speed);
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
  }
}