package duke;

public class TaskCommandProcessor {
    private final static char TASKID_TODO = 'T';
    private final static char TASKID_DEADLINE = 'D';
    private final static char TASKID_EVENT = 'E';

    /**
     * Processes a given Task Command based on its keyword and performs the corresponding action to the keyword.
     *
     * @param keyword keyword of Task Command.
     * @param content body of Task Command.
     * @param tasks Current TaskList of tasks.
     * @param ui Ui of Duke.
     * @return String result with the details of the newly added task.
     */
    protected static String processTaskCommand(String keyword, String content, TaskList tasks, Ui ui) {
        Task newTask;
        if (keyword.equals("deadline")) {
            newTask = new Deadline(content, TASKID_DEADLINE);
        } else if (keyword.equals("event")) {
            newTask = new Event(content, TASKID_EVENT);
        } else {
            newTask = new Todo(content, TASKID_TODO);
        }
        tasks.add(newTask);
        return ui.addTaskConfirmation(newTask, tasks.getSize());
    }
}
