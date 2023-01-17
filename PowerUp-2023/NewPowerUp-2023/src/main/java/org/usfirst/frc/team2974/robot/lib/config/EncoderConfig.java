package org.usfirst.frc.team2974.robot.lib.config;

public interface EncoderConfig {

  double getDistancePerPulse();

  int getChannell1();

  int getChannell2();

  boolean isInverted();
}
