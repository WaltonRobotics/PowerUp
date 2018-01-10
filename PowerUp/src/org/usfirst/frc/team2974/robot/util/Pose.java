package org.usfirst.frc.team2974.robot.util;

public class Pose {
    private final double x;
    private final double y;
    private final double angle;

    public Pose(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }
}
