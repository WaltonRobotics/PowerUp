package frc.team2974.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControlledLinearActuatorSimTest
{
    @Test
    void testMovement() // tests if the simulated robot moves
    {
        ControlledLinearActuatorSim sim = new ControlledLinearActuatorSim(0, 1, 1);

        sim.setCommandedPose(1);
        double pose = sim.update(1);
        assertEquals(1, pose, "Passed");
    }

    @Test
    void testCorrectMovement() // tests if the simulated robot 
    {
        ControlledLinearActuatorSim sim = new ControlledLinearActuatorSim(0, 5, 1);
        sim.setCommandedPose(6);
        double pose = 0;
        for (int i = 0; i < 5; i++) {
            pose = sim.update(i);
        }

        assertEquals(5, pose, "Passed");
    }

    @Test
    void testMaxResetMovement()
    {
        ControlledLinearActuatorSim sim = new ControlledLinearActuatorSim(0, 1, 1);
        sim.setCommandedPose(2);
        double pose = 0;
        for (int i = 0; i < 5; i++) {
            pose = sim.update(i);
        }

        assertEquals(1, pose, "Passed");
    }

    @Test
    void testMinResetMovement()
    {
        ControlledLinearActuatorSim sim = new ControlledLinearActuatorSim(0, 1, 1);
        sim.setCommandedPose(-1);
        double pose = 0;
        for (int i = 0; i < 5; i++) {
            pose = sim.update(i);
        }

        assertEquals(0, pose, "Passed");
    }
}
