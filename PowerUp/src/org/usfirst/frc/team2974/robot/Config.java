package org.usfirst.frc.team2974.robot;

public final class Config {

    private Config() {}

    public static final class Hardware {
        public static final double ROBOT_WIDTH = .70485;

        public static final int LEFT_MOTOR_CHANNEL = 1;
        public static final int LEFT_ENCODER_CHANNEL1 = 0; // for first digital input
        public static final int LEFT_ENCODER_CHANNEL2 = 1; // for second digital input

        public static final int RIGHT_MOTOR_CHANNEL = 0;
        public static final int RIGHT_ENCODER_CHANNEL1 = 2;
        public static final int RIGHT_ENCODER_CHANNEL2 = 3;

        public static final int SHIFTER_CHANNEL = 0;
    }

    public static final class Input {
        public static final int RIGHT_JOYSTICK_PORT = 1;
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int GAMEPAD_PORT = 2;

        public static final int SHIFT_UP_BUTTON = 2;
        public static final int SHIFT_UP_BUTTON_ALT = 11;

        public static final int SHIFT_DOWN_BUTTON = 3;
        public static final int SHIFT_DOWN_BUTTON_ALT = 10;
    }

    public static final class Path {
        public static final String PATH_DIRECTORY = "./paths/";
        public static final String NETWORKTABLE = "SmartDashboard";
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
