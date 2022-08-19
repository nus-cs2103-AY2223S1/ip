import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Task {
    private static final String DATA_PATH = new File("").getAbsolutePath() + "/data/duke.txt";
    private String title;
    private boolean completed;

    Task(String title) {
        this.title = title;
        this.completed = false;
    }

    Task(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public void markAsCompleted() {
        this.completed = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n\t%s", this));
    }

    public void markAsIncomplete() {
        this.completed = false;
        System.out.println(String.format("OK, I've marked this task as not done yet:\n\t%s", this));
    }

    private String getStatusIcon() {
        return (this.completed ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.title);
    }

    public String toSaveString() {
        return String.format("%d,%s", this.completed ? 1 : 0, this.title);
    }

    private static Task parse(String data) throws ParsingTaskException {
        String[] components = data.split(",");

        if (components.length == 0) {
            throw new ParsingTaskException("Data was empty or not formatted properly.");
        }
        String type = components[0];

        if (type.equals("T")) {
            return Todo.parse(data);
        } else if (type.equals("D")) {
            return Deadline.parse(data);
        } else if (type.equals("E")) {
            return Event.parse(data);
        } else {
            throw new ParsingTaskException(String.format("Task was of unknown type: %s", type));
        }
    }

    public static List<Task> loadSavedTasks() {
        List<Task> tasks = new ArrayList<>();
        try {

            File file = new File(DATA_PATH);

            file.getParentFile().mkdirs();
            file.createNewFile();

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                try {
                    tasks.add(Task.parse(data));
                } catch (ParsingTaskException e) {
                    System.out.println(e);
                    continue;
                }
            }

            scanner.close();

            return tasks;

        } catch (Exception e) {
            System.out.println("An error occurred.\n" + e);
        } finally {
            return tasks;
        }
    }

    public static void saveTaskList(List<Task> tasks) {
        try {
            // Create new file
            String content = "";
            for (Task t : tasks ) {
                content += t.toSaveString() + "\n";
            }

            File file = new File(DATA_PATH);

            // If the file doesn't exist, then create it
            file.getParentFile().mkdirs();
            file.createNewFile();


            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            bw.write(content);

            // Close connection
            bw.close();

            System.out.println("Saved tasks list successfully!");
        }
        catch (Exception e){
            System.out.println(e + DATA_PATH);
        }
    }
}
