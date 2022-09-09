package org.usfirst.frc.team2974.robot.command.teleop.driveMode;

import static org.usfirst.frc.team2974.robot.OI.driveGamepad;

public abstract class DriveMode {

    public abstract void feed();

    public double applyDeadband(double rawValue, double deadband) {
        if (Math.abs(rawValue) < deadband) {
            return 0;
        }

        return rawValue;
    }

    public double getLeftJoystickY() {
        return -driveGamepad.getLeftY();
    }

    public double getRightJoystickY() {
        return -driveGamepad.getRawAxis(3);
    }

    /* The following methods are for drive modes with separated turn and throttle commands (i.e. Curvature/Arcade). */

    /**
     * The left joystick is used for throttle.
     */
    public double getThrottle() {
        return getLeftJoystickY();
    }

    /**
     * The right joystick is used for turning.
     */
    public double getTurn() {
        return driveGamepad.getRightX();
    }

}
