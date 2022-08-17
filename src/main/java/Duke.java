import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void printTaskList(ArrayList<Task> taskList) {
        for(int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        String logo = "Botto";
        System.out.println("Hello from " + logo + "\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;

        while(shouldContinue) {
            String input = scanner.nextLine();
            //split the input by whitespace
            String[] splitted = input.split("\\s");
            //command is first word of the input
            String command = splitted[0];

            switch(command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    shouldContinue = false;
                    break;

                case "list":
                    printTaskList(taskList);
                    break;

                default:
                    Task newTask = new Task(input);
                    taskList.add(new Task(input));
                    System.out.println("added: " + newTask.toString());
                    break;
            }
        }

    }
}
