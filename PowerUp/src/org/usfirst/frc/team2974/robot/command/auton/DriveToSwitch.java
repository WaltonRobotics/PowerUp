package org.usfirst.frc.team2974.robot.command.auton;

import static org.usfirst.frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static org.usfirst.frc.team2974.robot.Config.Path.L0;
import static org.usfirst.frc.team2974.robot.Config.Path.L1;
import static org.usfirst.frc.team2974.robot.Config.Path.L10;
import static org.usfirst.frc.team2974.robot.Config.Path.L4;
import static org.usfirst.frc.team2974.robot.Config.Path.L5;
import static org.usfirst.frc.team2974.robot.Config.Path.L7;
import static org.usfirst.frc.team2974.robot.Config.Path.R0;
import static org.usfirst.frc.team2974.robot.Config.Path.R1;
import static org.usfirst.frc.team2974.robot.Config.Path.R10;
import static org.usfirst.frc.team2974.robot.Config.Path.R4;
import static org.usfirst.frc.team2974.robot.Config.Path.R5;
import static org.usfirst.frc.team2974.robot.Config.Path.R7;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.usfirst.frc.team2974.robot.util.AutonUtil;

/**
 * Drives to the switch from the chosen (left(), right(), center()) starting position.
 */
public class DriveToSwitch extends AutonOption {

	public DriveToSwitch left() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L4, L5);
	}

	public DriveToSwitch right() {
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R4, R5);
	}

	public DriveToSwitch center() {
		addParallel(new ElevatorTarget(MEDIUM_HEIGHT));
		addSequential(new CrossBaseline().center()); // :)

		CommandGroup temp = new CommandGroup();
		temp.addSequential(new DropCube());
		temp.addSequential(new ElevatorTarget(LOW_HEIGHT));
		addParallel(temp);

		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch toPyramid() {
		addSequential(new CrossBaseline().backUp());

		CommandGroup temp = new CommandGroup();
		temp.addSequential(
			new WaitCommand(4) /*TODO make a sort of wait for distance motion command*/);
		temp.addSequential(new IntakeCube());
		addParallel(temp);

		addSequential(new CrossBaseline().toPyramid());

		setOptionSelected(true);
		return this;
	}

	public DriveToSwitch nextCube() {

		addSequential(new CrossBaseline().fromPyramid()); //moves back from pyramid
		addSequential(new CrossBaseline().returnToSwitch()); //splines to correct side of switch

		setOptionSelected(true);

		return this;
	}

	public DriveToSwitch startRightEndLeft() {
		//test these points
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, R0, R1, L10, L7, L5);
	}

	public DriveToSwitch startLeftEndRight() {
		//test these points
		return AutonUtil.driveToSinglePoint(this, MEDIUM_HEIGHT, L0, L1, R10, R7, R5);
	}
}
