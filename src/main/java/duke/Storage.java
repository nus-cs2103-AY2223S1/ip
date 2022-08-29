package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ListIterator;
import java.util.Scanner;


public class Storage {
    private File homeFile = new File("data");
    private String filePath;
    private File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
        homeFile.mkdirs();
        try {
            boolean result = f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load save files when Duke is initialised.
     * @return Scanner s to be read by TaskList object.
     */
    public Scanner load() {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            return s;
        } catch (FileNotFoundException e) {
            System.out.println("I couldn't find a save file");
        }
        return new Scanner("");
    }

    /**
     * Method to overwrite save files whenever task list changes.
     * @param textToWrite text to add in.
     * @throws IOException
     */
    public void writeToFile(String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Method to append to save file whenever task list changes.
     * @param textToAppend
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rebuilds the save file when requested.
     * @param iterate Feeds data line by line to be written onto save file.
     * @throws IOException
     */
    public void rebuildFile(ListIterator<Task> iterate) throws IOException {
        writeToFile("");
        while (iterate.hasNext()) {
            appendToFile(iterate.next().toSave() + System.lineSeparator());
        }
    }

}
