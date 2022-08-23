public class TaskMessager extends Messager {
    private static final String TASK_LIST = "Here are the tasks in your list:";
    private static final String TASK_MARK = "Good Job! I will mark this task as done: ";
    private static final String TASK_UNMARK = "Aw... Strive to finish it soon! Will mark it as undone: ";
    private static final String TASK_ADD = "Got it. I've added this task";
    private static final String TASK_DELETE = "Noted. I've removed this task:";


    public TaskMessager() {
        super();
    }

    public void listMessage(Object message) {
        message(TASK_LIST);
        message(message);
    }

    private void remainMessage(int length) {
        message(String.format("Now you have %d tasks in the list", length));
    }

    public void addMessage(Object message, int length) {
        message(TASK_ADD);
        message("\t" + message);
        remainMessage(length);
    }

    public void markMessage(Object message) {
        message(TASK_MARK);
        message(message);
    }

    public void unmarkMessage(Object message) {
        message(TASK_UNMARK);
        message(message);
    }

    public void deleteMessage(Object message, int length) {
        message(TASK_DELETE);
        message("\t" + message);
        remainMessage(length);

    }
}
