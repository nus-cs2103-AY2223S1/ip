public class Ui {
    private static final String minorIndentation = "  ";
    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";

    /**
     * Show messages to the user.
     *
     * @param messages List of messages to be shown.
     */
    public void show(String... messages) {
        for (String m : messages) {
            System.out.println(indentation + m);
        }
    }

    /**
     * Shows a formatted message to the user.
     *
     * @param format A format string.
     * @param args Arguments referenced by format string.
     */
    public void showf(String format, Object... args) {
        String message = String.format(format, args);
        this.show(message);
    }

    public void showLineBreak() {
        this.show(horizontalLine);
    }

    public void showWelcomeMessage() {
        this.show(
                horizontalLine,
                "Hello I'm",
                "____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "",
                "What can I do for you?",
                horizontalLine
        );
    }

    public void showGoodbyeMessage() {
        this.show(
                "Bye, hope to see you soon!",
                horizontalLine
        );
    }

    public void showErrorMessage(String message) {
        this.show(
                message,
                horizontalLine
        );
    }

    public void showErrorMessage(Exception exception) {
        this.showErrorMessage(exception.getMessage());
    }

    public void showMarkResult(Task task, int taskIndex) {
        String markedTask = String.format("Marked task %d as done!", taskIndex);
        this.show(
                markedTask,
                minorIndentation + task
        );
    }

    public void showUnmarkResult(Task task, int taskIndex) {
        String unmarkedTask = String.format("Marked task %d as not done!", taskIndex);
        this.show(
                unmarkedTask,
                minorIndentation + task
        );
    }

    public void showDeleteResult(Task task, int numberOfTasksLeft) {
        String tasksLeft = String.format("Now you have %d tasks left.", numberOfTasksLeft);
        this.show(
                "Removing this task!",
                minorIndentation + task,
                tasksLeft
        );
    }

    public void showTodoResult(Todo todo, int numberOfTasks) {
        String tasksLeft = String.format("Now you have %d tasks.", numberOfTasks);
        this.show(
                "Added this todo!",
                minorIndentation + todo,
                tasksLeft
        );
    }

    public void showDeadlineResult(Deadline deadline, int numberOfTasks) {
        String tasksLeft = String.format("Now you have %d tasks.", numberOfTasks);
        this.show(
                "Added this deadline!",
                minorIndentation + deadline,
                tasksLeft
        );
    }

    public void showEventResult(Event event, int numberOfTasks) {
        String tasksLeft = String.format("Now you have %d tasks.", numberOfTasks);
        this.show(
                "Added this event!",
                minorIndentation + event,
                tasksLeft
        );
    }
}
