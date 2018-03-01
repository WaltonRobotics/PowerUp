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

    DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_RIGHT("lRR."), DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_LEFT("lRL."),
    DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_RIGHT("lLR."), DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_LEFT("lLL."),

    DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_RIGHT("cRR."), DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_LEFT("cRL."),
    DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_RIGHT("cLR."), DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_LEFT("cLL."),

    DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_RIGHT("rRR."), DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_LEFT("rRL."),
    DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_RIGHT("rLR."), DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_LEFT("rLL."),

    DRIVE_STATION_LEFT_SWITCH_RIGHT("lR.."), DRIVE_STATION_LEFT_SWITCH_LEFT("lL.."),
    DRIVE_STATION_CENTER_SWITCH_RIGHT("cR.."), DRIVE_STATION_CENTER_SWITCH_LEFT("cL.."),
    DRIVE_STATION_RIGHT_SWITCH_RIGHT("rR.."), DRIVE_STATION_RIGHT_SWITCH_LEFT("rL.."),

    DRIVE_STATION_LEFT_SCALE_RIGHT("l.R."), DRIVE_STATION_LEFT_SCALE_LEFT("l.L."),
    DRIVE_STATION_CENTER_SCALE_RIGHT("c.R."), DRIVE_STATION_CENTER_SCALE_LEFT("c.L."),
    DRIVE_STATION_RIGHT_SCALE_RIGHT("r.R."), DRIVE_STATION_RIGHT_SCALE_LEFT("r.L."),

    CROSS_BASELINE_LEFT("l..."), CROSS_BASELINE_CENTER("c..."), CROSS_BASELINE_RIGHT("r..."),

    DO_NOTHING("....");

    private final String position;

    GamePosition(String position) {
        this.position = position;
    }

    private static GamePosition getGamePosition(String string) {
        for (GamePosition gamePosition : values()) {
            if (string.matches(gamePosition.getPosition()))
                return gamePosition;
        }

        return DO_NOTHING;
    }

    /**
     * @param startPos the starting position of the robot, 'l', 'r' or 'c'.
     * @param gameData the match game data
     * @return the correct game position
     */
    public static GamePosition getGamePosition(char startPos, String gameData) {
        char[] editData = gameData.toCharArray();
        if(startPos == 'c') {
            editData[1] = '.'; // no scale
        } else {
            if(Character.toUpperCase(startPos) != editData[0])
                editData[0] = '.';

            if(Character.toUpperCase(startPos) != editData[1])
                editData[1] = '.';
        }

//        editData[2] = '.'; // not needed but why not

        gameData = new String(editData);

        return getGamePosition(Character.toLowerCase(startPos) + gameData);
    }

    public String getPosition() {
        return position;
    }

    public CommandGroup getCommand() {
        return AutonLoader.getAutonCommands(this);
    }

    /**
     * Unit tests for GamePositions.
     *      -> is good.
     * @param args not used
     */
    public static void main(String[] args) {
        for(int startingPos = 0; startingPos < 3; startingPos++) {
            for(int switchPos = 0; switchPos < 2; switchPos++) {
                for(int scalePos = 0; scalePos < 2; scalePos++) {
                    char start = 'c';
                    if(startingPos == 0)
                        start = 'l';
                    else if(startingPos == 1)
                        start = 'r';

                    char switch_ = 'L';
                    if(switchPos == 1)
                        switch_ = 'R';

                    char scale = 'L';
                    if(scalePos == 1)
                        scale = 'R';

                    if(start == 'c') {
                        scale = '.';
                    } else {
                        if(Character.toUpperCase(start) != switch_)
                            switch_ = '.';

                        if(Character.toUpperCase(start) != scale)
                            scale = '.';
                    }

                    String string = "" + start + switch_ + scale + ".";

                    System.out.println(string + " -> " + getGamePosition(string));
                }
            }
        }
    }
}
