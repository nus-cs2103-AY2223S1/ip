import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Prompt.startPrompt();
        duke();
    }

    private static final TaskList taskList = new TaskList();

    private static void duke() {

        final String charSet = "[a-zA-Z0-9 !@#$%^&*()~`]+";

        final String terminatingText = "bye";
        final String listText = "list";
        final String check = "^check \\d+$";
        final String uncheck = "^uncheck \\d+$";
        final String todo = String.format("^todo %s", charSet);
        final String deadline = String.format("^deadline %s\\\\by %s", charSet, charSet);
        final String event = String.format("^event %s\\\\at %s", charSet, charSet);

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
                        String[] info = input.split(" ", 2);
                        String taskIndex = info[1];
                        uncheckTask(Integer.parseInt(taskIndex));
                        break;
                    }
                    if (input.matches(todo)) {
                        String[] info = input.split(" ", 2);
                        String taskInfo = info[1];
                        addTask(new TaskTodo(taskInfo));
                        break;
                    }
                    if (input.matches(deadline)) {
                        String[] info = input.split(" ", 2);
                        String taskInfo = info[1];
                        String by = "\\by";
                        int byIndex = taskInfo.lastIndexOf(by);
                        String taskName = taskInfo.substring(0, byIndex - 1);
                        String taskBy = taskInfo.substring(byIndex + 1 + by.length());
                        addTask(new TaskDeadline(taskName, taskBy));
                        break;
                    }
                    if (input.matches(event)) {
                        String[] info = input.split(" ", 2);
                        String taskInfo = info[1];
                        String at = "\\at";
                        int atIndex = taskInfo.lastIndexOf(at);
                        String taskName = taskInfo.substring(0, atIndex - 1);
                        String taskAt = taskInfo.substring(atIndex + 1 + at.length());
                        addTask(new TaskEvent(taskName, taskAt));
                        break;
                    }
                    new InvalidInstructionException();
                    Prompt.lineDivider();
            }
        }
    }

    private static void listTasks() {
        taskList.listTask();
        Prompt.lineDivider();
    }

    private static void checkTask(int index) {
        taskList.checkTask(index);
        taskList.listTask();
        Prompt.lineDivider();
    }

    private static void uncheckTask(int index) {
        taskList.uncheckTask(index);
        taskList.listTask();
        Prompt.lineDivider();
    }

    private static <T extends Task> void addTask(T task) {
        taskList.addTask(task);
        taskList.listTask();
        Prompt.lineDivider();
    }
}
