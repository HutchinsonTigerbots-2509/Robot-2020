/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.VariableVault;

public class Intake extends SubsystemBase {
  
  public final WPI_TalonSRX IntakeMotor0 = new WPI_TalonSRX(VariableVault.kIntakeMotor0ID);
  public final WPI_TalonSRX IntakeMotor1 = new WPI_TalonSRX(VariableVault.kIntakeMotor1ID);

  public final SpeedControllerGroup Right = new SpeedControllerGroup(IntakeMotor0, IntakeMotor1);

  public Intake() {
  }

  @Override
  public void periodic() {
  }

  public void IntakeControl(Joystick stick){
    if(stick.getRawAxis(3) > 0.2){
      IntakeMotor0.set(ControlMode.PercentOutput, stick.getRawAxis(3));
      IntakeMotor1.set(ControlMode.PercentOutput, -stick.getRawAxis(3));
    }else if(stick.getRawAxis(4) > 0.2){
      IntakeMotor0.set(ControlMode.PercentOutput, -stick.getRawAxis(3));
      IntakeMotor1.set(ControlMode.PercentOutput, stick.getRawAxis(3));
    }else{
      IntakeMotor0.set(ControlMode.PercentOutput, 0);
      IntakeMotor1.set(ControlMode.PercentOutput, 0);
    }
  }
}
