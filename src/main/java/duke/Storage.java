package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;


public class Storage {

    public void loadStorage(String filePath, TaskList taskList) {
        File f = new File(filePath);
        Parser p = new Parser();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                Task task = p.fileLoadParser(sc.nextLine());
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("file not found, we will create one for you");
        }
    }
    public void writeToTaskList(String filePath, TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            int i = 0;
            while (i < taskList.getsize()) {
                writer.write(taskList.get(i).tofileString());
                writer.write(System.getProperty("line.separator"));
                i++;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Directory does not exist, creating new directory now");
            File f = new File("data");
            f.mkdir();
            writeToTaskList(filePath, taskList);
        }
    }
}
