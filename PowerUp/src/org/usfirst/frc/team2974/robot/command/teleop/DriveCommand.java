package org.usfirst.frc.team2974.robot.command.teleop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team2974.robot.OI;
import org.usfirst.frc.team2974.robot.RobotHardware;
import org.usfirst.frc.team2974.robot.io.JoystickButton;

public class DriveCommand extends Command {
    private final DifferentialDrive differentialDrive;

    public DriveCommand() {
        super("Drive");
        differentialDrive = new DifferentialDrive(RobotHardware.leftMotor, RobotHardware.rightMotor);
    }

    public void shiftUp() {
        RobotHardware.shifter.set(true);
    }

    public void shiftDown() {
        RobotHardware.shifter.set(false);
    }

    @Override
    protected void execute() {
        if (OI.RIGHT_JOYSTICK.getRawButtonPressed(JoystickButton._2.getIndex()))
            shiftDown();

        else if (OI.RIGHT_JOYSTICK.getRawButtonPressed(JoystickButton._3.getIndex()))
            shiftUp();

        differentialDrive.tankDrive(OI.LEFT_JOYSTICK.getY(), OI.LEFT_JOYSTICK.getY());
    }

    public DifferentialDrive getDifferentialDrive() {
        return differentialDrive;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
