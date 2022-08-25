package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.saveTaskAsString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadSaveData() throws DukeException, IOException {

        ArrayList<Task> loadedTasks = new ArrayList<>();
        if (!this.file.exists()) {
            System.out.println("No existing save file found. Creating 'duke.txt' for you!");
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException err) {
                System.out.println("Problem trying to create directory!");
            }
        } else {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String taskString = scan.nextLine();

                // split and organise task data
                String[] taskSplit = taskString.split(" \\| ");
                String taskType = taskSplit[0];
                boolean isTaskDone = taskSplit[1].equals("1");
                String taskDescription = taskSplit[2];

                Task task;
                switch (taskType) {
                    case "T": {
                        task = new ToDo(taskDescription);
                        break;
                    }
                    case "D": {
                        String taskDate = taskSplit[3];
                        task = new Deadline(taskDescription, taskDate);
                        break;
                    }
                    case "E": {
                        String taskDate = taskSplit[3];
                        task = new Event(taskDescription, taskDate);
                        break;
                    }
                    default:
                        throw new DukeException("Task type is not supported!");
                }
                if (isTaskDone) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
        }
            return loadedTasks;
    }

}
