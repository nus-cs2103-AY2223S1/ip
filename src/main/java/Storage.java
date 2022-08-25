import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {

    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * initData checks for existence of file, attempts to read and process the data if it exists,
     * creates a new file if it does not.
     */
    public File load() {
        return new File(filepath);
    }

    /**
     * parses and writes content of Task to the data file.
     *
     * @param input given Task to write to file.
     */
    public void writeToFile(Task input) throws IOException{
        FileWriter writer = new FileWriter(filepath, true);
        String taskType = input.taskType();
        String completed = input.isCompleted() ? "1" : "0";
        String name = input.getTaskName();
        if (Objects.equals(taskType, "T")) {
            String str = String.format("%s | %s | %s \n", taskType, completed, name);
            writer.write(str);
        } else {
            String date = input.getDate();
            String str = String.format("%s | %s | %s | %s\n", taskType, completed, name, date);
            writer.write(str);
        }
        writer.close();
    }

    /**
     * rewrites the entire data file with everyone item in current Array.
     */
    public void rewriteFile(ArrayList<Task> taskArray) throws IOException{
        FileWriter writer = new FileWriter(filepath, false);

        for (Task task : taskArray) {
            String taskType = task.taskType();
            String completed = task.isCompleted() ? "1" : "0";
            String name = task.getTaskName();
            if (Objects.equals(taskType, "T")) {
                String str = String.format("%s | %s | %s \n", taskType, completed, name);
                writer.write(str);
            } else {
                String date = task.getDate();
                String str = String.format("%s | %s | %s | %s\n", taskType, completed, name, date);
                writer.write(str);
            }
        }
        writer.close();
    }
}
