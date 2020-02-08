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
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Turret.AlignTurret;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.Conveyor;
import frc.robot.commands.RunConveyorMax;
import frc.robot.commands.ShootAll;
import frc.robot.commands.ConveyorReverse;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.RunShooterMax;
import frc.robot.subsystems.Intake;
import frc.robot.commands.ColorWheelForward;
import frc.robot.commands.ColorWheelReverse;
import frc.robot.commands.SwitchPipeline;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Declare buttons here
  private static JoystickButton AlignButton;
  private static JoystickButton RunConveyorMaxButton;
  private static JoystickButton ConveyorReverseButton;
  private static JoystickButton ShootAllButton;
  private static JoystickButton RunShooterMaxButton;
  private static JoystickButton ColorWheelForward;
  private static JoystickButton ColorWheelReverse;
  private static JoystickButton SwitchPipelineButton;

   // Subsystems - Create all subsystems here, and then pass them into Commands
  public static Drivetrain sDrivetrain = new Drivetrain();
  public static Vision sVision = new Vision();
  public static Turret sTurret = new Turret();
  public static Conveyor sConveyor = new Conveyor();
  public static Shooter sShooter = new Shooter();
  public static Intake sIntake = new Intake();
  public static ColorWheel sColorWheel = new ColorWheel();

  
   // Joysticks - Joysticks are made here
  public static Joystick OpStick = new Joystick(Constants.kOpStickID);
  public static Joystick CoOpStick = new Joystick(Constants.kCoOpStickID);

   // Commands - Create Command Objects
   // NOTE: it should be private, but if you need to reference it elsewhere, then



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
  AlignButton = new JoystickButton(CoOpStick, 1);
  AlignButton.toggleWhenPressed(new AlignTurret(sVision, sTurret));

  RunConveyorMaxButton = new JoystickButton(CoOpStick, 5);
  RunConveyorMaxButton.whileHeld(new RunConveyorMax(sConveyor));

  ConveyorReverseButton = new JoystickButton(CoOpStick, 6);
  ConveyorReverseButton.whileHeld(new ConveyorReverse(sConveyor));

  ShootAllButton = new JoystickButton(CoOpStick, 2);
  ShootAllButton.whileHeld(new ShootAll(sShooter, sConveyor));

  RunShooterMaxButton = new JoystickButton(CoOpStick, 3);
  RunShooterMaxButton.toggleWhenPressed(new RunShooterMax(sShooter));

  ColorWheelForward = new JoystickButton(OpStick, 2);
  ColorWheelForward.whileHeld(new ColorWheelForward(sColorWheel));

  ColorWheelReverse = new JoystickButton(OpStick, 4);
  ColorWheelReverse.whileHeld(new ColorWheelReverse(sColorWheel));

  SwitchPipelineButton = new JoystickButton(CoOpStick, 4);
  SwitchPipelineButton.whenPressed(new SwitchPipeline(sVision));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // NOTE: Put in an actual command
  //   return cAlignTurret;
  // }
}
