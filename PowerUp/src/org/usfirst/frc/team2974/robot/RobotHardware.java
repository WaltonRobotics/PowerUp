package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

import static org.usfirst.frc.team2974.robot.Config.HardwareChannels.*;

public class RobotHardware {
    public static final Solenoid shifter;

    public static final Talon rightMotor;
    public static final Talon leftMotor;


    static {
        shifter = new Solenoid(SOLENOID_CHANNEL);

        rightMotor = new Talon(RIGHT_MOTOR_CHANNEL);
        leftMotor = new Talon(LEFT_MOTOR_CHANNEL);
    }
}
