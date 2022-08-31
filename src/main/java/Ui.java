/**
 * Prints out
 */
public class Ui {
    private static final String LINE_BREAK = "------------------------------------------------------------";

    public static void printMessageForStartingUp() {
        System.out.println(LINE_BREAK);
        System.out.println("Sup bro! My name is Candice.");
        System.out.println("I'm here to help you track your tasks!");
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForShuttingDown() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye bro. See you soon.");
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForAddTask(Task newTask, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("I gotchu fam. Your task has been added:");
        System.out.println("  " + newTask.getStatus());
        System.out.println("You have " + taskList.getLength() + " task(s). Stop procrastinating bro.");
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForDeleteTask(Task removedTask, TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("I hope you're not deleting this just to avoid work:");
        System.out.println("  " + removedTask.getStatus());
        System.out.println("You have " + taskList.getLength() + " task(s). Stop procrastinating bro.");
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForMarkTask(Task selectedTask) {
        System.out.println(LINE_BREAK);
        System.out.println("Nice one lah, finally doing your work.");
        System.out.println(selectedTask.getStatus());
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForUnmarkTask(Task selectedTask) {
        System.out.println(LINE_BREAK);
        System.out.println("What happened bro.");
        System.out.println(selectedTask.getStatus());
        System.out.println(LINE_BREAK);
    }

    public static void printMessageForList(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Here are your tasks. You better start now:");
        System.out.println(taskList.toString());
        System.out.println(LINE_BREAK);
    }
}
