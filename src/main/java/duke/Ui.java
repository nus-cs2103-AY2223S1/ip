package duke;

public class Ui {
    private final String LINE_BREAK = "____________________________________________________________";

    public void hello() {
        System.out.println(LINE_BREAK + "\nHello! I'm Donovan\nWhat can I do for you?\n" + LINE_BREAK);
    }

    public void bye() {
        System.out.println(LINE_BREAK + "\n\tBye! Hope to see you again soon!\n" + LINE_BREAK);
    }

    public void showLoadingError() {
        System.out.println("OOPS! I have difficulty loading your file!");
    }

    public void printTodo(Task todo, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n", todo, size);
    }

    public void printDeadline(Task deadline, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n", deadline, size);
    }

    public void printEvent(Task event, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tGot it. I've added this task:\n\t%s\n\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n", event, size);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("\tHere are the tasks in your list.");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            System.out.printf("\t%d. %s\n", i + 1, task.toString());
        }
        System.out.println(LINE_BREAK);
    }

    public void printDelete(Task task, int size) {
        System.out.printf(LINE_BREAK
                + "\n\tNoted. I've removed this task:\n\t%s\n" + "\tNow you have %d tasks in the list.\n"
                        + LINE_BREAK + "\n", task, size);
    }

    public void printMarkedTask(Task task) {
        System.out.printf(LINE_BREAK
                + "\n\tNice! I've marked this task as done:\n\t%s\n"
                        + LINE_BREAK + "\n", task);
    }

    public void printUnmarkedTask(Task task) {
        System.out.printf(LINE_BREAK
                + "\n\tOkay, I've marked this task as not done yet:\n\t%s\n"
                        + LINE_BREAK + "\n", task);
    }


}
