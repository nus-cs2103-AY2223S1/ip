package duke;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.util.List;
import duke.task.Task;


public class Storage {

    public void loadStorage(String filePath, TaskList taskList) {
        File f = new File(filePath);
        Parser p = new Parser();
        List<Task> list = taskList.list;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                Task task = p.fileLoadParser(sc.nextLine());
                list.add(task);
            }
        } catch (IOException e) {
            System.out.println("file not found, we will create one for you");
        }
    }
    public void writeToTaskList(String filePath, TaskList taskList) {
        try {
            List<Task> list = taskList.list;
            FileWriter writer = new FileWriter(filePath);
            int i = 0;
            while(i < list.size()) {
                writer.write(list.get(i).tofileString());
                writer.write(System.getProperty("line.separator"));
                i++;
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Directory does not exist, creating new directory now");
            File f = new File("data");
            f.mkdir();
            writeToTaskList(filePath, taskList);
        }
    }
}
