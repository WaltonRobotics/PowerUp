package org.usfirst.frc.team2974.robot.command.teleop.driveMode;

import static org.usfirst.frc.team2974.robot.OI.*;
import static org.usfirst.frc.team2974.robot.Robot.driveInputDeviceChooser;

public abstract class DriveMode {

    public abstract void feed();

    public double applyDeadband(double rawValue, double deadband) {
        if (Math.abs(rawValue) < deadband) {
            return 0;
        }

        return rawValue;
    }

    public double getLeftJoystickY() {
        if(driveInputDeviceChooser.getSelected().equals("Joysticks")){
            return -leftJoystick.getY();
        }
        else {
            return -driveGamepad.getLeftY();
        }
    }

    public double getRightJoystickY() {
        if (driveInputDeviceChooser.getSelected().equals("Joysticks")) {
            return -rightJoystick.getY();
        }
        else {
            return -driveGamepad.getRawAxis(3);
        }
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
