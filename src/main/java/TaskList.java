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
    private static final Storage storage = new Storage("data", "gibson.txt");

    public TaskList() {
        taskList = new ArrayList<Task>();
        if (storage.hasDataToLoad()) {
            addFromStringList(storage.load());
        }
    }

    public void add(Task task) {
        taskList.add(task);
        storage.save(toString());
    }

    public Task remove(int i) {
        Task t = taskList.get(i);
        taskList.remove(i);
        storage.save(toString());
        return t;
    }

    public Task mark(int i) {
        Task t = taskList.get(i);
        t.mark();
        storage.save(toString());
        return t;
    }

    public Task unmark(int i) {
        Task t = taskList.get(i);
        t.unmark();
        storage.save(toString());
        return t;
    }

    public int size() {
        return taskList.size();
    }

    public void addFromStringList(List<String> list) {
        for (String line : list) {
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
