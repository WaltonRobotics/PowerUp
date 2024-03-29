package org.usfirst.frc.team2974.robot;

import static org.usfirst.frc.team2974.robot.Config.Elevator.MAXIMUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MAXIMUM_HEIGHT_COMP;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MINIMUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MINIMUM_HEIGHT_COMP;

import org.usfirst.frc.team2974.robot.Gamepad.Button;
import org.usfirst.frc.team2974.robot.Gamepad.POV;
import org.usfirst.frc.team2974.robot.lib.metadata.Pose;

/**
 * This holds many constant values for all parts of the robot. It increases efficiency and effectiveness of the code.
 */
public final class Config {

  private Config() {
  }

  public enum Robot {
    PRACTICE(0.7800, 0.000409, MINIMUM_HEIGHT, MAXIMUM_HEIGHT, 775, false, false),
    COMPETITION(0.7800, 0.0002045, MINIMUM_HEIGHT_COMP, MAXIMUM_HEIGHT_COMP, 386, true,
        true/*true*/);


    private final double robotWidth;
    private final double distancePerPulse;
    private final int minimumElevatorHeight;
    private final int maximumElevatorHeight;
    private final int inchesToNativeUnits;
    private final boolean isReversed;
    private final boolean sensorPhase;

    Robot(double robotWidth, double distancePerPulse, int minimumElevatorHeight,
        int maximumElevatorHeight, int inchesToNativeUnits, boolean isReversed,
        boolean SensorPhase) {

      this.robotWidth = robotWidth;
      this.distancePerPulse = distancePerPulse;
      this.minimumElevatorHeight = minimumElevatorHeight;
      this.maximumElevatorHeight = maximumElevatorHeight;
      this.inchesToNativeUnits = inchesToNativeUnits;
      this.isReversed = isReversed;
      sensorPhase = SensorPhase;
    }

    public int getInchesToNativeUnits() {
      return inchesToNativeUnits;
    }

    public int getMinimumElevatorHeight() {
      return minimumElevatorHeight;
    }

