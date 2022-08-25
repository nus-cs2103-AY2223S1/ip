import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String taskDataPath;
    protected static UI UI;

    Storage(String taskDataPath) {
        this.taskDataPath = taskDataPath;
    }
    public void readTaskData(TaskList taskList) {
        try {
            File f = new File(taskDataPath);
            Scanner s = new Scanner(f);
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
        } catch (FileNotFoundException e) {
            UI.printResponse("I cannot find the data file :(\n");
        }
    }

    public void updateTaskData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(taskDataPath);
            for (int i = 0; i < taskList.getTaskList().size(); ++i) {
                fw.write(taskList.getTaskList().get(i).toWrite());
            }
            fw.close();
        } catch (IOException e) {
            UI.printResponse("Something went wrong: " + e.getMessage() + "\n");
        }
    }
}
