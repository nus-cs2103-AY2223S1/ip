package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Storage {
    private String dataPath;
    private String taskDataFileName;
    private String memoryDataFileName;
    protected static UI UI;
    Storage(String dataPath, String taskDataFileName, String memoryDataFileName) {
        this.dataPath = dataPath;
        this.taskDataFileName = taskDataFileName;
        this.memoryDataFileName = memoryDataFileName;
    }

    /**
     * Reads list of tasks from saved data and updates inputted TaskList object accordingly
     *
     * @param taskList
     */
    public void readTaskAndMemoryData(TaskList taskList, HashMap<String, String> memory) throws DukeException {
        try {
            File path = new File(dataPath);
            File taskDataFile = new File(dataPath + "/" + taskDataFileName);
            File memoryDataFile = new File(dataPath + "/" + memoryDataFileName);

            if (path.exists() && path.isDirectory()) {
                if (taskDataFile.exists() &&
                        taskDataFile.isFile() &&
                        memoryDataFile.exists() &&
                        memoryDataFile.isFile()) {
                    readTaskDataLineByLine(taskList, taskDataFile);
                    readMemoryLineByLine(memory, memoryDataFile);
                } else {
                    taskDataFile.createNewFile();
                    throw new DukeException(UI.FILE_NOT_FOUND + UI.CREATE_FILE);
                }
            } else {
                path.mkdirs();
                taskDataFile.createNewFile();
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

    public void readMemoryLineByLine(HashMap<String, String> memory, File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] commands = line.split(",");

            memory.put(commands[0], commands[1]);
        }
    }

    public void updateTaskAndMemoryData(TaskList taskList, HashMap<String, String> memory) throws DukeException {
        updateTaskData(taskList);
        updateMemoryData(memory);
    }

    /**
     * Saves task list data based on inputted TaskList object
     *
     * @param taskList
     */
    public void updateTaskData(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataPath + "/" + taskDataFileName);
            for (int i = 0; i < taskList.getTaskList().size(); ++i) {
                fw.write(taskList.getTaskList().get(i).toWrite());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage() + "\n");
        }
    }

    public void updateMemoryData(HashMap<String, String> memory) throws DukeException {
        try {
            FileWriter fw = new FileWriter(dataPath + "/" + memoryDataFileName);
            for (String i : memory.keySet()) {
                fw.write(i + "," + memory.get(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage() + "\n");
        }
    }
}
