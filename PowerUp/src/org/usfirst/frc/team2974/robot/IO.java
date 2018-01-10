package org.usfirst.frc.team2974.robot;

import edu.wpi.first.wpilibj.Joystick;

import static org.usfirst.frc.team2974.robot.Config.InputPorts.*;

public class IO {
    public static final Joystick RIGHT_JOYSTICK;
    public static final Joystick LEFT_JOYSTICK;
    public static final Joystick GAMEPAD;

    static {
        RIGHT_JOYSTICK = new Joystick(RIGHT_JOYSTICK_PORT);
        LEFT_JOYSTICK = new Joystick(LEFT_JOYSTICK_PORT);
        GAMEPAD = new Joystick(GAMEPAD_PORT);
    }
}
