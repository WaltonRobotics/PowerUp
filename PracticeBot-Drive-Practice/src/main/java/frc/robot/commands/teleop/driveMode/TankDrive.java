package frc.robot.commands.teleop.driveMode;

import static frc.robot.Robot.drivetrain;

public class TankDrive extends DriveMode {

    @Override
    public void feed() {
        double leftOutput = applyDeadband(getLeftJoystickY());
        double rightOutput = applyDeadband(getRightJoystickY());

        drivetrain.setDutyCycles(leftOutput, rightOutput);
    }

}
