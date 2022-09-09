package org.usfirst.frc.team2974.robot.command.teleop.driveMode;

import edu.wpi.first.math.MathUtil;

import static org.usfirst.frc.team2974.robot.Config.MotionConstants.DriveSettings.QUICK_STOP_ALPHA;
import static org.usfirst.frc.team2974.robot.Config.MotionConstants.DriveSettings.QUICK_STOP_THRESHOLD;
import static org.usfirst.frc.team2974.robot.OI.quickTurnButton;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

public class CurvatureDrive extends DriveMode {

    private double quickStopAccumulator;

    @Override
    public void feed() {
        double xSpeed = getThrottle();
        double zRotation = getTurn();

        xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
        zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);


        double angularPower;
        boolean overPower;

        if (quickTurnButton.get()) {
            if (Math.abs(xSpeed) < QUICK_STOP_THRESHOLD) {
                quickStopAccumulator = (1 - QUICK_STOP_ALPHA) * quickStopAccumulator
                        + QUICK_STOP_ALPHA * MathUtil.clamp(zRotation, -1.0, 1.0) * 2;
            }
            overPower = true;
            angularPower = zRotation;
        } else {
            overPower = false;
            angularPower = Math.abs(xSpeed) * zRotation - quickStopAccumulator;

            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        double leftMotorOutput = xSpeed + angularPower;
        double rightMotorOutput = xSpeed - angularPower;

        // If rotation is overpowered, reduce both outputs to within acceptable range
        if (overPower) {
            if (leftMotorOutput > 1.0) {
                rightMotorOutput -= leftMotorOutput - 1.0;
                leftMotorOutput = 1.0;
            } else if (rightMotorOutput > 1.0) {
                leftMotorOutput -= rightMotorOutput - 1.0;
                rightMotorOutput = 1.0;
            } else if (leftMotorOutput < -1.0) {
                rightMotorOutput -= leftMotorOutput + 1.0;
                leftMotorOutput = -1.0;
            } else if (rightMotorOutput < -1.0) {
                leftMotorOutput -= rightMotorOutput + 1.0;
                rightMotorOutput = -1.0;
            }
        }

        // Normalize the wheel speeds
        double maxMagnitude = Math.max(Math.abs(leftMotorOutput), Math.abs(rightMotorOutput));
        if (maxMagnitude > 1.0) {
            leftMotorOutput /= maxMagnitude;
            rightMotorOutput /= maxMagnitude;
        }

        drivetrain.setSpeeds(leftMotorOutput, rightMotorOutput);
    }

}