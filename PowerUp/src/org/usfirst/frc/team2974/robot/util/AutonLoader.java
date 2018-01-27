package org.usfirst.frc.team2974.robot.util;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.command.auton.CrossBaselineCommand;
import org.usfirst.frc.team2974.robot.command.auton.DoNothingCommand;
import org.usfirst.frc.team2974.robot.command.auton.GamePosition;

/**
 * @author Evan Tichenor (evan.tichenor@gmail.com)
 * @version 1.0, 1/27/2018
 */
public class AutonLoader {

    public static CommandGroup getAutonCommands(GamePosition gamePosition) {
        switch (gamePosition) {
            case CROSS_BASELINE:
                return new CrossBaselineCommand();

            default:
            case DO_NOTHING:
                return new DoNothingCommand();

        }
    }
}
