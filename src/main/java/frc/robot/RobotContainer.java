// LOOK BELOW 
//
//
// LOOK HERE:
// https://github.com/wpilibsuite/allwpilib/tree/master/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/armbot
//
// LOOK ABOVE
//
//

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ChangeColorWheel;
import frc.robot.commands.ChangeRotateWheel;
import frc.robot.subsystems.Colorwheel;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  
  // The robot's subsystems and commands are defined here...
  // public final Vision sVision = new Vision();

   // Subsystems - Create all subsystems here, and then pass them into Commands
  // private final Drivetrain DT = new Drivetrain(); // hehe I Cole Rahne
  // public final Intake sIntake = new Intake(); // hehe I cole rain

  public static Colorwheel sColorWheel = new Colorwheel();
   
   // Joysticks - Joysticks are made here
  public static Joystick OpStick = new Joystick(VariableVault.kOpStickID);

  public static JoystickButton ChangeColorWheelButton;
  public static JoystickButton ChangeRotateWheelButton;

   // Commands - Create Command Objects
   // NOTE: it should be private, but if you need to reference it elsewhere, then 
  // public final OPDrive OPDrive = new OPDrive(DT, OpStick);
  public final ChangeColorWheel cChangeColorWheel = new ChangeColorWheel();
  public final ChangeRotateWheel cChangeRotateWheel = new ChangeRotateWheel();



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    ChangeColorWheelButton = new JoystickButton(OpStick, 1);
    ChangeColorWheelButton.whenPressed(new ChangeColorWheel());

    ChangeRotateWheelButton = new JoystickButton(OpStick, 2);
    ChangeRotateWheelButton.whenPressed(new ChangeRotateWheel());

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // NOTE: Put in an actual command
    // return OPDrive;
    return null;
  }
}
