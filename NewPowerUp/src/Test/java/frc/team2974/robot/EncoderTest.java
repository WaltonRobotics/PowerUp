package frc.team2974.robot;

import edu.wpi.first.wpilibj.Encoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class EncoderTest {
    private Encoder encoder;
    //private Drivetrain drivetrain;
    private double distancePerPulse;

    @Before
    public void init() {
        distancePerPulse = 0.0002045;
        encoder = new Encoder(-1, 1);
        encoder.setDistancePerPulse(distancePerPulse);
        //drivetrain = new Drivetrain();
    }

    @Test
    public void encoderTest() {
        Assert.assertEquals(distancePerPulse, encoder.getDistancePerPulse(), .005);
    }
}
