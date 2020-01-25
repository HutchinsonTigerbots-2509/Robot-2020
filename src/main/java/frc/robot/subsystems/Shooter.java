/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private static WPI_VictorSPX ShooterMotor1 = new WPI_VictorSPX(Constants.kShooterMotor1ID);
  private static WPI_VictorSPX ShooterMotor2 = new WPI_VictorSPX(Constants.kShooterMotor2ID);
  /**
   * Creates a new Shooter.
   */
  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ShooterForward(){
    ShooterMotor1.set(1);
    ShooterMotor2.set(1);
  }

  public void ShooterReverse(){
    ShooterMotor1.set(-1);
    ShooterMotor2.set(-1);
  }

  public void StopShooter(){
    ShooterMotor1.set(0);
    ShooterMotor2.set(0);
  }

}
