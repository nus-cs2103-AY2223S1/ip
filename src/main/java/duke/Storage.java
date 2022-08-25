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

    /**
     * Reads list of tasks from saved data and updates inputted TaskList object accordingly
     *
     * @param taskList
     */
    public void readTaskData(TaskList taskList) throws DukeException {
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
                    UI.printResponse(UI.fileNotFound + UI.createFile);
                }
            } else {
                path.mkdirs();
                file.createNewFile();
                UI.printResponse(UI.fileNotFound + UI.createFile);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("FileNotFoundException error has occurred :(\n");
        } catch (IOException e) {
            throw new DukeException("IOException error has occurred :(\n");
        }
    }

    /**
     * Saves task list data based on inputted TaskList object
     *
     * @param taskList
     */
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
