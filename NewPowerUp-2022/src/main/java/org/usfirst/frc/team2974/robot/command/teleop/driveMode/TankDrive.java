package org.usfirst.frc.team2974.robot.command.teleop.driveMode;

import static org.usfirst.frc.team2974.robot.Config.MotionConstants.DriveSettings.DEADBAND;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

public class TankDrive extends DriveMode {

    @Override
    public void feed() {
        double leftOutput = applyDeadband(getLeftJoystickY(), DEADBAND);
        double rightOutput = applyDeadband(getRightJoystickY(), DEADBAND);

        drivetrain.setSpeeds(leftOutput, rightOutput);
    }

}
