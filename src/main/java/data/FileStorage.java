package data;

import entities.Task;
import entities.Tasklist;
import entities.Todo;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Utils.customPrint;

public class FileStorage implements IStorage {
    private final String filePath;
    private static final Pattern TASK_REGEX = Pattern.compile("^\\[(T|D|E)\\]\\[(x| )\\] (.*)(?: \\(.*: (.*)\\))?$");

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(Tasklist tasklist) {
        try {
            File file = new File(this.filePath);
            file.createNewFile();
        } catch (IOException e) {
            customPrint((String.format("Unable to create file: %s", this.filePath)));
        }

        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            for (Task task: tasklist) {
                myWriter.write(task.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            customPrint(String.format("Unable to write to file: %s", this.filePath));
        }
    }
    @Override
    public Tasklist load() {
        try {
            Tasklist result = new Tasklist();
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            int lineNumber = 1;
            while((line = reader.readLine()) != null){
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
                    }
                    lineNumber++;
                } catch (IllegalStateException ex) {
                    customPrint(String.format("Invalid line found at line %d. Ignoring line %d.", lineNumber));
                }
            }
            return result;
        } catch (IOException ex) {
            customPrint(String.format("File (%s) not found! Starting from empty Tasklist.", this.filePath));
            return new Tasklist();
        }
    }
}
