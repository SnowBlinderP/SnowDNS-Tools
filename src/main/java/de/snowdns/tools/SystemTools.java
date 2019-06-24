package de.snowdns.tools;

import java.io.File;

public class SystemTools {

    public static String CPUs() {
        return Runtime.getRuntime().availableProcessors() + "";
    }
    public static String getFreeRam() {
                return Runtime.getRuntime().freeMemory() + "";
    }
    public static String getFreeDisk(){

        File[] roots = File.listRoots();
        for (File root : roots) {
            return root.getFreeSpace() + "";
        }
        return "";
    }
}
