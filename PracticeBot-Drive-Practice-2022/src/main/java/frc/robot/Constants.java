package frc.robot;

public final class Constants {

    public static class Hardware {

        public static final int DRIVE_LEFT_CHANNEL = 0;
        public static final int DRIVE_RIGHT_CHANNEL = 1;

        public static final int LEFT_ENCODER_CHANNEL1 = 0;
        public static final int LEFT_ENCODER_CHANNEL2 = 1;

        public static final int RIGHT_ENCODER_CHANNEL1 = 2;
        public static final int RIGHT_ENCODER_CHANNEL2 = 3;

        public static final int SHIFTER_CHANNEL = 0;

    }

    public static class DriveSettings {

        public static final double DEADBAND = 0.1;
        public static final double QUICK_STOP_THRESHOLD = 0.2;
        public static final double QUICK_STOP_ALPHA = 0.1;

    }

    public class Inputs {
        public static final int LEFT_JOYSTICK_PORT = 0;
        public static final int RIGHT_JOYSTICK_PORT = 1;
        public static final int XBOX_CONTROLLER_PORT = 3;

        public static final int SHIFT_UP_PORT = 5;
        public static final int SHIFT_DOWN_PORT = 4;
        public static final int QUICK_TURN_PORT = 2;

        public static final String JOYSTICKS_HID_KEY = "Joysticks";
        public static final String XBOX_CONTROLLER_HID_KEY = "Xbox Controller";
    }

}
