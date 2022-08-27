import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves Task List to memory
 */
public class Storage {
    private File data;
    private FileWriter writer;
    private String PATH;

    public Storage(String filePath) {
        this.PATH = filePath;
        this.data = new File(PATH);

        try {
            if (this.data.createNewFile()) {
                System.err.println("File created " + this.data.getName());
            } else {
                System.err.println("File already exists at " + this.data.getPath());
            }
        } catch (IOException e) {
            System.out.println("I cannot save all your tasks in a file! Please try again.");
            System.err.println(e.getMessage());
        }
    }

    public void saveTasks(TaskList tasks) {
        try {
            this.writer = new FileWriter(this.data);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                this.writer.write(task.toString() + "\n");
                System.err.println("Successfully saved: " + task.toString());
            }
            this.writer.close();

        } catch (IOException e) {
            System.out.println("I cannot save the task.");
            System.err.println(e.getMessage());
        }
    }

    public TaskList load() {
        TaskList dataTasks = new TaskList();
        try {
            Scanner fileScanner = new Scanner(this.data);
            while (fileScanner.hasNextLine()) {
                String input = fileScanner.nextLine();
                System.out.println(input);
                String[] keywords = input.replace('|', '/').split("/", 2);
                dataTasks.loadTask(keywords);
            }

        } catch (FileNotFoundException e) {
            System.out.println("I... I can't find the file :(");
            System.err.println("Error: " + e.getMessage());
        } finally {
            return dataTasks;
        }
    }
}
