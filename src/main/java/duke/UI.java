package duke;

public class UI {


    public static void welcome() {
        System.out.println("Hello! I am Duke. Please enter your command: ");
    }

    public static void bye() { System.out.println("Bye. Hope to see you again soon!"); }

    public static void added(Task task) {
        System.out.println(task.added());
    }

    public static void delete(Task task) {
        System.out.printf("Noted. I've removed this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", task, Task.taskCount);
    }

    public static void list(TaskList taskList) {
        System.out.printf("Here are the tasks in your list:\n");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.getTask(i));
        }
    }

    public static void markAsDone(Task task) {
        System.out.printf("Nice! I've marked this task as done: \n" +
                            "%s\n", task);
    }

    public static void markAsUndone(Task task) {
        System.out.printf("OK, I've marked this task as not done yet: \n" +
                            "%s\n", task);
    }
}
