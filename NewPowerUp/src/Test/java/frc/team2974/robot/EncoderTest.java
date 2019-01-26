package frc.team2974.robot;

import edu.wpi.first.wpilibj.Encoder;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;

import static frc.team2974.robot.RobotMap.encoderLeft;
import static frc.team2974.robot.RobotMap.encoderRight;

public class EncoderTest {

    private Encoder encoder;
    private double distancePerPulse;

    @Before
    public  void init() {
        distancePerPulse = 0.0002045;

        encoderLeft.setDistancePerPulse(distancePerPulse);
        encoderRight.setDistancePerPulse(distancePerPulse);

        encoder = new Encoder(-1, 1);
        encoder.setDistancePerPulse(distancePerPulse);
    }

    @Test
    public void encoderTest() {

        Assert.assertEquals(distancePerPulse, encoder.getDistancePerPulse(), .005);
    }

    @Test
    public void encoderLeftTest()
    {
        Assert.assertEquals(distancePerPulse, encoderLeft.getDistancePerPulse(), .005);
    }

    @Test
    public void encoderRightTest()
    {
        Assert.assertEquals(distancePerPulse, encoderRight.getDistancePerPulse(), .005);
    }
}
