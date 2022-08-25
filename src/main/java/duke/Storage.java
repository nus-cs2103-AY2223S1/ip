package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    protected String taskDataPath;
    protected String taskDataFileName;
    protected static UI UI;
    Storage(String taskDataPath, String taskDataFileName) {
        this.taskDataPath = taskDataPath;
        this.taskDataFileName = taskDataFileName;
    }
    public void readTaskData(TaskList taskList) {
        try {
            File path = new File(taskDataPath);
            File file = new File(taskDataPath + "/" + taskDataFileName);

            if (path.exists() && path.isDirectory()) {

                if (file.exists() && file.isFile()) {
                    Scanner s = new Scanner(file);
                    while (s.hasNext()) {
                        String line = s.nextLine();
                        String[] commands = line.split(",");

                        Task newTask;

                        if (commands[0].equals("T")) {
                            newTask = new ToDo(commands[2]);
                        } else if (commands[0].equals("D")) {
                            newTask = new Deadline(commands[2], commands[3], commands[4]);
                        } else {
                            newTask = new Event(commands[2], commands[3], commands[4]);
                        }

                        if(!commands[1].equals("0")) {
                            newTask.markAsDone();
                        }
                        taskList.getTaskList().add(newTask);
                    }
                } else {
                    file.createNewFile();
                    UI.printResponse("I cannot find the data file\n" + "But don't worry I will create one for you :)\n");
                }
            } else {
                path.mkdirs();
                file.createNewFile();
                UI.printResponse("I cannot find the data file\n" + "But don't worry I will create one for you :)\n");
            }
        } catch (FileNotFoundException e) {
            UI.printResponse("FileNotFoundException error has occurred :(\n");
        } catch (IOException e) {
            UI.printResponse("IOException error has occurred :(\n");
        }
    }

    public void updateTaskData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskDataPath + "/" + taskDataFileName);
            for (int i = 0; i < taskList.getTaskList().size(); ++i) {
                fw.write(taskList.getTaskList().get(i).toWrite());
            }
            fw.close();
        } catch (IOException e) {
            UI.printResponse("Something went wrong: " + e.getMessage() + "\n");
        }
    }
}
