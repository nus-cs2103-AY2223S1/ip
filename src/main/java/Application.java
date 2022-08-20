import java.util.Scanner;

public class Application {
    private TaskList taskList = new TaskList();
    boolean active = true;

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (active) {
            printResponse("please choose from these commands: add | list | mark | unmark | bye");
            String input = sc.nextLine().strip().toUpperCase();
            switch (input) {
                case "LIST":
                    list();
                    break;
                case "MARK":
                    mark(input, sc);
                    break;
                case "UNMARK":
                    unmark(input, sc);
                    break;
                case "ADD":
                    add(sc);
                    break;
                case "BYE":
                    bye();
                    active = false;
                    sc.close();
                    break;
                default:
                    handleInvalidCommand();
            }
        }
    }

    private void list() {
        printResponse(taskList.toString());
    }

    private void mark(String input, Scanner sc) {
        printResponse("which task would you like to mark as done? (enter its number in the list!)");
        int index = Integer.valueOf(sc.nextLine()) - 1;
        String doneTask = taskList.markAsDone(index);
        String response = "Task marked as done:\n" + doneTask;
        printResponse(response);
        // todo: handle missing/invalid index
    }

    private void unmark(String input, Scanner sc) {
        printResponse("which task would you like to mark as undone? (enter its number in the list!)");
        int index = Integer.valueOf(sc.nextLine()) - 1;
        String doneTask = taskList.markAsUndone(index);
        String response = "Task marked as undone:\n" + doneTask;
        printResponse(response);
        // todo: handle missing/invalid index
    }

    private void add(Scanner sc) {
        // add task to TaskList
        Task newTask = null;
        TaskType taskType = getTaskType(sc);
        String description = getTaskDescription(sc);
        switch (taskType) {
            case TODO:
                newTask = new Todo(description);
                break;
            case DEADLINE:
                printResponse("deadline:");
                String deadline = sc.nextLine();
                newTask = new Deadline(description, deadline);
                break;
            case EVENT:
                printResponse("event date/time:");
                String eventTime = sc.nextLine();
                newTask = new Event(description, eventTime);
                break;
        }
        taskList.addTask(newTask);
        printResponse("added: " + newTask);
    }

    private void bye() {
        printResponse("bye...");
    }

    private void handleInvalidCommand() {
        printResponse("I'm sorry, but I don't know what that means :)\n"
                + "Please choose from: add | mark | unmark | list | bye");
    }

    private TaskType getTaskType(Scanner sc) {
        printResponse("Please choose one: todo | deadline | event");
        String taskType = sc.nextLine().strip().toUpperCase();
        try {
            return TaskType.valueOf(taskType);
        } catch (IllegalArgumentException e) {
            // keep trying until valid TaskType provided by user
            return getTaskType(sc);
        }
    }

    private String getTaskDescription(Scanner sc) {
        printResponse("description:");
        String description = "";
        while (description.isEmpty()) {
            description = sc.nextLine().strip();
            if (description.isEmpty()) {
                printResponse("Please enter a non-blank description:");
            }
        }
        return description;
    }

    private void printResponse(String input) {
        // String indentedInput = input.replaceAll("(?m)^", "\t");
        // System.out.println("\t----------------------------------");
        System.out.println("----------------------------------");
        // System.out.println(indentedInput);
        System.out.println(input);
        System.out.println("----------------------------------");
        // System.out.println("\t----------------------------------");
    }
}
