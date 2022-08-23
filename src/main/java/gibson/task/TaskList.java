package gibson.task;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(List<String> list) {
        taskList = new ArrayList<Task>();
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
                String[] stringArray = Parser.substringBeforeAfterToken(body, "\\(at:");
                t = new Event(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
            } else {
                String[] stringArray = Parser.substringBeforeAfterToken(body, "\\(by:");
                t = new Deadline(stringArray[0], stringArray[1].substring(0, stringArray[1].length() - 1));
            }
            if (marker == 'X') {
                t.mark();
            }
            taskList.add(t);
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int i) {
        Task t = taskList.get(i);
        taskList.remove(i);
        return t;
    }

    public Task mark(int i) {
        Task t = taskList.get(i);
        t.mark();
        return t;
    }

    public Task unmark(int i) {
        Task t = taskList.get(i);
        t.unmark();
        return t;
    }

    public int size() {
        return taskList.size();
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
