package org.usfirst.frc.team2974.robot;

import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_HIGH;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_LOW;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_MEDIUM;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_NUDGE_DOWN;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_NUDGE_UP;
// import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_TOGGLE_CONTROL;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_ZERO;
import static org.usfirst.frc.team2974.robot.Config.Input.GAMEPAD_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.INTAKE_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.LEFT_JOYSTICK_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.OUTPUT_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.OUTPUT_HALF_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.RIGHT_JOYSTICK_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.STOP_BUTTON;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.usfirst.frc.team2974.robot.util.ButtonOnce;
import org.usfirst.frc.team2974.robot.util.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands
 * and command groups that allow control of the robot.
 */
public final class OI {

  public static final Joystick leftJoystick;
  public static final Joystick rightJoystick;
  public static final Gamepad gamepad;

  // used with Drivetrain subsystem
//	public static final Trigger shiftUp;
//	public static final Trigger shiftDown;
//	public static final Trigger shiftUpAlt; // ButtonMultiple ^^^
//	public static final Trigger shiftDownAlt;

  //used with Elevator subsystem
  public static final Trigger elevatorZero;
  public static final Trigger elevatorNudgeUp;
  public static final Trigger elevatorNudgeDown;
//  public static final Trigger elevatorToggleControl;
  public static final Trigger elevatorHigh;
  public static final Trigger elevatorMedium;
  public static final Trigger elevatorLow;

  // used with IntakeOutput subsystem
  public static final Trigger intake;
  public static final Trigger output;
  public static final Trigger outputHalf;
  //	public static final Trigger intakeHalf;
  public static final Trigger stop;
public static Object joystick;

  static {
    leftJoystick = new Joystick(LEFT_JOYSTICK_PORT);
    rightJoystick = new Joystick(RIGHT_JOYSTICK_PORT);
    gamepad = new Gamepad(GAMEPAD_PORT);

    // Shifting buttons approved by Mr.B for Noah
//		shiftUp = new ButtonMultiple(leftJoystick, SHIFT_UP_BUTTON, SHIFT_UP_BUTTON_ALT);
//		shiftDown = new ButtonMultiple(leftJoystick, SHIFT_DOWN_BUTTON, SHIFT_DOWN_BUTTON_ALT);
//		shiftUpAlt = new JoystickButton(leftJoystick, SHIFT_UP_BUTTON_ALT); // moved to ButtonMultiple :)
//		shiftDownAlt = new JoystickButton(leftJoystick, SHIFT_DOWN_BUTTON_ALT);

    elevatorNudgeUp = new POVButton(gamepad, ELEVATOR_NUDGE_UP);
    elevatorNudgeDown = new POVButton(gamepad, ELEVATOR_NUDGE_DOWN);
    elevatorZero = new ButtonOnce(gamepad, ELEVATOR_ZERO);
//    elevatorToggleControl = new ButtonOnce(gamepad, ELEVATOR_TOGGLE_CONTROL);
    elevatorHigh = new ButtonOnce(gamepad, ELEVATOR_HIGH);
    elevatorMedium = new ButtonOnce(gamepad, ELEVATOR_MEDIUM);
    elevatorLow = new ButtonOnce(gamepad, ELEVATOR_LOW);

    intake = new JoystickButton(gamepad, INTAKE_BUTTON);
    output = new JoystickButton(gamepad, OUTPUT_BUTTON);
    outputHalf = new JoystickButton(gamepad, OUTPUT_HALF_BUTTON);
    stop = new JoystickButton(gamepad, STOP_BUTTON);
//		intakeHalf = new JoystickButton(gamepad, INTAKE_LOW_BUTTON);
  }

  private OI() {
  }
}
