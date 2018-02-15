package org.usfirst.frc.team2974.robot;

import org.waltonrobotics.controller.Pose;

/**
 * This holds many constant values for all parts of the robot.
 * It increases efficiency and effectiveness of the code.
 */
public final class Config {

    private Config() {}

    public static final class Hardware {
        public static final double ROBOT_WIDTH = .70485; // new robot: .99314 with bumpers
        public static final double DISTANCE_PER_PULSE = 0.0005652; // new robot:

        public static final int LEFT_MOTOR_CHANNEL = 1;
        public static final int LEFT_ENCODER_CHANNEL1 = 0; // for first digital input
        public static final int LEFT_ENCODER_CHANNEL2 = 1; // for second digital input

        public static final int RIGHT_MOTOR_CHANNEL = 0;
        public static final int RIGHT_ENCODER_CHANNEL1 = 2;
        public static final int RIGHT_ENCODER_CHANNEL2 = 3;

        public static final int SHIFTER_CHANNEL = 0;

        public static final int INTAKE_LEFT_MOTOR_CHANNEL = -1;
        public static final int INTAKE_RIGHT_MOTOR_CHANNEL = -1;

        public static final int ELEVATOR_LEFT_MOTOR_CHANNEL = -1;
        public static final int ELEVATOR_RIGHT_MOTOR_CHANNEL = -1;
    }

    public static final class Input {
        public static final int RIGHT_JOYSTICK_PORT = 1;
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int GAMEPAD_PORT = 2;

        public static final int SHIFT_UP_BUTTON = 2;
        public static final int SHIFT_UP_BUTTON_ALT = 11;

        public static final int SHIFT_DOWN_BUTTON = 3;
        public static final int SHIFT_DOWN_BUTTON_ALT = 10;

        public static final int INTAKE_BUTTON = Gamepad.Button.RIGHT_TRIGGER.index();
        public static final int OUTPUT_BUTTON = Gamepad.Button.LEFT_TRIGGER.index();
    }

    public static final class Elevator {
        private static final double FEET_TO_METERS = .3048; // ease of use for feet values

        public static final double SCALE_INITIAL_HEIGHT = 5 * FEET_TO_METERS;
        public static final double SCALE_MAX_HEIGHT = 6 * FEET_TO_METERS;

        public static final double SWITCH_HEIGHT = (1 + 6.75 / 12) * FEET_TO_METERS;

//        public static final double BOTTOM =
//        public static final double TOP =

    }

    public static final class Intake {

    }

    public static final class Path {
        public static final String PATH_DIRECTORY = "./paths/";
        public static final String NETWORKTABLE = "SmartDashboard";
        public static final double CROSS_BASELINE_Y = 4.2748092;

        public static final double VELOCITY_MAX = 2;
        public static final double ACCELERATION_MAX = 1;

        // IMPORTANT: these points are measured from the center line
        public static final Pose R0 = new Pose(2.38333, 0.42835, 90);
        public static final Pose R1 = new Pose(3.61036, 3.55600, 90);
        public static final Pose R2 = new Pose(3.61036, 6.64134, 90);
        public static final Pose R3 = new Pose(2.28905, 8.03946, 180);
        public static final Pose R4 = new Pose(2.85623, 3.55600, 90);
        public static final Pose R5 = new Pose(1.79248, 4.26720, 180);
        public static final Pose R6 = new Pose(3.61036, 5.80987, 0);
        public static final Pose R7 = new Pose(2.85623, 4.97840, 270); // FIXME(?): does 270 have to be -90?
        public static final Pose R8 = new Pose(2.85623, 5.80987, 270);
        public static final Pose R9 = new Pose(2.33045, 5.83509, 270);
        public static final Pose R10 = new Pose(0.83147, 5.80987, 0);

        public static final Pose C0 = new Pose(0.19177, 0.42835, 90);
        public static final Pose C1 = new Pose(1.29388, 3.12791, 90); // TODO: check the center points
        public static final Pose C2 = new Pose(1.29388, 3.12791, 90);

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
            double newAngle = Math.atan2(Math.sin(angle), -Math.cos(angle));
            if(newAngle < 0)
                newAngle += 2 * Math.PI;
            return new Pose(-p.getX(), p.getY(), Math.toDegrees(newAngle));
        }
        public static final double MIDDLE_Y_SWITCH = 3.4100;
    }	
    
    public static final class MotionConstants {
    	public static final double KV = 0.31;
    	public static final double KAcc = 0;
    	public static final double KK = 0;
    	public static final double KS = 1;
    	public static final double KAng = 0.5;
    	public static final double KL = 1;
    }
}
