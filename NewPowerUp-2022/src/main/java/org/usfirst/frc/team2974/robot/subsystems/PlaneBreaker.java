package org.usfirst.frc.team2974.robot.subsystems;

import static org.usfirst.frc.team2974.robot.RobotMap.planeBreaker;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PlaneBreaker extends SubsystemBase {

	public PlaneBreaker() {
	}

	public void moveUp() {
		planeBreaker.set(true);
	}

	public void moveDown() {
		planeBreaker.set(false);
	}
}
