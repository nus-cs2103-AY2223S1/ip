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
