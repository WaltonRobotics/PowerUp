package org.usfirst.frc.team2974.robot;

import org.waltonrobotics.controller.Point;

public final class Config {

    private Config() {}

    public static final class Hardware {
        public static final double ROBOT_WIDTH = .70485;
        public static final double DISTANCE_PER_PULSE = 0.0005652;

        public static final int LEFT_MOTOR_CHANNEL = 0;
        public static final int LEFT_ENCODER_CHANNEL1 = 2; // for first digital input
        public static final int LEFT_ENCODER_CHANNEL2 = 3; // for second digital input

        public static final int RIGHT_MOTOR_CHANNEL = 1;
        public static final int RIGHT_ENCODER_CHANNEL1 = 0;
        public static final int RIGHT_ENCODER_CHANNEL2 = 1;

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
        public static final double CROSS_BASELINE_Y = 4.2748092;

        // these points are measured from the center line
        public static final Point STARTING_POSITION_RIGHT = new Point(2.887624, 0.85725);
        public static final Point STARTING_POSITION_CENTER = new Point(0.1238, 0.85725);
        public static final Point STARTING_POSITION_LEFT = new Point(-2.887624, 0.85725);
    }
}
