package frc.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Hardware.*;
import static frc.robot.Robot.drivetrain;

public class Drivetrain extends SubsystemBase {

    private final Talon leftWheels = new Talon(DRIVE_LEFT_CHANNEL);
    private final Talon rightWheels = new Talon(DRIVE_RIGHT_CHANNEL);

    private final Encoder leftEncoder = new Encoder(new DigitalInput(LEFT_ENCODER_CHANNEL1), new DigitalInput(LEFT_ENCODER_CHANNEL2));
    private final Encoder rightEncoder = new Encoder(new DigitalInput(RIGHT_ENCODER_CHANNEL1), new DigitalInput(RIGHT_ENCODER_CHANNEL2));

    private final Compressor compressor = new Compressor();
    private final Solenoid pneumaticsShifter = new Solenoid(SHIFTER_CHANNEL);

    public Drivetrain() {
        rightWheels.setInverted(true);
        rightEncoder.setReverseDirection(true);

        setDistancePerPulse();
        resetEncoders();

        compressor.stop();
    }

    public double getDistancePerPulse() {
        return 0.00038963112;
    }

    public double leftMetersTravelled() {
        return leftEncoder.getDistance();
    }

    public double rightMetersTravelled() {
        return rightEncoder.getDistance();
    }

    public void setDistancePerPulse() {
        leftEncoder.setDistancePerPulse(getDistancePerPulse());
        rightEncoder.setDistancePerPulse(getDistancePerPulse());
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void shiftDown() {
        pneumaticsShifter.set(true);
    }

    public void shiftUp() {
        pneumaticsShifter.set(false);
    }

    public boolean isShiftDown() {
        return pneumaticsShifter.get();
    }

    public void setDutyCycles(double leftDutyCycle, double rightDutyCycle) {
        leftWheels.set(leftDutyCycle);
        rightWheels.set(rightDutyCycle);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Is shift down", isShiftDown());
        SmartDashboard.putNumber("Left meters", drivetrain.leftMetersTravelled());
        SmartDashboard.putNumber("Right meters", drivetrain.rightMetersTravelled());
    }
}
