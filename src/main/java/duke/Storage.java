package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class Storage {
    String filePath;
    String fileDirectory;
    String actualFile;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path of file to retrieve task list from and to store tasks in.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileDirectory = this.filePath.split("/", 2)[0];
        this.actualFile = this.filePath.split("/", 2)[1];
    }

    /**
     * Opens the text file containing the task list and retrieves tasks from it.
     *
     * @return TaskList containing the tasks from the text file.
     */
    public TaskList open() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] splitInput = s.nextLine().strip().split(" / ", 4);
                String type = splitInput[0].strip();
                String completionStr = splitInput[1].strip();
                Boolean isDone = completionStr.equals("1");
                String description = splitInput[2].strip();
                if (type.equals("T")) {
                    taskList.addTask(new Todos(description, isDone));
                } else if (type.equals("D")) {
                    taskList.addTask(new Deadlines(description, splitInput[3].strip(), isDone));
                } else if (type.equals("E")) {
                    taskList.addTask(new Events(description, splitInput[3].strip(), isDone));
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            File f1 = new File(this.filePath);
            try {
                f1.createNewFile();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
            return taskList;
        }
    }

    /**
     * Stores the current task list to the text file.
     *
     * @param taskList Task list to be stored in file.
     */
    public void end(TaskList taskList) {
        try {
            new File(this.filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(this.filePath);
            for(int i = 0; i < taskList.getSize(); i++) {
                Task toWrite = taskList.retrieveTask(i);
                fw.write(toWrite.fileString());
                fw.write(System.lineSeparator());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
