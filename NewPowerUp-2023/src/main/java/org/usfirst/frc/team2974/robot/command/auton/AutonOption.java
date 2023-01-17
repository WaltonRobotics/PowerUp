//package org.usfirst.frc.team2974.robot.command.auton;
//
//import edu.wpi.first.wpilibj2.command.CommandBase;
//
//public abstract class AutonOption extends CommandBase {
//
//	private boolean isOptionSelected;
//
//	protected AutonOption() {
//
//		isOptionSelected = false;
//	}
//
//	public abstract AutonOption right();
//
//	public abstract AutonOption left();
//
//	public abstract AutonOption center();
//
//	@Override
//	public final void initialize() {
//		super.initialize();
//
//		if (!isOptionSelected) {
//			throw new RuntimeException(
//				"Left or Right was not called when the command was initialized! This is a programmer error.");
//		}
//	}
//
//	public void setOptionSelected(boolean optionSelected) {
//		isOptionSelected = optionSelected;
//	}
//}
