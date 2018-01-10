package org.usfirst.frc.team2974.robot.util;

import org.usfirst.frc.team2974.robot.Config;

import java.io.File;
import java.util.HashMap;

public class PathLoader {
    public static final HashMap<String, Pose[]> SAVED_PATHS_HASH_MAP;

    static {
        SAVED_PATHS_HASH_MAP = new HashMap<>();

        File[] files = new File(Config.Paths.PATH_DIRECTORY).listFiles((dir, name) -> name.endsWith(".path") && dir.isFile());

        if (files != null) {
            for (File file : files) {
                HashMap<String, Pose[]> hashMap = PathHelper.retrievePosesFromFile(file.getPath());
                if (hashMap != null) {
                    SAVED_PATHS_HASH_MAP.putAll(hashMap);
                }
            }
        }
    }
}
