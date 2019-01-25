package frc.team2974.robot;

public class ControlledLinearActuatorSim
{
    private double minPose, maxPose, velocity;
    private double commandedPose, currentPose;

    public ControlledLinearActuatorSim(double minPose, double maxPose, double velocity) {
        this.minPose = minPose;
        this.maxPose = maxPose;
        this.velocity = velocity;
        commandedPose = 0;
        currentPose = 0;
    }

    public void setCommandedPose(double commandedPose) {
        this.commandedPose = commandedPose;
    }

    /**
     * moves the robot, does not pass the max and min positions
     * @param timeStep
     * @return the current position of the simulated robot
     */
    public double update(double timeStep) {
        boolean positiveError = (commandedPose - currentPose) > 0;
        double amountToMove = (velocity * timeStep) * (positiveError ? 1 : -1);
        currentPose += amountToMove;
        boolean newPositiveError = (commandedPose - currentPose) > 0;
        if (newPositiveError != positiveError) {
            currentPose = commandedPose;
        }
        if (currentPose > maxPose) {
            currentPose = maxPose;
        }
        if (currentPose < minPose) {
            currentPose = minPose;
        }
        return currentPose;
    }
}
