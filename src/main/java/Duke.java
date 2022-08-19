import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        printResponse("hi... please feed me with some tasks");
        String input = reader.nextLine().strip().toUpperCase();
        while (!input.equals("BYE")) {
            String response;
            int index;
            switch (input) {
                case "LIST":
                    // display TaskList
                    printResponse(taskList.toString());
                    break;
                case "MARK":
                    printResponse("which task would you like to mark? (enter its number in the list!)");
                    index = reader.nextInt() - 1;
                    String doneTask = taskList.markAsDone(index);
                    response = "Task marked as done:\n" + doneTask;
                    printResponse(response);
                    // todo: handle missing/invalid index
                    break;
                case "UNMARK":
                    printResponse("which task would you like to mark as undone? (enter its number in the list!)");
                    index = reader.nextInt() - 1;
                    String undoneTask = taskList.markAsUndone(index);
                    response = "Task marked as not done yet:\n" + undoneTask;
                    printResponse(response);
                    // todo: handle code duplication (similar to mark)
                    break;
                case "ADD":
                    // add task to TaskList
                    Task newTask = new Todo(""); // better soln?
                    printResponse("what would you like to add? (todo | deadline | event)");
                    String taskType = reader.nextLine().strip().toUpperCase();
                    printResponse("description:");
                    String description = reader.nextLine();
                    switch (taskType) {
                        case "TODO":
                            newTask = new Todo(description);
                            break;
                        case "DEADLINE":
                            printResponse("deadline:");
                            String deadline = reader.nextLine();
                            newTask = new Deadline(description, deadline);
                            break;
                        case "EVENT":
                            printResponse("event date/time:");
                            String eventTime = reader.nextLine();
                            newTask = new Event(description, eventTime);
                            break;
                        default:
                            // throw error
                    }
                    taskList.addTask(newTask);
                    printResponse("added: " + newTask);
                default:

            }
            input = reader.nextLine().strip().toUpperCase();
        }

        printResponse("bye...");
        reader.close();
    }

    private static void printResponse(String input) {
        String indentedInput = input.replaceAll("(?m)^", "\t");
        System.out.println("\t----------------------------------");
        System.out.println(indentedInput);
        System.out.println("\t----------------------------------");
    }
}
