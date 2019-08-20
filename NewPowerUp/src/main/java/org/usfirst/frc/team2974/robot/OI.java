package org.usfirst.frc.team2974.robot;

import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_HIGH;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_LOW;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_MEDIUM;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_NUDGE_DOWN;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_NUDGE_UP;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_TOGGLE_CONTROL;
import static org.usfirst.frc.team2974.robot.Config.Input.ELEVATOR_ZERO;
import static org.usfirst.frc.team2974.robot.Config.Input.GAMEPAD_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.INTAKE_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.LEFT_JOYSTICK_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.OUTPUT_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.RIGHT_JOYSTICK_PORT;
import static org.usfirst.frc.team2974.robot.Config.Input.STOP_BUTTON;
import static org.usfirst.frc.team2974.robot.Config.Input.STOP_BUTTON_ALT;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team2974.robot.command.teleop.Stop;
import org.usfirst.frc.team2974.robot.util.ButtonMultiple;
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
//	public static final Button shiftUp;
//	public static final Button shiftDown;
//	public static final Button shiftUpAlt; // ButtonMultiple ^^^
//	public static final Button shiftDownAlt;

  //used with Elevator subsystem
  public static final Button elevatorZero;
  public static final Button elevatorNudgeUp;
  public static final Button elevatorNudgeDown;
  public static final Button elevatorToggleControl;
  public static final Button elevatorHigh;
  public static final Button elevatorMedium;
  public static final Button elevatorLow;

  // used with IntakeOutput subsystem
  public static final Button intake;
  public static final Button output;
  //	public static final Button outputHalf;
//	public static final Button intakeHalf;
  public static final ButtonMultiple stop;

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
    elevatorToggleControl = new ButtonOnce(gamepad, ELEVATOR_TOGGLE_CONTROL);
    elevatorHigh = new ButtonOnce(gamepad, ELEVATOR_HIGH);
    elevatorMedium = new ButtonOnce(gamepad, ELEVATOR_MEDIUM);
    elevatorLow = new ButtonOnce(gamepad, ELEVATOR_LOW);

    intake = new JoystickButton(gamepad, INTAKE_BUTTON);
    output = new JoystickButton(gamepad, OUTPUT_BUTTON);
    stop = new ButtonMultiple(gamepad, STOP_BUTTON, STOP_BUTTON_ALT);
    stop.whenPressed(new Stop());
//		intakeHalf = new JoystickButton(gamepad, INTAKE_LOW_BUTTON);
  }

  private OI() {
  }
}
