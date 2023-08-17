package com.tourguide.multimedia.util;

public class FileUtil {
    public static String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return "";  // No extension found
        }
        return fileName.substring(lastIndexOfDot + 1).toLowerCase();
    }
}
