import frc.team2974.robot.subsystems.IntakeOutput;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class IntakeTest
{
    private IntakeOutput intakeOutput;

    @Before
    public void init()
    {
        intakeOutput = new IntakeOutput();
    }

    @Test
    public void leftMotorCurrentTest()
    {
        Assert.assertNotEquals(0, intakeOutput.getLeftMotorCurrent(), 0.0);
    }

    @Test
    public void rightMotorCurrentTest()
    {
        Assert.assertNotEquals(0, intakeOutput.getRightMotorCurrent(), 0.0);
    }
}
