package frc.team2974.robot;

import edu.wpi.first.wpilibj.Encoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static frc.team2974.robot.RobotMap.encoderLeft;
import static frc.team2974.robot.RobotMap.encoderRight;

public class EncoderTest {
    private Encoder encoder;
    //private Drivetrain drivetrain;
    private double distancePerPulse;

    @BeforeAll
    public void init() {
        distancePerPulse = 0.0002045;

        encoderLeft.setDistancePerPulse(distancePerPulse);
        encoderRight.setDistancePerPulse(distancePerPulse);

        encoder = new Encoder(-1, 1);
        encoder.setDistancePerPulse(distancePerPulse);
        //drivetrain = new Drivetrain();
    }

    @Test
    public void encoderTest() {

        Assertions.assertEquals(distancePerPulse, encoder.getDistancePerPulse(), .005);
    }

    @Test
    public void encoderLefttTest()
    {
        Assertions.assertEquals(distancePerPulse, encoderLeft.getDistancePerPulse(), .005);
    }
}
