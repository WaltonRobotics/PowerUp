package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.ACCELERATION;
import static org.usfirst.frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.*;

import edu.wpi.first.wpilibj.command.WaitCommand;
import org.waltonrobotics.controller.Pose;

/**
 * Drives to scale from the left or the right starting position.
 */
public class DriveToScale extends AutonOption {


    public DriveToScale left() {
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, L0, L1, L2, L3));
//		addSequential(new ElevatorTarget(HIGH_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 0.1, 0.1, L3, L11));
//		addSequential(new WaitCommand(1));
//		addSequential(new DropCube());
//		addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(true, 0.1, 0.1, L11, L3));
//
//		setOptionSelected(true);
//		return this;

        return driveToScale(new Pose[]{L0, L1, L2, L3}, L11);
    }

    @Override
    public AutonOption center() {
        return this;
    }

    public DriveToScale right() {
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, R0, R1, R2, R3));
//		addSequential(new ElevatorTarget(HIGH_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(false, 0.1, 0.1, R3, R11));
//		addSequential(new WaitCommand(1));
//		addSequential(new DropCube());
//		addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(true, 0.1, 0.1, R11, R3));
//
//		setOptionSelected(true);
//		return this;

        return driveToScale(new Pose[]{R0, R1, R2, R3}, R11);
    }

    public DriveToScale driveToScale(Pose[] toScale, Pose forwardPoint) {
        addSequential(SimpleSpline.pathFromPosesWithAngle(false, toScale));
        addSequential(new ElevatorTarget(HIGH_HEIGHT));
        addSequential(SimpleSpline
                .pathFromPosesWithAngleAndScale(VELOCITY_MAX / 3, ACCELERATION_MAX, false, 0.1, 0.1, toScale[toScale.length - 1],
                        forwardPoint));
        addSequential(new WaitCommand(1));
        addSequential(new DropCube());
        addSequential(SimpleSpline.pathFromPosesWithAngleAndScale(VELOCITY_MAX / 3, ACCELERATION_MAX, true, 0.1, 0.1, forwardPoint,
                toScale[toScale.length - 1]));

        setOptionSelected(true);

        return this;
    }
}
