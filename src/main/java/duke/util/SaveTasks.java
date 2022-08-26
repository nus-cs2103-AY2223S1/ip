package duke.util;

import duke.DukeException;
import duke.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveTasks {
    private static final int TASKTYPE = 0;
    private static final int ISTASKDONE = 1;
    private static final int TASKDESCRIPTION = 2;
    private static final int TASKDATETIME = 3;
    private ArrayList<Task> savedTasks = new ArrayList<>(100);

    private String fileDir;
    private String filePath;

    public SaveTasks(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws DukeException {
        try {
            File dir = new File(this.fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(this.filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            Scanner fileReader = new Scanner(fileName);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return savedTasks;
    }

    public void readEntry(String entry) {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        switch (fields[TASKTYPE]) {
            case "T":
                taskToAdd = new Todos(fields[TASKDESCRIPTION]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
            case "E":
                taskToAdd = new Event(fields[TASKDESCRIPTION], fields[TASKDATETIME]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
            case "D":
                taskToAdd = new Deadlines(fields[TASKDESCRIPTION], fields[TASKDATETIME],
                        DateAndTimeFormatter.validateAndParse(fields[TASKDATETIME]));
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
        }
    }

    public void save(TaskList savedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(savedTasks.taskListToSaveString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
