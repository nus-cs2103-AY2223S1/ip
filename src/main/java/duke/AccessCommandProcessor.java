package duke;

public class AccessCommandProcessor {

    /**
     * Processes a given Access Command based on its keyword and performs the corresponding action to the keyword.
     * @param keyword keyword of Access Command
     * @param content body of Access Command
     * @param tasks Current TaskList of tasks
     * @return String result matching the state of the TaskList with the given command
     */
    protected static String processAccessCommand(String keyword, String content, TaskList tasks) {
        if (keyword.equals("list")) {
            String currentTasks = tasks.enumerateList();
            return (currentTasks.isEmpty()) ? "No Outstanding Tasks :)" : currentTasks;
        } else {
            String queriedTasks = tasks.findTasks(content);
            return (queriedTasks.isEmpty())
                    ? String.format("No tasks containing '%s' found", content)
                    : queriedTasks;
        }
    }
}
