package frc.team2974.robot.command.auton;

import static frc.team2974.robot.Config.Path.Center.C0;
import static frc.team2974.robot.Config.Path.Center.C1;
import static frc.team2974.robot.Config.Path.Center.C2;
import static frc.team2974.robot.Config.Path.Left.L0;
import static frc.team2974.robot.Config.Path.Right.R0;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team2974.robot.Robot;
import org.waltonrobotics.command.SimpleLine;
import org.waltonrobotics.command.SimpleSpline;

/**
 * Command for crossing the baseline. Usage is: new CrossBaseline().left();
 */
public class CrossBaseline extends CommandGroup {

  private boolean isOptionSelected;

  /**
   * Follow up your constructor with a call to either left, right, or center to make this command work correctly.
   */
  public CrossBaseline() {
    isOptionSelected = false;

    // add sequential later.
  }

  /**
   * Called when on the left side of the field.
   *
   * @return this
   */
  public CrossBaseline left(double yMovement) {
    isOptionSelected = true;

    // drive forward x meters

    addSequential(SimpleLine.lineWithDistance(L0, yMovement));
    return this;
  }

  /**
   * Called when on the right side of the field.
   *
   * @return this
   */
  public CrossBaseline right(double yMovement) {
    isOptionSelected = true;

    // drive forward x meters

    addSequential(SimpleLine.lineWithDistance(R0, yMovement));
    return this;
  }

  /**
   * Called when on the center of the field.
   *
   * @return this
   */
  public CrossBaseline center() {
    isOptionSelected = true;

    // either go left or right, depends on switch position
    if (Robot.getSwitchPosition() == 'R') {
      // go right
      addSequential(SimpleSpline.pathFromPosesWithAngle(false, C0, C1));
    } else {
      // go left
      addSequential(SimpleSpline.pathFromPosesWithAngle(false, C0, C2));
    }

    return this; // ease of use :) <--- smiley face :)
  }

  @Override
  protected void initialize() {
    super.initialize();

    if (!isOptionSelected) {
      throw new RuntimeException(
          "Left or Right was not called when the command was initialized! This is a programmer error.");
    }
  }
}
