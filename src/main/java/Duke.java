import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        printResponse("hi... please feed me with some tasks");
        String input = reader.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                // display TaskList
                printResponse(taskList.toString());
            } else if (input.split(" ")[0].equals("mark")) {
                // mark task as done
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                String doneTask = taskList.markAsDone(index);
                String response = "Task marked as done:\n" + doneTask;
                printResponse(response);
                // todo: handle missing/invalid index
            } else if (input.split(" ")[0].equals("unmark")) {
                // mark task as undone;
                int index = Integer.valueOf(input.split(" ")[1]) - 1;
                String undoneTask = taskList.markAsUndone(index);
                String response = "Task marked as not done yet:\n" + undoneTask;
                printResponse(response);
                // todo: handle code duplication (similar to mark)
            } else {
                // add task to TaskList
                taskList.addTask(input);
                printResponse("added: " + input);
            }
            input = reader.nextLine();
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
