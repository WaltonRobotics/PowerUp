package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.usfirst.frc.team2974.robot.util.ButtonOnce;
import org.usfirst.frc.team2974.robot.util.POVButton;

import static org.usfirst.frc.team2974.robot.Config.Input.*;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands
 * and command groups that allow control of the robot.
 */
public final class OI {

  public static final Joystick leftJoystick;
  public static final Joystick rightJoystick;
  public static final Gamepad manipulationGamepad;
  public static final Gamepad driveGamepad;

  // used with Drivetrain subsystem
//	public static final Button shiftUp;
//	public static final Button shiftDown;
//	public static final Button shiftUpAlt; // ButtonMultiple ^^^
//	public static final Button shiftDownAlt;

  //used with Elevator subsystem
  public static final Button elevatorZero;
  public static final Button elevatorNudgeUp;
  public static final Button elevatorNudgeDown;
//  public static final Button elevatorToggleControl;
  public static final Button elevatorHigh;
  public static final Button elevatorMedium;
  public static final Button elevatorLow;

  // used with IntakeOutput subsystem
  public static final Button intake;
  public static final Button output;
  public static final Button outputHalf;
  //	public static final Button intakeHalf;
  public static final Button stop;

  public static final Button quickTurnButton;

  static {
    leftJoystick = new Joystick(LEFT_JOYSTICK_PORT);
    rightJoystick = new Joystick(RIGHT_JOYSTICK_PORT);
    driveGamepad = new Gamepad(DRIVE_GAMEPAD_PORT);
    manipulationGamepad = new Gamepad(MANIPULATION_GAMEPAD_PORT);


    // Shifting buttons approved by Mr.B for Noah
//		shiftUp = new ButtonMultiple(leftJoystick, SHIFT_UP_BUTTON, SHIFT_UP_BUTTON_ALT);
//		shiftDown = new ButtonMultiple(leftJoystick, SHIFT_DOWN_BUTTON, SHIFT_DOWN_BUTTON_ALT);
//		shiftUpAlt = new JoystickButton(leftJoystick, SHIFT_UP_BUTTON_ALT); // moved to ButtonMultiple :)
//		shiftDownAlt = new JoystickButton(leftJoystick, SHIFT_DOWN_BUTTON_ALT);

    elevatorNudgeUp = new POVButton(manipulationGamepad, ELEVATOR_NUDGE_UP);
    elevatorNudgeDown = new POVButton(manipulationGamepad, ELEVATOR_NUDGE_DOWN);
    elevatorZero = new ButtonOnce(manipulationGamepad, ELEVATOR_ZERO);
//    elevatorToggleControl = new ButtonOnce(gamepad, ELEVATOR_TOGGLE_CONTROL);
    elevatorHigh = new ButtonOnce(manipulationGamepad, ELEVATOR_HIGH);
    elevatorMedium = new ButtonOnce(manipulationGamepad, ELEVATOR_MEDIUM);
    elevatorLow = new ButtonOnce(manipulationGamepad, ELEVATOR_LOW);

    intake = new JoystickButton(manipulationGamepad, INTAKE_BUTTON);
    output = new JoystickButton(manipulationGamepad, OUTPUT_BUTTON);
    outputHalf = new JoystickButton(manipulationGamepad, OUTPUT_HALF_BUTTON);
    stop = new JoystickButton(manipulationGamepad, STOP_BUTTON);
//		intakeHalf = new JoystickButton(gamepad, INTAKE_LOW_BUTTON);
    quickTurnButton = new JoystickButton(rightJoystick,2);
  }

  private OI() {
  }
}
