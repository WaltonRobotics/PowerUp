package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaselineCommand;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

/**
 * Used solely to get the autonomous commands from GamePosition.
 */
public class AutonLoader {

    public static CommandGroup getAutonCommands(GamePosition gamePosition) {
        switch (gamePosition) {
            case CROSS_BASELINE:
                return new CrossBaselineCommand();
            // TODO: add others
            default:
            case DO_NOTHING:
                return new DoNothingCommand();
        }
    }
}
