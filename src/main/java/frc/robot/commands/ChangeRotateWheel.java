/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.VariableVault;
import frc.robot.subsystems.Colorwheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ChangeRotateWheel extends InstantCommand {
  static Colorwheel sColorWheel = RobotContainer.sColorWheel;
  static VariableVault vV = new VariableVault();
  public ChangeRotateWheel() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(sColorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // if (sColorWheel.getCurrentColor() == vV.kBlue){
    //   sColorWheel.setColorTracker(1);
    //   sColorWheel.activateRotateWheelSwitch();
    // } else if (sColorWheel.getCurrentColor() == vV.kYellow){
    //   sColorWheel.setColorTracker(2);
    //   sColorWheel.activateRotateWheelSwitch();
    // } else if (sColorWheel.getCurrentColor() == vV.kRed){
    //   sColorWheel.setColorTracker(3);
    //   sColorWheel.activateRotateWheelSwitch();
    // } else if (sColorWheel.getCurrentColor() == vV.kGreen){
    //   sColorWheel.setColorTracker(4);
    //   sColorWheel.activateRotateWheelSwitch();
    // }


    // if (sColorWheel.getCurrentColor() == vV.kRed){
    //   //   sColorWheel.setColorTracker(3);
    //   //   sColorWheel.activateRotateWheelSwitch();

    // sColorWheel.setCurrentColor(sColorWheel.getCurrentColor());

    // sColorWheel.setColorTracker(3);
    // sColorWheel.activateRotateWheelSwitch();
    // }

    // sColorWheel.initRotate();
    // sColorWheel.activateRotateWheelSwitch();

    sColorWheel.startTheMotor();
  }
}
