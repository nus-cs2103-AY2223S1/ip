package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which handles loading tasks from the text file and saving tasks.
 *
 */
public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
    }

    /**
     * Reads from file to get previously stored Tasks.
     *
     * @param taskList
     */
    public void load(TaskList taskList) {
        // Check if file exists
        if (!this.file.exists()) {
            // Check if directory exists, if not make directory
            try {
                File directory = new File(this.file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occured.");
            }
        }

        try {
            Scanner fileScanner = new Scanner(this.file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineArr = line.split("#");
                String className = lineArr[0];
                String name = lineArr[1];
                switch(className) {
                case "Event":
                    String duration = lineArr[2];
                    Event newEvent = new Event(name, duration);
                    taskList.addTask(newEvent);
                    if (lineArr[3].equals("true")) {
                        newEvent.markDone();
                    }
                    break;
                case "Deadline":
                    String deadline = lineArr[2];
                    Deadline newDeadline = new Deadline(name, deadline);
                    taskList.addTask(newDeadline);
                    if (lineArr[3].equals("true")) {
                        newDeadline.markDone();
                    }
                    break;
                case "ToDo":
                    ToDo newToDo = new ToDo(name);
                    taskList.addTask(newToDo);
                    if (lineArr[2].equals("true")) {
                        newToDo.markDone();
                    }
                    break;
                default:

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


    /**
     * Writes to the file with the current list of Tasks.
     *
     * @param taskList
     */
    public void write(TaskList taskList) {

        try {
            FileWriter writer = new FileWriter(this.file);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (int i = 0; i < taskList.size(); i++) {
                ArrayList<String> lineArray = new ArrayList<>();
                Task task = taskList.getTask(i);
                if (task instanceof Deadline) {
                    lineArray.add("Task");
                    lineArray.add(task.getName());
                    Deadline deadline = (Deadline) task;
                    lineArray.add(deadline.getDeadline());
                } else if (task instanceof Event) {
                    lineArray.add("Event");
                    Event event = (Event) task;
                    lineArray.add(task.getName());
                    lineArray.add(event.getDuration());
                } else if (task instanceof ToDo) {
                    lineArray.add("ToDo");
                    lineArray.add(task.getName());
                }
                lineArray.add(task.isDone() ? "true" : "false");
                String line = String.join("#", lineArray);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }
}
