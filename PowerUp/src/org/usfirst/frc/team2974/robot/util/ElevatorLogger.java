package org.usfirst.frc.team2974.robot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 */
public class ElevatorLogger {

    public static class ElevatorData {
        private double time;
        private double positionInches;
        private double positionNU;

        public ElevatorData(double time, double positionInches, double positionNU) {
            this.time = time;
            this.positionInches = positionInches;
            this.positionNU = positionNU;
        }

        public double getTime() {
            return time;
        }

        public double getPositionInches() {
            return positionInches;
        }

        public double getPositionNU() {
            return positionNU;
        }
    }

    private final LinkedList<ElevatorData> motionDataList;
    private final String filePath;

    /**
     * Call this in robotInit() before making the drivetrain
     *
     * @param filePath
     *            - Where do you want to save the logs? To save to the roboRIO, use
     *            base directory "/home/lvuser/". To save to a thumb drive, use
     *            winSCP or similar program to find the right filepath
     */
    public ElevatorLogger(String filePath) {
        motionDataList = new LinkedList<>();
        this.filePath = filePath;
    }

    /**
     * This is called in the MotionController to add MotionData to the
     * motionDataList that MotionLogger has
     *
     * @param dataAdd
     */
    public void addMotionData(ElevatorData dataAdd) {
        motionDataList.add(dataAdd);
    }

    /**
     * Call this in autonomousInit() to clear the motionDataList
     */
    public void initialize() {
        motionDataList.clear();
    }

    /**
     * Call this in disabledInit() to send the motionDataList to a .csv file.
     */
    public void writeMotionDataCSV() {
        if (motionDataList.isEmpty()) {
            return;
        }
        String fileName = new SimpleDateFormat("Elevator yyyy-MM-dd hh-mm-ss").format(new Date());
        File file = new File(filePath + fileName + ".csv");

        StringBuilder sb = new StringBuilder();
        sb.append("Time");
        sb.append(", ");
        sb.append("Position inch");
        sb.append(", ");
        sb.append("Position NU");
        sb.append('\n');

        for (ElevatorData data : motionDataList) {
            sb.append(data.getTime());
            sb.append(", ");
            sb.append(data.getPositionInches());
            sb.append(", ");
            sb.append(data.getPositionNU());
            sb.append('\n');
        }

        try(PrintWriter pw = new PrintWriter(file)) {
            System.out.println("File " + fileName + " has been made!");
            pw.write(sb.toString());
            pw.flush();
        } catch (FileNotFoundException e) {
            System.out.println("There is no file at " + file);
            e.printStackTrace();
        }

        motionDataList.clear();
    }
}
