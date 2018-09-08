package frc.team2974.robot.command.teleop;

import static frc.team2974.robot.Config.Elevator.HIGH_HEIGHT;
import static frc.team2974.robot.Config.Elevator.LOW_HEIGHT;
import static frc.team2974.robot.Config.Elevator.MEDIUM_HEIGHT;
import static frc.team2974.robot.Config.Elevator.NUDGE_DISTANCE;
import static frc.team2974.robot.Config.Elevator.TOLERANCE;
import static frc.team2974.robot.OI.elevatorHigh;
import static frc.team2974.robot.OI.elevatorLow;
import static frc.team2974.robot.OI.elevatorMedium;
import static frc.team2974.robot.OI.elevatorNudgeDown;
import static frc.team2974.robot.OI.elevatorNudgeUp;
import static frc.team2974.robot.OI.elevatorToggleControl;
import static frc.team2974.robot.OI.elevatorZero;
import static frc.team2974.robot.OI.gamepad;
import static frc.team2974.robot.Robot.elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorCommand extends Command {


  public ElevatorCommand() {
    requires(elevator);
  }

  @Override
  protected void initialize() {
    elevator.disableControl();
  }

  @Override
  protected void execute() {
    if (elevator.isMotionControlled()) {
      if (elevatorNudgeUp.get() && !elevator.atTopPosition()) {
        elevator.nudge(NUDGE_DISTANCE);
      }

      if (elevatorNudgeDown.get() && !elevator.atLowerPosition()) {
        elevator.nudge(-NUDGE_DISTANCE);
      }

      if (elevatorHigh.get()) {
        elevator.setTarget(HIGH_HEIGHT);
      } else if (elevatorMedium.get()) {
        elevator.setTarget(MEDIUM_HEIGHT);
      } else if (elevatorLow.get()) {
        elevator.setTarget(LOW_HEIGHT);
      }
    } else {
      if (Math.abs(gamepad.getLeftY()) > TOLERANCE) {
        double power = -gamepad.getLeftY();

        if (power < 0) {
          power *= 0.75;
        }

        elevator.setPower(power);
      } else if (Math.abs(gamepad.getRightY()) > TOLERANCE) {
        double power = -gamepad.getRightY();

        if (power < 0) {
          power *= 0.75;
        }

        elevator.setPower(power);
      } else {
        elevator.setPower(0);
      }
    }

    if (elevatorToggleControl.get()) {
      if (elevator.isMotionControlled()) {
        elevator.disableControl();
      } else {
        elevator.enableControl();
      }
    }

    if (elevatorZero.get()) {
      elevator.startZero();
    }

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
