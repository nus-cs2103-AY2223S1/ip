package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class that deals with loading and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The method which loads the file.
     * @return The list of tasks that were stored in the file.
     */
    public ArrayList<Task> load() {
        File tasks = new File(filePath);
        tasks.getParentFile().mkdirs();
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!tasks.createNewFile()) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(tasks.getPath()));
                    label:
                    for (String line; (line = reader.readLine()) != null; ) {
                        String[] taskInfo = line.split("[*]", 4);

                        switch (taskInfo[0]) {
                            case "T":
                                Task toDo = new ToDo(taskInfo[2]);
                                if (taskInfo[1].equals("1")) {
                                    toDo.setDone();
                                }
                                taskList.add(toDo);
                                break;
                            case "D":
                                Task deadline = new Deadline(taskInfo[2], taskInfo[3]);
                                if (taskInfo[1].equals("1")) {
                                    deadline.setDone();
                                }
                                taskList.add(deadline);
                                break;
                            case "E":
                                Task event = new Event(taskInfo[2], taskInfo[3]);
                                if (taskInfo[1].equals("1")) {
                                    event.setDone();
                                }
                                taskList.add(event);
                                break;
                            default:
                                break label;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Unable to load file!");
                }
            }
        } catch (IOException e) {
            System.out.println("Error in locating/creating the save file");
        }
        return taskList;
    }

    /**
     * Saves the tasks into a text file.
     * @param tasks The tasks that are being saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter("Data/DukeTasks.txt", false);
            writer.write(tasks.taskListToText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