    public int getMaximumElevatorHeight() {
      return maximumElevatorHeight;
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

    public boolean isReversed() {
      return isReversed;
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

    public static final int LED_CHANNEL5 = 5;
    public static final int LED_CHANNEL6 = 6;

    private Hardware() {
    }
  }

  public static final class Input {

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
    public static final int OUTPUT_HALF_BUTTON = Button.LEFT_BUMPER.index();
//    public static final int INTAKE_LOW_BUTTON = Button.RIGHT_BUMPER.index();
    public static final int STOP_BUTTON = Button.RIGHT_BUMPER.index();
//    public static final int STOP_BUTTON_ALT = Button.LEFT_BUMPER.index();

    public static final int ELEVATOR_NUDGE_UP = POV.N.angle(); // pov
    public static final int ELEVATOR_NUDGE_DOWN = POV.S.angle(); // pov
    public static final int ELEVATOR_ZERO = Button._9.index();
    public static final int ELEVATOR_TOGGLE_CONTROL = Button._10.index();

    public static final int ELEVATOR_HIGH = Button._4.index();
    public static final int ELEVATOR_MEDIUM = Button._3.index();
    public static final int ELEVATOR_LOW = Button._2.index();
    public static final int ELEVATOR_SMOOTH = Button._2.index();

    private Input() {
    }
  }

  public static final class Elevator {

    public static final double TOLERANCE = 0.1;

    public static final double HIGH_HEIGHT = 68; // inches, this gets the scale
    public static final double MEDIUM_HEIGHT = 24.00; // inches, this gets the switch, exchange top, and portal
    public static final double LOW_HEIGHT = 0; // inches, the floor

    public static final int MINIMUM_HEIGHT = 1000; // in nu (native units)
    public static final int MAXIMUM_HEIGHT = 50900; // in nu

    public static final int MINIMUM_HEIGHT_COMP = 00; // 1000 in nu (native units)
    public static final int MAXIMUM_HEIGHT_COMP = 27200; //2700 -  // in nu

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

    public static final class Camera {

      public static final int DRIVER_PIPELINE = 3;
      public static final int AUTO_ALIGN_PIPELINE = 2;
      public static final int LED_OFF = 1;
      public static final int LED_ON = 3;

      public static final int WIDTH = 320; //320
      public static final int HEIGHT = 240; //240
      public static final int FPS = 30;
      public static final int DEFAULT_CAMERA_COMPRESSION_QUALITY = 20; // between 0 and 100, 100 being the max, -1 being left to Shuffleboard

      private Camera() {
      }
    }

  public static final class SmartDashboardKeys {

    public static final String CONSTANTS_KANGLE = "Constants/KAngle";
    public static final String CONSTANTS_MAX_VELOCITY = "Constants/Max Velocity";
    public static final String CONSTANTS_MAX_ACCELERATION = "Constants/Max Acceleration";
    public static final String CONSTANTS_KL = "Constants/KL";
    public static final String CONSTANTS_KV = "Constants/KV";
    public static final String CONSTANTS_KACC = "Constants/KAcc";
    public static final String CONSTANTS_KK = "Constants/KK";
    public static final String CONSTANTS_KS = "Constants/KS";
    public static final String PARKING_LINE_OFFSET = "Parking Line/Offset";
    public static final String PARKING_LINE_FOCUS_X = "Parking Line/Focus X";
    public static final String PARKING_LINE_FOCUS_Y = "Parking Line/Focus Y";
    public static final String PARKING_LINE_PERCENTAGE = "Parking Line/Percentage";
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

    public static final double VELOCITY_MAX = 4; /* 3.075548163 TESTED MAX VELOCITY*/ //3 m/s
    public static final double ACCELERATION_MAX = 3.5; //2 m/s^2
    public static final Pose R6 = new Pose(3.61036, 5.80987, 0);
    public static final Pose R15 = new Pose(4, 6.50);

    public static double ANGLE_180 = StrictMath.toRadians(180);
    public static final Pose R5 = new Pose(1.49248, 4.26720, ANGLE_180);
    public static final Pose R10 = new Pose(2.9147 /*0.83147 */, 6.40987 /*5.90987 */,
        ANGLE_180/*180*/);
    public static final Pose R13 = new Pose(0.83147, 5.93987, ANGLE_180);
    public static final Pose R17 = new Pose(2.27905, 7.47946, ANGLE_180);
    public static final Pose R18 = new Pose(2.07905, 7.47946, ANGLE_180);
    // IMPORTANT: these points are measured from the center line
    // we can do this because every point is measured from the center line.
    private static double ANGLE_45 = StrictMath.toRadians(45);
    public static final Pose R12 = new Pose(2.12905, 7.17946/*8.03946*/, ANGLE_45/*180*/);
    private static double ANGLE_90 = StrictMath.toRadians(90);
    public static final Pose R0 = new Pose(2.38333, 0.42835, ANGLE_90);
    public static final Pose R1 = new Pose(2.65000 /*2.6*/, 3.55600, ANGLE_90);
    public static final Pose R2 = new Pose(2.65000/*2.6*/, 4.64134, ANGLE_90);
    public static final Pose R4 = new Pose(2.85623, 3.55600, ANGLE_90);
    public static final Pose C0 = new Pose(0.19177, 0.42835, ANGLE_90);
    public static final Pose C1 = new Pose(1.59388, 3.17791 /*3.12791*/, ANGLE_90);
    public static final Pose C2 = new Pose(-1.59388, 3.17791 /*3.12791*/, ANGLE_90);
    public static final Pose C4 = new Pose(0.0, 0.8478 /*1.4478*/, ANGLE_90); // before pyramid
    public static final Pose C5 = new Pose(0.0249 /*0.05*/, 2.5146, ANGLE_90); // at pyramid
    public static final Pose C6 = new Pose(0.0, 1.4478 /*1.4478*/,
        ANGLE_90); // after getting the second cube. does not go as far back as when going to the pyramid
    public static final Pose C7 = new Pose(1.59388, 3.37791 /*3.12791*/, ANGLE_90); // right
    public static final Pose C8 = new Pose(-1.39388, 3.37791 /*3.12791*/, ANGLE_90); //left
    public static final Pose C9 = new Pose(0.0 /*0.05*/, 2.5146, ANGLE_90); // at pyramid
    private static double ANGLE_135 = StrictMath.toRadians(135);
    public static final Pose R3 = new Pose(2.17905/*2.12905*/, 7.17946/*8.03946*/, ANGLE_135/*180*/);
    public static final Pose R16 = new Pose(3.02905, 7.57946/*8.03946*/, ANGLE_135/*180*/);
    private static double ANGLE_140 = StrictMath.toRadians(140);
    public static final Pose R11 = new Pose(1.97905 /*1.92905 */, 7.37946/*8.03946*/, ANGLE_140/*180*/);
    private static double ANGLE_150 = StrictMath.toRadians(150);
    private static double ANGLE_240 = StrictMath.toRadians(240);
    public static final Pose R14 = new Pose(R3.getX(), R3.getY(), ANGLE_240);
    private static double ANGLE_270 = StrictMath.toRadians(270);
    public static final Pose R7 = new Pose(3.05623, 5.72840, ANGLE_270);
    public static final Pose R8 = new Pose(2.85623, 5.80987, ANGLE_270);
    public static final Pose R9 = new Pose(1.0914 /*1.0414 */, 5.02069, ANGLE_270);
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
    public static final Pose L11 = negateX(R11);
    public static final Pose L12 = negateX(R12);
    public static final Pose L13 = negateX(R13);
    public static final Pose L14 = negateX(R14);
    public static final Pose L15 = negateX(R15);
    public static final Pose L16 = negateX(R16);
    public static final Pose L17 = negateX(R17);
    public static final Pose L18 = negateX(R18);


    private Path() {
    }

    /**
     * Used for R -> L points. Easy and fast.
     *
     * @param p the point to negate its x
     * @return a new point with the x negated from p
     */
    private static Pose negateX(Pose p) {
      double angle = p.getAngle();
      // the new angle is the original angle but x is negated
      double newAngle = StrictMath.atan2(StrictMath.sin(angle), -StrictMath.cos(angle));
      if (newAngle < 0) {
        newAngle += 2 * Math.PI;
      }
      return new Pose(-p.getX(), p.getY(), newAngle);
    }
  }

  public static final class MotionConstants {

    public static final double KV = 0.194350; // 0.267
    public static final double KAcc = 0.194350; //0
    public static final double KK = 0.193466; // 0

    public static final double KS = 2;//1
    public static final double KAng = 1;//1
    public static final double KL = 2; //2

    public static final double IL = 0.00; // 0.01
    public static final double IAng = 0.005; // 0.01

    private MotionConstants() {
    }
  }
}
