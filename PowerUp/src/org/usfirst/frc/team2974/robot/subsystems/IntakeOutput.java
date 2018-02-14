package org.usfirst.frc.team2974.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2974.robot.command.teleop.IntakeCommand;

import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorLeft;
import static org.usfirst.frc.team2974.robot.RobotMap.intakeMotorRight;

/**
 * The Intake/Outtake for the power cubes.
 */
public class IntakeOutput extends Subsystem {

    public IntakeOutput() {
        super();
        intakeMotorLeft.setInverted(true); // TODO: check
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }

    /**
     * Sets the motor powers.
     * @param left
     * @param right
     */
    public void setMotorPowers(double left, double right) {
        intakeMotorLeft.set(ControlMode.Velocity, left);
        intakeMotorRight.set(ControlMode.Velocity, right);
    }

    public void intake() {
        setMotorPowers(1, 1);
    }

    public void output() {
        setMotorPowers(-1, -1);
    }
}
