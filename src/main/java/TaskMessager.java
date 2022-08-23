public class TaskMessager extends Messager {
    private static final String TASK_LIST = "Here are the tasks in your list:";
    private static final String TASK_MARK = "Good Job! I will mark this task as done: ";
    private static final String TASK_UNMARK = "Aw... Strive to finish it soon! Will mark it as undone: ";

    public TaskMessager() {
        super();
    }

    public void listMessage(Object message) {
        ioHelper.print(TASK_LIST);
        message(message);
    }

    public void addMessage(Object message) {
        message("Added: " + message);
    }

    public void markMessage(Object message) {
        ioHelper.print(TASK_MARK);
        message(message);
    }

    public void unmarkMessage(Object message) {
        ioHelper.print(TASK_UNMARK);
        message(message);
    }

}
