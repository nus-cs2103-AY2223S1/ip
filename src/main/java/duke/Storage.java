package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
                    readTaskDataLineByLine(taskList, file);
                } else {
                    file.createNewFile();
                    throw new DukeException(UI.FILE_NOT_FOUND + UI.CREATE_FILE);
                }
            } else {
                path.mkdirs();
                file.createNewFile();
                throw new DukeException(UI.FILE_NOT_FOUND + UI.CREATE_FILE);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage() + "\n");
        } catch (IOException e) {
            throw new DukeException(e.getMessage() + "\n");
        }
    }

    public void readTaskDataLineByLine(TaskList taskList, File file) throws FileNotFoundException {
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
