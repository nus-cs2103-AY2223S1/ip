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

    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileDirectory = this.filePath.split("/", 2)[0];
        this.actualFile = this.filePath.split("/", 2)[1];
    }

    public TaskList open() {
        TaskList taskList = new TaskList();
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] splitInput = s.nextLine().strip().split("/", 4);
                String type = splitInput[0].strip();
                if (type.equals("T")) {
                    taskList.addTask(new Todos(splitInput[2].strip(),
                            Integer.parseInt(splitInput[1].strip()) == (1)));
                } else if (type.equals("D")) {
                    taskList.addTask(new Deadlines(splitInput[2].strip(), splitInput[3].strip()));
                } else if (type.equals("E")) {
                    taskList.addTask(new Events(splitInput[2].strip(), splitInput[3].strip()));
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            File f1 = new File(this.filePath);
            try {
                f1.createNewFile();
            } catch (IOException e1) {
                System.out.println(e1.getMessage());
            }
            return taskList;
        }
    }

    public void end(TaskList taskList) {
        try {
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
