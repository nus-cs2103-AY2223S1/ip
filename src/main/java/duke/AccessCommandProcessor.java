package duke;

public class AccessCommandProcessor {
    protected static String processAccessCommand(String keyword, String content, TaskList tasks) {
        if (keyword.equals("list")) {
            return tasks.enumerateList();
        } else {
            return tasks.findTasks(content);
        }
    }
}
