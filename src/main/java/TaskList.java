import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TaskList {
    private ArrayList<Task> taskList;
    private static final Path DIRECTORY_PATH = Paths.get("data");
    private static final Path FILE_PATH = Paths.get("data/gibson.txt");

    public TaskList() {
        taskList = new ArrayList<Task>();
        try {
            if (Files.exists(DIRECTORY_PATH)) {
                if (Files.exists(FILE_PATH)) {
                    load();
                } else {
                    Files.createFile(FILE_PATH);
                }
            } else {
                Files.createDirectories(DIRECTORY_PATH);
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            System.err.println("Failed to create directory/file. " + e.getMessage());
        }
    }

    public void add(Task task) {
        taskList.add(task);
        save();
    }

    public Task remove(int i) {
        Task t = taskList.get(i);
        taskList.remove(i);
        save();
        return t;
    }

    public Task mark(int i) {
        Task t = taskList.get(i);
        t.mark();
        save();
        return t;
    }

    public Task unmark(int i) {
        Task t = taskList.get(i);
        t.unmark();
        save();
        return t;
    }

    public int size() {
        return taskList.size();
    }
    private void save() {
        try {
            Files.write(FILE_PATH, toString().getBytes());
        } catch (IOException e) {
            System.err.println("Failed to write to Gibson.txt. " + e.getMessage());
        }
    }

    private void load() {
        try {
            List<String> linesInSave = Files.readAllLines(FILE_PATH);
            for (String line : linesInSave) {
                Pattern pattern = Pattern.compile("]");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                char type = line.charAt(matcher.start() - 1);
                matcher.find();
                char marker = line.charAt(matcher.start() - 1);
                String body = line.substring(matcher.end() + 1);
                Task t;
                if (type == 'T') {
                    t = new Task(body);
                } else if (type == 'E') {
                    String[] stringArray = RegexUtility.substringBeforeAfterToken(body, "\\(at:");
                    t = new Event(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
                } else {
                    String[] stringArray = RegexUtility.substringBeforeAfterToken(body, "\\(by:");
                    t = new Deadline(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
                }
                if (marker == 'X') {
                    t.mark();
                }
                taskList.add(t);
            }
        } catch (IOException e) {
            System.err.println("Failed to read Gibson.txt. " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(".").append(taskList.get(i).toString());
            if (i + 1 < taskList.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
