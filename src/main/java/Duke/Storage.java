package Duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
    private File file;
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeTasks(TaskList tl)  {
        try {
            ArrayList<Task> list = tl.listTasks();
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Task task : list) {
                printWriter.println(task);
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("☹ OOPS!!! Failed to save your file!.");
        }
    }
}