package org.usfirst.frc.team2974.robot;

import org.usfirst.frc.team2974.robot.Gamepad.Button;
import org.usfirst.frc.team2974.robot.Gamepad.POV;
import org.waltonrobotics.controller.Pose;

/**
 * This holds many constant values for all parts of the robot. It increases efficiency and
 * effectiveness of the code.
 */
public final class Config {

	private Config() {
	}

	public enum Robot {
		PRACTICE(0.7800, 0.000409, false), COMPETITION(0.7800, 0.0002045, true);


		private final double robotWidth;
		private final double distancePerPulse;
		private final boolean sensorPhase;

		Robot(double robotWidth, double distancePerPulse, boolean SensorPhase) {

			this.robotWidth = robotWidth;
			this.distancePerPulse = distancePerPulse;
			sensorPhase = SensorPhase;
		}

		public double getRobotWidth() {
			return robotWidth;
		}

		public double getDistancePerPulse() {
			return distancePerPulse;
		}

		public boolean getSensorPhase() {
			return sensorPhase;
		}
	}

	public static final class Hardware {

		public static final int LEFT_MOTOR_CHANNEL = 0; // pwm
		public static final int LEFT_ENCODER_CHANNEL1 = 2; // for first digital input
		public static final int LEFT_ENCODER_CHANNEL2 = 3; // for second digital input

		public static final int RIGHT_MOTOR_CHANNEL = 1; // pwm
		public static final int RIGHT_ENCODER_CHANNEL1 = 0; // digital
		public static final int RIGHT_ENCODER_CHANNEL2 = 1; // digital

		public static final int SHIFTER_CHANNEL = 0; // pcm

		public static final int INTAKE_LEFT_MOTOR_CHANNEL = 4; // can
		public static final int INTAKE_RIGHT_MOTOR_CHANNEL = 5; // can

		public static final int INTAKE_LIMIT = 5; // digital

		public static final int ELEVATOR_MOTOR_CHANNEL = 3; // can

		public static final int ELEVATOR_LIMIT_LOWER_CHANNEL = 4; // digital

		public static final int ROBOT_IDENTIFIER_CHANNEL = 9;

		public static final int PLANE_BREAK = 1; //pcm

		private Hardware() {
		}
	}

	public static final class Input { // TODO: get from drive team

		public static final int LEFT_JOYSTICK_PORT = 0;
		public static final int RIGHT_JOYSTICK_PORT = 1;
		public static final int GAMEPAD_PORT = 2;

		// left joystick //
		public static final int SHIFT_UP_BUTTON = 3;
		public static final int SHIFT_UP_BUTTON_ALT = 6;

		public static final int SHIFT_DOWN_BUTTON = 2;
		public static final int SHIFT_DOWN_BUTTON_ALT = 7;

		public static final int SHIFT_TOGGLE_BUTTON = 1;

		// right joystick //

		// gamepad //
		public static final int INTAKE_BUTTON = Button.RIGHT_TRIGGER.index();
		public static final int OUTPUT_BUTTON = Button.LEFT_TRIGGER.index();

		public static final int ELEVATOR_NUDGE_UP = POV.N.angle(); // pov
		public static final int ELEVATOR_NUDGE_DOWN = POV.S.angle(); // pov
		public static final int ELEVATOR_ZERO = Button._9.index();
		public static final int ELEVATOR_TOGGLE_CONTROL = Button._10.index();

		public static final int ELEVATOR_HIGH = Button._4.index();
		public static final int ELEVATOR_MEDIUM = Button._3.index();
		public static final int ELEVATOR_LOW = Button._2.index();

		private Input() {
		}
	}

	public static final class Elevator {

		public static final double TOLERANCE = 0.1;
		public static final double INCHES_TO_NU = 775; // TODO: improve number to improve accuracy

