package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filepath;
    private static final String DUKE_FILEPATH = "data";
    private static final String DUKEFILE_STR = "data/duke.txt";
    private static File dukeFile;
    private Ui ui = new Ui();

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<String> load() {
        ArrayList<String> loadedTasks = new ArrayList<>();
        File directory = new File(DUKE_FILEPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        dukeFile = new File(this.filepath);

        try {
            dukeFile.createNewFile();
            Scanner scFile = new Scanner(dukeFile);
            while (scFile.hasNextLine()) {
                String nextTask = scFile.nextLine();
                loadedTasks.add(nextTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedTasks;
    }

    public void writeToFile(String strToWrite) throws DukeException {
        try {
            FileWriter dukeWriter = new FileWriter(DUKEFILE_STR, true);
            dukeWriter.append(strToWrite + "\n");
            dukeWriter.close();
        } catch (IOException e) {
            throw new DukeException("Ohno, I can't seem to save your task to the file");
        }
    }

    public void removeFromFile(String taskToRemove) throws FileNotFoundException, DukeException {
        Integer taskNo = Integer.valueOf(taskToRemove);
        Scanner sc = new Scanner(dukeFile);
        String toReplace = "";
        int taskCounter = 0;
        while (sc.hasNextLine()) {
            String nextTask = sc.nextLine();
            taskCounter++;
            if (taskCounter != taskNo) {
                toReplace = toReplace + nextTask + "\n";
            }
        }
        try {
            FileWriter dukeWriter = new FileWriter(DUKEFILE_STR);
            dukeWriter.write(toReplace);
            dukeWriter.close();
        } catch (IOException e) {
            throw new DukeException("oops, I can't remove the task from the saved data :(");
        }
    }
}
