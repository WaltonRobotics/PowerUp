package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class RobotHardware {
    public static final Solenoid shifter;

    public static final Talon rightMotor;
    public static final Talon leftMotor;



    static {
        shifter = new Solenoid(IO.SOLENOID_CHANNEL);

        rightMotor = new Talon(IO.RIGHT_MOTOR_CHANNEL);
        leftMotor = new Talon(IO.LEFT_MOTOR_CHANNEL);
    }
}
