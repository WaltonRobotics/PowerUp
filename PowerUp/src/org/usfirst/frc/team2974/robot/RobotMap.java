package org.usfirst.frc.team2974.robot;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;

import static org.usfirst.frc.team2974.robot.Config.Hardware.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final Talon motorLeft;
	public static final Talon motorRight;

	public static final Encoder encoderLeft;
	public static final Encoder encoderRight;

//	public static final Compressor compressor;
//	public static final Solenoid pneumaticsShifter;

	public static final WPI_TalonSRX elevatorMotor; // changed from CANTalon

	public static final VictorSPX intakeMotorLeft;
	public static final VictorSPX intakeMotorRight;

	public static final DigitalInput elevatorLimitLower;

	// public static final DigitalInput limitIntake;

	static {
		motorLeft = new Talon(LEFT_MOTOR_CHANNEL);
		motorRight = new Talon(RIGHT_MOTOR_CHANNEL);

		encoderRight = new Encoder(new DigitalInput(RIGHT_ENCODER_CHANNEL1), new DigitalInput(RIGHT_ENCODER_CHANNEL2));
		encoderLeft = new Encoder(new DigitalInput(LEFT_ENCODER_CHANNEL1), new DigitalInput(LEFT_ENCODER_CHANNEL2));

//		compressor = new Compressor(COMPRESSOR_CHANNEL);
//		pneumaticsShifter = new Solenoid(SHIFTER_CHANNEL);

		elevatorMotor = new WPI_TalonSRX(ELEVATOR_MOTOR_CHANNEL);
		elevatorLimitLower = new DigitalInput(ELEVATOR_LIMIT_LOWER_CHANNEL);

		intakeMotorLeft = new VictorSPX(INTAKE_LEFT_MOTOR_CHANNEL);
		intakeMotorRight = new VictorSPX(INTAKE_RIGHT_MOTOR_CHANNEL);
	}
}
