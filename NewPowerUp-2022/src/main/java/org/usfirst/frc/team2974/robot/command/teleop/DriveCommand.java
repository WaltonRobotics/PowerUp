package org.usfirst.frc.team2974.robot.command.teleop;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static org.usfirst.frc.team2974.robot.Robot.driveModeChooser;
import static org.usfirst.frc.team2974.robot.Robot.drivetrain;

public class DriveCommand extends CommandBase {

    public DriveCommand() {
        addRequirements(drivetrain);

        //reset button not yet mapped
        //resetDrivetrainButton.whenPressed(() -> drivetrain.reset());
    }

    @Override
    public void execute() {
        driveModeChooser.getSelected().feed();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
