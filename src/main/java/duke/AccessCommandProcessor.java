package duke;

public class AccessCommandProcessor {
    protected static String processAccessCommand(String keyword, String content, TaskList tasks) {
        if (keyword.equals("list")) {
            String outstandingTasks = tasks.enumerateList();
            return (outstandingTasks.isEmpty()) ? "No Outstanding Tasks :)" : outstandingTasks;
        } else {
            String queriedTasks = tasks.findTasks(content);
            return (queriedTasks.isEmpty())
                    ? String.format("No tasks containing '%s' found", content)
                    : queriedTasks;
        }
    }
}
