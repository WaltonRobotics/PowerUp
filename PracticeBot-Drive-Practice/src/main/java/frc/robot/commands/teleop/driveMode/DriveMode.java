package frc.robot.commands.teleop.driveMode;

import edu.wpi.first.wpilibj.GenericHID;

import static frc.robot.Constants.DriveSettings.DEADBAND;
import static frc.robot.Constants.Inputs.JOYSTICKS_HID_KEY;
import static frc.robot.Constants.Inputs.XBOX_CONTROLLER_HID_KEY;
import static frc.robot.OI.*;

public abstract class DriveMode {

    public abstract void feed();

    /**
     * Note that by default the areas outside the deadband are not scaled from 0 to -1 / 1.
     */
    double applyDeadband(double value) {
        return Math.abs(value) > DEADBAND ? value : 0;
    }

    double getLeftJoystickY() {
        return -leftJoystick.getY();
    }

    double getRightJoystickY() {
        return -rightJoystick.getY();
    }

    /* The following methods are for drive modes with separated turn and throttle commands (i.e. Curvature/Arcade). */

    /**
     * The left joystick is used for throttle.
     */
    double getThrottle() {
        return getLeftJoystickY();
    }

    /**
     * The right joystick is used for turning.
     */
    double getTurn() {
        return rightJoystick.getX();
    }

}
