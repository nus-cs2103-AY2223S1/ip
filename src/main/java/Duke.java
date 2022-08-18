import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Prompt.startPrompt();
        duke();
    }

    private static final TaskList taskList = new TaskList();

    private static void duke() {

        final String terminatingText = "bye";
        final String listText = "list";
        final String check = "^check \\d+$";
        final String uncheck = "^uncheck \\d+$";

        boolean running = true;
        while (running) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case terminatingText:
                    running = false;
                    Prompt.endPrompt();
                    break;
                case listText:
                    listTasks();
                    break;
                default:
                    if (input.matches(check)) {
                        String taskIndex = input.split(" ", 2)[1];
                        checkTask(Integer.parseInt(taskIndex));
                        break;
                    }
                    if (input.matches(uncheck)) {
                        String taskIndex = input.split(" ", 2)[1];
                        uncheckTask(Integer.parseInt(taskIndex));
                        break;
                    }
                    defaultCase(input.trim());
                    break;
            }
        }
    }

    private static void listTasks() {
        taskList.listTask();
        Prompt.lineDivider();
    }

    private static void checkTask(int index) {
        taskList.checkTask(index);
        Prompt.lineDivider();
    }

    private static void uncheckTask(int index) {
        taskList.uncheckTask(index);
        Prompt.lineDivider();
    }

    static void defaultCase(String input) {
        taskList.addTask(new Task(input));
        Prompt.lineDivider();
    }
}
