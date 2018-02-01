package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.util.AutonLoader;

/**
 * This enum is meant to represent all possible autonomous.
 *
 * DriveCommand Station position
 * l = left drive station
 * c = center drive station
 * r = right drive station
 * <p>
 * Field position
 * L = left
 * R = right
 * . = any
 * X = remove character
 */
public enum GamePosition {
    // TODO: make these names smaller and better

    DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_RIGHT("lRR."), DRIVE_STATION_LEFT_LEVER_RIGHT_SCALE_LEFT("lRL."),
    DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_RIGHT("lLR."), DRIVE_STATION_LEFT_LEVER_LEFT_SCALE_LEFT("lLL."),

    DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_RIGHT("cRR."), DRIVE_STATION_CENTER_LEVER_RIGHT_SCALE_LEFT("cRL."),
    DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_RIGHT("cLR."), DRIVE_STATION_CENTER_LEVER_LEFT_SCALE_LEFT("cLL."),

    DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_RIGHT("rRR."), DRIVE_STATION_RIGHT_LEVER_RIGHT_SCALE_LEFT("rRL."),
    DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_RIGHT("rLR."), DRIVE_STATION_RIGHT_LEVER_LEFT_SCALE_LEFT("rLL."),

    DRIVE_STATION_LEFT_LEVER_RIGHT("lR.."), DRIVE_STATION_LEFT_LEVER_LEFT("lL.."),
    DRIVE_STATION_CENTER_LEVER_RIGHT("cR.."), DRIVE_STATION_CENTER_LEVER_LEFT("cL.."),
    DRIVE_STATION_RIGHT_LEVER_RIGHT("rR.."), DRIVE_STATION_RIGHT_LEVER_LEFT("rL.."),

    DRIVE_STATION_LEFT_SCALE_RIGHT("l.R."), DRIVE_STATION_LEFT_SCALE_LEFT("l.L."),
    DRIVE_STATION_CENTER_SCALE_RIGHT("c.R."), DRIVE_STATION_CENTER_SCALE_LEFT("c.L."),
    DRIVE_STATION_RIGHT_SCALE_RIGHT("r.R."), DRIVE_STATION_RIGHT_SCALE_LEFT("r.L."),

    CROSS_BASELINE_LEFT("l..."), CROSS_BASELINE_CENTER("c..."), CROSS_BASELINE_RIGHT("r..."),

    DO_NOTHING("....");

    private final CommandGroup commands;
    private final String position;

    GamePosition(String position) {
        this.position = position;

        commands = AutonLoader.getAutonCommands(this);
    }

    public static GamePosition getGamePosition(String string) {
        for (GamePosition gamePosition : values()) {
            if (string.matches(gamePosition.getPosition()))
                return gamePosition;
        }

        return DO_NOTHING;
    }

    public String getPosition() {
        return position;
    }

    public CommandGroup getCommand() {
        return commands;
    }
}
