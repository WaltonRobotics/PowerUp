package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick left;
	public Joystick right;
	public Button shiftUp;
	public Button shiftDown;
	public Button shiftUpAlt;
	public Button shiftDownAlt;
	
	public OI() {
		right = new Joystick(1);
		left = new Joystick(0);
		shiftUp = new JoystickButton(left, 2); //Shifting buttons approved by Mr.B for Noah
		shiftDown = new JoystickButton(left, 3);
		shiftUpAlt = new JoystickButton(left, 11);
		shiftDownAlt = new JoystickButton(left, 10);
	}
}
