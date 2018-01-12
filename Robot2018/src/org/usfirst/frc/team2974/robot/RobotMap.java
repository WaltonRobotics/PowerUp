package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static Talon left;
	public static Talon right;
	
	public static Encoder encoderLeft;
	public static Encoder encoderRight;
	
	public static void init() {
		left = new Talon(0);
		right = new Talon(1);
		
		encoderRight = new Encoder(new DigitalInput(0), new DigitalInput(1));
		encoderLeft = new Encoder(new DigitalInput(2), new DigitalInput(3));
	}
}