		public static final double HIGH_HEIGHT = 72; // inches, this gets the scale
		public static final double MEDIUM_HEIGHT = 24.00; // inches, this gets the switch, exchange top, and portal
		public static final double LOW_HEIGHT = 0; // inches, the floor

		public static final int MINIMUM_HEIGHT = 1000; // in nu (native units)
		public static final int MAXIMUM_HEIGHT = 20000; // in nu

		public static final double NUDGE_DISTANCE = 1; // in inches

		public static final int CRUISE_SPEED = 2500; // native sensor units per 100 ms
		public static final int ACCELERATION = 2000; // ^^^ per second

		// motion control constants
		public static final double KF = 0.4661;
		public static final double KP = 1;
		public static final double KI = 0.001;
		public static final double KD = 0;

		public static final int TIMEOUT = 100; // in ms

		private Elevator() {
		}
	}

	public static final class IntakeOutput {

		public static final double MAX_POWER = 0.75;
		public static final double LOW_POWER = 0.25; // Test this value
		public static final double HOLD_POWER = 0.1; // TEST

		private IntakeOutput() {
		}
	}

	public static final class Path {

		public static final double CROSS_BASELINE_Y = 4.2748092;

		public static final double VELOCITY_MAX = 2; // m/s
		public static final double ACCELERATION_MAX = 1; // m/s^2

		// IMPORTANT: these points are measured from the center line
		public static final Pose R0 = new Pose(2.38333, 0.42835, 90);
		public static final Pose R1 = new Pose(2.60000, 3.55600, 90);
		public static final Pose R2 = new Pose(2.60000, 6.64134, 90);
		public static final Pose R3 = new Pose(2.28905, 8.03946, 180);
		public static final Pose R4 = new Pose(2.85623, 3.55600, 90);
		public static final Pose R5 = new Pose(1.79248, 4.26720, 180);
		public static final Pose R6 = new Pose(3.61036, 5.80987, 0);
		public static final Pose R7 = new Pose(2.85623, 4.97840, 270);
		public static final Pose R8 = new Pose(2.85623, 5.80987, 270);
		public static final Pose R9 = new Pose(2.33045, 5.83509, 270);
		public static final Pose R10 = new Pose(0.83147, 5.80987, 0);

		public static final Pose C0 = new Pose(0.19177, 0.42835, 90);
		public static final Pose C1 = new Pose(1.59388, 3.12791, 90);
		public static final Pose C2 = new Pose(-1.59388, 3.12791, 90);

		// we can do this because every point is measured from the center line.
		public static final Pose L0 = negateX(R0);
		public static final Pose L1 = negateX(R1);
		public static final Pose L2 = negateX(R2);
		public static final Pose L3 = negateX(R3);
		public static final Pose L4 = negateX(R4);
		public static final Pose L5 = negateX(R5);
		public static final Pose L6 = negateX(R6);
		public static final Pose L7 = negateX(R7);
		public static final Pose L8 = negateX(R8);
		public static final Pose L9 = negateX(R9);
		public static final Pose L10 = negateX(R10);

		private Path() {
		}

		/**
		 * Used for R -> L points. Easy and fast.
		 *
		 * @param p the point to negate its x
		 * @return a new point with the x negated from p
		 */
		private static Pose negateX(Pose p) {
			double angle = Math.toRadians(p.getAngle());
			// the new angle is the original angle but x is negated
			double newAngle = StrictMath.atan2(StrictMath.sin(angle), -StrictMath.cos(angle));
			if (newAngle < 0) {
				newAngle += 2 * Math.PI;
			}
			return new Pose(-p.getX(), p.getY(), Math.toDegrees(newAngle));
		}
	}

	public static final class MotionConstants {

		public static final double KV = 0.267;
		public static final double KAcc = 0;
		public static final double KK = 0;
		public static final double KS = 1;
		public static final double KAng = 1;
		public static final double KL = 2;

		public static final double IL = 0.01;
		public static final double IAng = 0.01;

		private MotionConstants() {
		}
	}
}
