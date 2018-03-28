package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.*;

/**
 * Drives to the scale, puts a cube in, gets another cube, drives to the switch, puts a cube in
 */
public class DriveSwitchScale extends AutonOption {

    public DriveSwitchScale left() {
        addSequential(new DriveToScale().left());

        addSequential(new ElevatorTarget(LOW_HEIGHT));
        addSequential(SimpleSpline
                .pathFromPosesWithAngle(true, L3, L10));
        addSequential(SimpleSpline
                .pathFromPosesWithAngle(false, L10, L9));

        //		addSequential(new DriveToSwitch().left()); // should also drop cube in
//		addSequential(SimpleSpline.pathFromPosesWithAngle(true, L5, L7, L8));
//		addParallel(new ElevatorTarget(LOW_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, L8, L9));
//		addSequential(new FindCube().left());
//		// TODO: gather cube too
//		addSequential(SimpleSpline.pathFromPosesWithAngle(true, L9, L10));
//		addParallel(new ElevatorTarget(HIGH_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, L10, L6, L2, L3));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new DropCube());

        setOptionSelected(true);

        return this;
    }

    @Override
    public AutonOption center() {
        return this;
    }


    public DriveSwitchScale right() {
        addSequential(new DriveToScale().right());

        addSequential(new ElevatorTarget(LOW_HEIGHT));

//		//FIXME ARRRGGGGG WHY IS POINT TURN NOT WORKING
        addSequential(SimpleSpline.pointTurn(R3, Math.toRadians(270))); //TODO make point turn

//        addSequential(SimpleSpline
//                .pathFromPosesWithAngle(true , R3, R3.offset(-1, -1, 0)));
//		addSequential(SimpleSpline
//			.pathFromPosesWithAngle(false, R10, R9));

//		addSequential(new DriveToSwitch().right()); // should also drop cube in
//		addSequential(SimpleSpline.pathFromPosesWithAngle(true, R5, R7, R8));
//		addParallel(new ElevatorTarget(LOW_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, R8, R9));
//		addSequential(new FindCube().right());
//		addSequential(SimpleSpline.pathFromPosesWithAngle(true, R9, R10));
//		addParallel(new ElevatorTarget(HIGH_HEIGHT));
//		addSequential(SimpleSpline.pathFromPosesWithAngle(false, R10, R6, R2, R3));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new DropCube());
        setOptionSelected(true);

        return this;
    }
}
