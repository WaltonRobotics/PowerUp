package org.usfirst.frc.team2974.robot;

import org.waltonrobotics.controller.Pose;

/**
 * This holds many constant values for all parts of the robot.
 * It increases efficiency and effectiveness of the code.
 */
public final class Config {

    private Config() {}

    public static final class Hardware {
        public static final double ROBOT_WIDTH = .7239; // new robot: 0.7493 between wheels , 28.5 inches between wheels
        public static final double DISTANCE_PER_PULSE = .000409; // new robot: .00041017

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
    }

    public static final class Input { // TODO: get from drive team
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int RIGHT_JOYSTICK_PORT = 1;
        public static final int GAMEPAD_PORT = 2;

        // left joystick //
        public static final int SHIFT_UP_BUTTON = 3;
        public static final int SHIFT_UP_BUTTON_ALT = 11;

        public static final int SHIFT_DOWN_BUTTON = 2;
        public static final int SHIFT_DOWN_BUTTON_ALT = 10;

        // right joystick //

        // gamepad //
        public static final int INTAKE_BUTTON = Gamepad.Button.RIGHT_TRIGGER.index();
        public static final int OUTPUT_BUTTON = Gamepad.Button.LEFT_TRIGGER.index();
        
        public static final int ELEVATOR_NUDGE_UP = 5;
        public static final int ELEVATOR_NUDGE_DOWN = 4;
        public static final int ELEVATOR_ZERO = 10;
        public static final int ELEVATOR_TOGGLE_CONTROL = 1;
    }

    public static final class Elevator {
        public static final double INCHES_TO_NU = 775; // TODO: FIXME

        public static final double SCALE_INITIAL_HEIGHT = 60; // inches
        public static final double SCALE_MAX_HEIGHT = 72; // inches

        public static final double SWITCH_HEIGHT = 18.75; // inches

        public static final int MINUMUM_HEIGHT = 1000; // in nu (native units)
        public static final int MAXIMUM_HEIGHT = 20000; // in nu

        public static final double NUDGE_DISTANCE = 1; // in inches

        public static final int CRUISE_SPEED = 1500; // native sensor units per 100 ms
        public static final int ACCELERATION = 1500; // ^^^ per second

        // motion control constants
        public static final double KF = 0.4661;
        public static final double KP = 1;
        public static final double KI = 0.001;
        public static final double KD = 0;

        public static final int TIMEOUT = 100; // in ms
    }

    public static final class IntakeOutput {
    	public static final double MAX_POWER = 0.85;
    	public static final double LOW_POWER = 0.25; // Test this value
    	public static final double HOLD_POWER = 0.1; // TEST
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
        public static final Pose C1 = new Pose(1.29388, 3.12791, 90);
        public static final Pose C2 = new Pose(-1.29388, 3.12791, 90);

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

        /**
         * Used for R -> L points. Easy and fast.
         * @param p the point to negate its x
         * @return a new point with the x negated from p
         */
        private static Pose negateX(Pose p) {
            double angle = Math.toRadians(p.getAngle());
            // the new angle is the original angle but x is negated
            double newAngle = Math.atan2(Math.sin(angle), -Math.cos(angle));
            if(newAngle < 0)
                newAngle += 2 * Math.PI;
            return new Pose(-p.getX(), p.getY(), Math.toDegrees(newAngle));
        }
    }	
    
    public static final class MotionConstants {
    	public static final double KV = .267;
    	public static final double KAcc = 0;
    	public static final double KK = 0;
    	public static final double KS = 1;
    	public static final double KAng = 0.5;
    	public static final double KL = 1;
    }
}
