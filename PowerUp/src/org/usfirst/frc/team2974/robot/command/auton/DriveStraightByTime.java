package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2974.robot.Robot;

public class DriveStraightByTime extends Command {

	private static double pMax;
	private final double aMax;
	private final double time;
	private final double t1;
	private double t0;
	private double dtAccel;
	private State state;

	public DriveStraightByTime(double vMax, double aMax, double time) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.aMax = aMax;
		this.time = time;
		pMax = Math.min(vMax / 2,
			1); // TODO - 2 is found by the encoder rate when the robot is moving at power 1, so
		// change this as necessary
		t1 = vMax / aMax;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		state = State.ACC;
		t0 = Timer.getFPGATimestamp();
		dtAccel = t1 - t0;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		state.run(this);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return time < (Timer.getFPGATimestamp() - t0);// || state == State.END
		// ;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.setSpeeds(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}

	private enum State {
		ACC {
			@Override // For accelerating portion of movement - moves to next
			// state when done
			public void run(DriveStraightByTime d) {
				if ((d.time / 2) < (Timer.getFPGATimestamp() - d.t0)) {
					d.state = State.DEC;
					return;
				} else if (Timer.getFPGATimestamp() >= d.t1) {
					d.state = State.CONST;
					return;
				}

				double power = (Timer.getFPGATimestamp() - d.t0) / d.dtAccel;
				Robot.drivetrain.setSpeeds(power, power);
			}
		},
		CONST {
			@Override // Constant velocity portion of motion
			public void run(DriveStraightByTime d) {
				if ((d.time - Timer.getFPGATimestamp()) <= d.dtAccel) {
					d.state = State.DEC;
					return;
				}

				Robot.drivetrain.setSpeeds(pMax, pMax);
			}
		},
		DEC {
			@Override // Decelerating portion of motion
			public void run(DriveStraightByTime d) {
				if (d.time < (Timer.getFPGATimestamp() - d.t0)) {
					d.state = END;
					return;
				}

				double power = (d.time - Timer.getFPGATimestamp()) / d.dtAccel;
				Robot.drivetrain.setSpeeds(power, power);
			}
		},
		END {
			@Override // Sets speed to 0 and ends program
			public void run(DriveStraightByTime d) {
				Robot.drivetrain.setSpeeds(0, 0);
				d.end();
			}
		};

		public void run(DriveStraightByTime d) {
		}
	}
}
