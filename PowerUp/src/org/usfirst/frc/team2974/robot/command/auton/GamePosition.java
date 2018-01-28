package org.usfirst.frc.team2974.robot.command.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2974.robot.Config;

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
 * . = ignore
 * X = remove character
 */
public enum GamePosition {
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


    CROSS_BASELINE("c..."), DO_NOTHING("....");

    private final CommandGroup commands;
    private final String position;

    GamePosition(String position) {
        this.position = position;
//        if (!PathLoader.SAVED_PATHS_HASH_MAP.containsKey(name())) {
//            throw new RobotRuntimeException("There is no path assigned the name of " + name() + " in the paths save directory: " + Config.Paths.PATH_DIRECTORY);
//        }

        // TODO: ultimately replace with AutonLoader (or something)
        commands = null;
//        poses = PathLoader.SAVED_PATHS_HASH_MAP.get(name());
    }

    public static GamePosition getGamePosition(String string) {
        for (GamePosition gamePosition : values()) {
            if (string.matches(gamePosition.getPosition()))
                return gamePosition;
        }

        return DO_NOTHING;
    }

    public CommandGroup execute() {
        // execute its function
        // moving and placing cubes included

        return commands;
    }

    public String getPosition() {
        return position;
    }

    public CommandGroup getCommands() {
        return commands;
    }
}
