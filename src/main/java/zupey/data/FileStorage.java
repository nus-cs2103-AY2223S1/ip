package zupey.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import zupey.entities.Deadline;
import zupey.entities.Event;
import zupey.entities.Task;
import zupey.entities.Tasklist;
import zupey.entities.Todo;

/** FileStorage saves the user's current Tasks */
public class FileStorage implements IStorage {
    private static final Pattern TASK_REGEX = Pattern.compile("^\\[(T|D|E)\\]\\[(x| )\\] ([^(]*)(?: \\(.*: (.*)\\))?$");
    private final String filePath;

    /**
     * Constructs FileStorage using filepath
     * @param filePath path to store the file in.
     */
    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Tasklist tasklist) {
        try {
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(String.format("Unable to create file: %s", this.filePath));
        }

        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            for (Task task: tasklist) {
                myWriter.write(task.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(String.format("Unable to write to file: %s", this.filePath));
        }
    }
    @Override
    public Tasklist load() {
        try {
            Tasklist result = new Tasklist();
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    Matcher m = TASK_REGEX.matcher(line);
                    m.find();
                    String taskType = m.group(1);
                    String taskDone = m.group(2);
                    String taskName = m.group(3);
                    String taskDate = m.group(4);
                    switch (taskType) {
                    case "T":
                        result.add(new Todo(taskName, taskDone.equals("x")));
                        break;
                    case "E":
                        result.add(new Event(taskName, taskDate));
                        break;
                    case "D":
                        result.add(new Deadline(taskName, taskDate));
                        break;
                    default:
                        continue;
                    }
                } catch (IllegalStateException ex) {
                    System.out.println(String.format("Invalid line found at line %d. Ignoring line %d.", lineNumber));
                }
            }
            return result;
        } catch (IOException ex) {
            System.out.println(String.format("File (%s) not found! Starting from empty Tasklist.", this.filePath));
            return new Tasklist();
        }
    }
}
