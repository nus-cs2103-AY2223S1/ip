package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulate the Storage of the chatbot
 *
 * @author: Jonas Png
 */

public class Storage {

    private String filePath;

    private File dataFile;

    public Storage(String filePath) {
        try {
            this.filePath = filePath;
            File folder = new File(filePath);
            folder.mkdir();
            this.dataFile = new File(filePath + "/duke.txt");
            if (!this.dataFile.exists()) {
                this.dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong in file creation");
        }
    }

    public void update(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(filePath + "/duke.txt");
            for (Task task : taskList) {
                myWriter.write(task.toStorageString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("There is something wrong with storage file!");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> memory = new ArrayList<>();
        Scanner myReader;
        try {
            myReader = new Scanner(this.dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Storage file not found at specified path!");
        }
        while (myReader.hasNextLine()) {
            String taskString = myReader.nextLine();
            String[] taskDataArray = taskString.split(" \\| "); // since | is a special character
            Task currTask = null;
            switch (taskDataArray[0]) {
            case "T":
                currTask = new ToDo(taskDataArray[2]);
                memory.add(currTask);
                break;
            case "E":
                currTask = new Event(taskDataArray[2], taskDataArray[3]);
                memory.add(currTask);
                break;
            case "D":
                currTask = new Deadline(taskDataArray[2], taskDataArray[3]);
                memory.add(currTask);
                break;
            }
            if (taskDataArray[1].equals("1")) {
                assert currTask != null;
                currTask.markAsDone();
            }
        }
        return memory;
    }
}
