package org.usfirst.frc.team2974.robot.command.teleop.driveMode;

import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

public class ArcadeDrive extends DriveMode {

    @Override
    public void feed() {
        double xSpeed = getThrottle();
        double zRotation = getTurn();

        drivetrain.setArcadeSpeeds(xSpeed, zRotation);
    }

}
