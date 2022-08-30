package iana.main;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import iana.exception.IanaException;
import iana.tasks.Task;
import iana.tasks.TaskList;

public class Storage {
    private String filePath;
    
    public Storage(String path) {
        this.filePath = path;
    }

    public TaskList load() throws IanaException {
        TaskList taskList = new TaskList(new ArrayList<Task>());

        try{
            File file = new File(this.filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] taskArray = input.split(" \\| ", 4);

                String taskClass = taskArray[0];
                boolean isCompleted = Integer.parseInt(taskArray[1]) == 1; // cannot just check int val as string
                String taskDescription =  taskArray[2];

                switch(taskClass) {
                    case "T":
                    String taskString = String.format("todo %s", taskDescription);
                    taskList.add(Task.of(taskString, isCompleted));
                    break;

                    case "E":
                    String taskTime = taskArray[3];
                    taskString = String.format("event %s /at %s", taskDescription, taskTime);
                    taskList.add(Task.of(taskString, isCompleted));
                    break;

                    case "D":
                    taskTime = taskArray[3];
                    taskString = String.format("deadline %s /by %s", taskDescription, taskTime);
                    taskList.add(Task.of(taskString, isCompleted));
                    break;

                    default:
                    throw new IanaException("File is corrupted");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new IanaException("File DataStorage.txt not found in [project_root]/src/main/data. Cannot read data!");
        }
        return taskList;
    } 

    public void write(TaskList taskList) throws IanaException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.toFileData());
            fw.close();
        } catch (IOException e) {
            throw new IanaException("File DataStorage.txt not found in [project_root]/src/main/data. Cannot write data!");
        }
    }
}
