package org.usfirst.frc.team2974.robot.util;

import static org.usfirst.frc.team2974.robot.RobotMap.LED1;
import static org.usfirst.frc.team2974.robot.RobotMap.LED2;

public final class LEDController {

  private LEDController() {

  }

  public static void setLEDAutoAlignMode() {
    LED1.set(true);
    LED2.set(true);
  }

  public static void setLEDFoundTargetMode() {
    LED1.set(false);
    LED2.set(true);
  }

  public static void setLEDNoTargetFoundMode() {
    LED1.set(false);
    LED2.set(false);
  }
}
