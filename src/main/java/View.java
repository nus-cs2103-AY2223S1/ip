import java.util.List;

public class View {

    public static void printLogo() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + "logo");
    }

    public static void printWelcomeText() {
        System.out.println(dialog("Hello! I'm Duke", "What can I do for you?"));
    }

    public static void printTerminateStatement() {
        System.out.println(dialog("Bye. Hope to see you again soon!"));
    }

    public static void printMemo(List<? extends Task> memo) {
        String[] strArr = new String[memo.size() + 1];
        strArr[0] = "Here are the tasks in your list:";
        for (int i = 1; i < strArr.length; i++) {
            strArr[i] = i + ". " + memo.get(i - 1);
        }
        System.out.println(dialog(strArr));
    }

    public static void printTask(Task task) {
        if (task.isDone) {
            System.out.println(dialog("Nice! I've marked this task as done:", task.toString()));
        } else {
            System.out.println(dialog("Nice! I've marked this task as not done yet:", task.toString()));
        }
    }

    public static void printDeletedTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Noted. I've removed this task:", task.toString(), remainder));
    }

    public static void printAddTask(Task task, Integer numOfRemainingTasks) {
        String remainder = String.format("Now you have %d tasks in the list.", numOfRemainingTasks);
        System.out.println(dialog("Got it. I've added this task:", task.toString(), remainder));
    }

    public static void printInvalidCommandException(InvalidCommandException e) {
        System.out.println(dialog(e.getMessage()));
    }

    public static void printEmptyDescriptionException(EmptyDescriptionException e) {
        System.out.println(dialog(e.getMessage()));
    }

    public static void printInvalidIndexException(InvalidIndexException e) {
        System.out.println(dialog(e.getMessage()));
    }

    public static String dialog(String... strings) {
        StringBuilder sb = new StringBuilder();
        sb.append("  ____________________________________________________________\n");
        for (String message : strings) {
            sb.append("   " + message + "\n");
        }
        sb.append("  ____________________________________________________________");
        return sb.toString();
    }
}
