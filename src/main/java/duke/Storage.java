package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();;
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File dataFile = new File(this.filePath);
        try {
            if (dataFile.exists()) {
                Scanner s = new Scanner(dataFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    char firstLetter = line.charAt(0);
                    Task task;
                    switch (firstLetter) {
                    case 'T':
                        task = Todo.fromFileRepresentation(line);
                        break;
                    case 'E':
                        task = Event.fromFileRepresentation(line);
                        break;
                    case 'D':
                        task = Deadline.fromFileRepresentation(line);
                        break;
                    default:
                        throw new DukeException("Did you wrongly modify the file?");
                    }
                    taskList.add(task);
                }
            } else {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("This should not happen... " + e.getMessage());
        }
        return taskList;
    }

    public void saveFile(TaskList taskList) throws DukeException {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : taskList) {
                fw.write(task.toFileRepresentation() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Where are you running this file? " + e.getMessage());
        }
    }
}
