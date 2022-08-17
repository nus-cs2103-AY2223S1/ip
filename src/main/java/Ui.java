import java.util.Scanner;

public class Ui {
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    public static final Scanner in = new Scanner(System.in);

    public static void showError(Exception e) {
        System.out.println(LINE_DIVIDER);
        String[] lines = e.getMessage().split("\n");
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void printMessages(String[] messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void printTaskChange(String message, Task task, TaskList taskList) {
        printMessages(new String[] {
                message,
                task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }

}
