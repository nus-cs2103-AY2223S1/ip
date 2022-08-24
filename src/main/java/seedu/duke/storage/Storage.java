package seedu.duke.storage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import seedu.duke.exception.DukeException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Reused from https://www.w3schools.com/java/java_files_create.asp
    // Reused from https://www.w3schools.com/java/java_files_read.asp
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File directory = new File("/data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File myObj = new File(this.filePath);
            if (myObj.createNewFile()) {
                return tasks;
            } else {
                return readFile(myObj, tasks);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            ArrayList<Task> data = tasks.getAllTasks();
            for (int i = 0; i < data.size(); i++) {
                myWriter.write(data.get(i).toFileString() + System.getProperty("line.separator"));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<Task> readFile(File myObj, ArrayList<Task> tasks) throws DukeException {
        try {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] output = data.split(" , ", 0);
                boolean isDone = Integer.parseInt(output[1]) == 1;
                if (Objects.equals(output[0], "E")) {
                    tasks.add(new Event(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "D")) {
                    tasks.add(new Deadline(output[2], output[3], isDone));
                } else if (Objects.equals(output[0], "T")) {
                    tasks.add(new ToDo(output[2], isDone));
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("An error occurred.");
        }
        return tasks;
    }
}
