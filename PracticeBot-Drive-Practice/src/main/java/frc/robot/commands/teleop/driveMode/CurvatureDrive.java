package frc.robot.commands.teleop.driveMode;

import edu.wpi.first.wpiutil.math.MathUtil;

import static frc.robot.Constants.DriveSettings.QUICK_STOP_ALPHA;
import static frc.robot.Constants.DriveSettings.QUICK_STOP_THRESHOLD;
import static frc.robot.OI.quickTurnButton;
import static frc.robot.Robot.drivetrain;

public class CurvatureDrive extends DriveMode {

    private double quickStopAccumulator;

    @Override
    public void feed() {
        double xSpeed = getThrottle();
        double zRotation = getTurn();

        xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
        xSpeed = applyDeadband(xSpeed);

        zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);
        zRotation = applyDeadband(zRotation);
        
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

        drivetrain.setDutyCycles(leftMotorOutput, rightMotorOutput);
    }

}
