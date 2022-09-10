package duke;

import javax.lang.model.type.ArrayType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    /**
     * File that contains user task information.
     */
    private File toRead;
    /**
     * Path that contains the file.
     */
    private String path;

    public Storage (String path) {
        this.path = path;
        toRead = new File(path);

        if (!toRead.exists()) {
            try {
                File directory = toRead.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                toRead.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        assert toRead.exists(): "File doesnt exist";
    }

    /**
     * Reads the file and loads the information into TaskList.
     * If file does not exist in specified location, creates the file.
     * @return List of the tasks in the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(toRead);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] info = line.split(",");
                String taskType = info[0];
                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E") : taskType;

                switch (taskType) {
                case "T":
                    loadTodo(info, tasks);
                    break;
                case "D":
                    loadDeadline(info, tasks);
                    break;
                case "E":
                    loadEvent(info, tasks);
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Oops file cannot be found!");
        } finally {
            return tasks;
        }
    }

    private void loadTodo(String[] info, ArrayList<Task> tasks) {
        Todo toAdd = new Todo(info[2]);
        if (Integer.parseInt(info[1]) == 1) {
            toAdd.setComplete();
        }
        tasks.add(toAdd);
    }
    
    private void loadDeadline(String[] info, ArrayList<Task> tasks) {
        Deadline toAdd = new Deadline(info[2]);
        String[] dateArr = info[3].split(" ");
        toAdd.setDate(LocalDate.parse(dateArr[0]));
        if(dateArr.length > 1) {
            toAdd.setTime(dateArr[1]);
            toAdd.setIsConvertedTime();
        }
        if (Integer.parseInt(info[1]) == 1) {
            toAdd.setComplete();
        }
        tasks.add(toAdd);
    }
    
    private void loadEvent(String[] info, ArrayList<Task> tasks) {
        Event toAdd = new Event(info[2]);
        String[] dateArrEvent = info[3].split(" ");
        toAdd.setDate(LocalDate.parse(dateArrEvent[0]));
        if(dateArrEvent.length > 1) {
            toAdd.setTime(dateArrEvent[1]);
            toAdd.setIsConvertedTime();
        }
        if (Integer.parseInt(info[1]) == 1) {
            toAdd.setComplete();
        }
        tasks.add(toAdd);
    }

    /**
     * Writes user's task information to the file in the path.
     * @param tasks List of tasks that the user specified.
     */
    public void write(TaskList tasks) {
        try {
            FileWriter toLoad = new FileWriter(path);
            for (Task t: tasks.getTasks()) {
                assert t != null : "Task is null!";
                String taskInfo = t.getTaskType() + ",";

                if (t.isCompleted()) {
                    taskInfo += "1,";
                } else {
                    taskInfo += "0,";
                }
                taskInfo += t.getTaskName() + ",";

                if (t.getTaskType().equals("D") || t.getTaskType().equals("E")) {
                    taskInfo += t.getDateFormat();
                }

                toLoad.write(taskInfo + "\n");
            }

            toLoad.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}

