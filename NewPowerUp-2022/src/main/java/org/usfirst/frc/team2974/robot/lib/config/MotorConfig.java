package org.usfirst.frc.team2974.robot.lib.config;

/**
 * @author Russell Newton
 **/
public interface MotorConfig {

  int getChannel();

  boolean isInverted();

  MotorParameters getMotorParameters();

}
