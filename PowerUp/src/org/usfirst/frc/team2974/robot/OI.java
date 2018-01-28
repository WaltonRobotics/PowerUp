package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import static org.usfirst.frc.team2974.robot.Config.Input.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static final Joystick leftJoystick;
	public static final Joystick rightJoystick;
	public static final Button shiftUp;
	public static final Button shiftDown;
	public static final Button shiftUpAlt;
	public static final Button shiftDownAlt;
	
	static {
		rightJoystick = new Joystick(RIGHT_JOYSTICK_PORT);
		leftJoystick = new Joystick(LEFT_JOYSTICK_PORT);
		shiftUp = new JoystickButton(leftJoystick, SHIFT_UP_BUTTON); //Shifting buttons approved by Mr.B for Noah
		shiftDown = new JoystickButton(leftJoystick, SHIFT_DOWN_BUTTON);
		shiftUpAlt = new JoystickButton(leftJoystick, SHIFT_UP_BUTTON_ALT);
		shiftDownAlt = new JoystickButton(leftJoystick, SHIFT_DOWN_BUTTON_ALT);
	}
}
