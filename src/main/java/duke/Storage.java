package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * Handles file state.
 */
public class Storage {
    private static Map<String, Storage> fileStates = new HashMap<>();
    private String contents = null;
    private String fileName;

    private Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets a singleton object that manages a particular file.
     * @param fileName File name to identify file.
     * @return Object managing the file.
     */
    public static Storage getFileState(String fileName) {
        if (!fileStates.containsKey(fileName)) {
            fileStates.put(fileName, new Storage(fileName));
        }
        return fileStates.get(fileName);
    }

    /*public String getContents() {
        if (contents == null) {
            try {
                File f = new File(fileName);
                Scanner s = new Scanner(f);
                StringBuilder sb = new StringBuilder();
                boolean isFirst = true;
                while (s.hasNext()) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        sb.append('\n');
                    }
                    sb.append(s.nextLine());
                }
                contents = sb.toString();
            } catch (FileNotFoundException ex) {
                // file not found or error
                contents = "";
            }
        }
        return contents;
    }*/

    ///**
    // * Saves the string to the file on disk
    // * @param str string to save
    // */
    /*public void saveContents(String str) {
        contents = str;
        // write to file TODO
    }*/

    private static String toBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_16));
    }

    private static String fromBase64(String input) {
        return StandardCharsets.UTF_16.decode(ByteBuffer.wrap(Base64.getDecoder().decode(input))).toString();
    }

    /**
     * Gets lines from file as a String[][].
     * @return Array of String[], each storing comma-separated parts of a line.
     */
    public String[][] getLines() {
        ArrayList<String[]> lines = new ArrayList<>();
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                ArrayList<String> curLine = new ArrayList<>();
                for (String str : sc.nextLine().split(",")) {
                    curLine.add(fromBase64(str));
                }
                lines.add(curLine.toArray(new String[]{}));
            }
            return (String[][]) lines.toArray(new String[][]{});
        } catch (FileNotFoundException ex) {
            // file not found or error
            return new String[][] {};
        }
    }

    /**
     * Saves the string to the file on disk.
     * @param strings Strings to save.
     */
    public void saveLines(String[][] strings) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            for (int i = 0; i < strings.length; i++) {
                if (i > 0) {
                    writer.append('\n');
                }
                StringBuilder builder = new StringBuilder();
                for (String s : strings[i]) {
                    builder.append(',').append(toBase64(s));
                }
                writer.append(builder.substring(1));
            }
            writer.close();
        } catch (IOException ex) {
            Ui.messagePrint("(>.<') I was unable to record your tasks...");
            ex.printStackTrace();
        }
    }
}
