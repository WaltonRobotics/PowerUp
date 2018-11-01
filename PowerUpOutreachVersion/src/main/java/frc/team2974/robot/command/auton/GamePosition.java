package frc.team2974.robot.command.auton;

import static frc.team2974.robot.Config.Path.Center.C0;
import static frc.team2974.robot.Config.Path.Left.L0;
import static frc.team2974.robot.Config.Path.Right.R0;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2974.robot.util.AutonLoader;
import org.waltonrobotics.controller.Pose;

/**
 * This enum is meant to represent all possible autonomous.
 *
 * DriveCommand Station position l = left drive station c = center drive station r = right drive station <p> Field
 * position L = left R = right . = any X = remove character
 */
public enum GamePosition {
  DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_RIGHT("lRR.", L0),
  DRIVE_STATION_LEFT_SWITCH_RIGHT_SCALE_LEFT("lRL.", L0),
  DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_RIGHT("lLR.", L0),
  DRIVE_STATION_LEFT_SWITCH_LEFT_SCALE_LEFT("lLL.", L0),

  DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_RIGHT("cRR.", C0),
  DRIVE_STATION_CENTER_SWITCH_RIGHT_SCALE_LEFT("cRL.", C0),
  DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_RIGHT("cLR.", C0),
  DRIVE_STATION_CENTER_SWITCH_LEFT_SCALE_LEFT("cLL.", C0),

  DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_RIGHT("rRR.", R0),
  DRIVE_STATION_RIGHT_SWITCH_RIGHT_SCALE_LEFT("rRL.", R0),
  DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_RIGHT("rLR.", R0),
  DRIVE_STATION_RIGHT_SWITCH_LEFT_SCALE_LEFT("rLL.", R0),

  DRIVE_STATION_LEFT_SCALE_RIGHT("l.R.", L0),
  DRIVE_STATION_LEFT_SCALE_LEFT("l.L.", L0),
  DRIVE_STATION_CENTER_SCALE_RIGHT("c.R.", C0),
  DRIVE_STATION_CENTER_SCALE_LEFT("c.L.", C0),
  DRIVE_STATION_RIGHT_SCALE_RIGHT("r.R.", R0),
  DRIVE_STATION_RIGHT_SCALE_LEFT("r.L.", R0),

  //	DRIVE_STATION_LEFT_SWITCH_RIGHT("lR..", L0),
  DRIVE_STATION_LEFT_SWITCH_LEFT("lL..", L0),
  DRIVE_STATION_CENTER_SWITCH_RIGHT("cR..", C0),
  DRIVE_STATION_CENTER_SWITCH_LEFT("cL..", C0),
  DRIVE_STATION_RIGHT_SWITCH_RIGHT("rR..", R0),
//	DRIVE_STATION_RIGHT_SWITCH_LEFT("rL..", R0),

  CROSS_BASELINE_LEFT("l...", L0), CROSS_BASELINE_CENTER("c...", C0), CROSS_BASELINE_RIGHT("r...",
      R0),

  DO_NOTHING("....", C0);

  private final String position;
  private final Pose startPosition;

  GamePosition(String position, Pose startPosition) {
    this.position = position;
    this.startPosition = startPosition;
  }

  private static GamePosition getGamePosition(String string) {
    for (GamePosition gamePosition : values()) {
      if (string.matches(gamePosition.getStartPosition())) {
        return gamePosition;
      }
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
    if (startPos == 'c') {
      editData[1] = '.'; // no scale
    } else {
//			if (Character.toUpperCase(startPos) != editData[0]) {
//				editData[0] = '.';
//			}
//
//			if (Character.toUpperCase(startPos) != editData[1]) {
//				editData[1] = '.';
//			}
    }

//        editData[2] = '.'; // not needed but why not

    gameData = new String(editData);

    return getGamePosition(Character.toLowerCase(startPos) + gameData);
  }

  /**
   * Unit tests for GamePositions. -> is good.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    for (int startingPos = 0; startingPos < 3; startingPos++) {
      for (int switchPos = 0; switchPos < 2; switchPos++) {
        for (int scalePos = 0; scalePos < 2; scalePos++) {
          char start = 'c';
          if (startingPos == 0) {
            start = 'l';
          } else if (startingPos == 1) {
            start = 'r';
          }

          char switch_ = 'L';
          if (switchPos == 1) {
            switch_ = 'R';
          }

          char scale = 'L';
          if (scalePos == 1) {
            scale = 'R';
          }

          if (start == 'c') {
            scale = '.';
          } else {
            if (Character.toUpperCase(start) != switch_) {
              switch_ = '.';
            }

            if (Character.toUpperCase(start) != scale) {
              scale = '.';
            }
          }

          String string = String.valueOf(start) + switch_ + scale + '.';

          System.out.println(string + " -> " + getGamePosition(string));
        }
      }
    }
  }

  public Pose getPosition() {
    return startPosition;
  }

  public String getStartPosition() {
    return position;
  }

  public CommandGroup getCommand() {
    return AutonLoader.getAutonCommands(this);
  }
}
