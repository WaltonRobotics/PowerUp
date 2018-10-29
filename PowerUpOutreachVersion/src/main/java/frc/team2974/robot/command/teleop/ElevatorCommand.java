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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    boolean goingDown = false;

    if (elevator.isMotionControlled()) {
      if (elevatorNudgeUp.get() && !elevator.atTopPosition()) {
        elevator.nudge(NUDGE_DISTANCE);
      } else if (elevatorNudgeDown.get() && !elevator.atLowerPosition()) {
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
      double power = 0;

      if (Math.abs(gamepad.getLeftY()) > TOLERANCE) {
        power = -gamepad.getLeftY();

        if (power < 0) {
          power *= 0.75;
        }
      } else if (Math.abs(gamepad.getRightY()) > TOLERANCE) {
        power = -gamepad.getRightY();

        if (power < 0) {
          power *= 0.75;
        }
      } else {

        power = 0;

      }

      elevator.setPower(power);

      if (elevator.atLowerPosition()) {
        power = SmartDashboard.getNumber("Bottom power", .2);
      } else if (elevator.atTopPosition()) {
        toggleMotionControl();
        elevator.setTarget(HIGH_HEIGHT);
      }

      if (power < 0 && elevator.atTopPosition()) {
        goingDown = true;
      }
    }

    if (goingDown || elevatorToggleControl.get()) {
      toggleMotionControl();
    }

    if (elevatorZero.get()) {
      elevator.startZero();
    }

  }

  public void toggleMotionControl() {
    if (elevator.isMotionControlled()) {
      elevator.disableControl();
    } else {
      elevator.enableControl();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
