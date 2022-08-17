import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
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
            int index;
            Task task;

            switch(command) {
                case "mark":
                    //the index should be the "2nd word"
                    index = Integer.parseInt(splitted[1]);
                    //get the selected task
                    task = taskList.get(index - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;

                case "unmark":
                    //the index should be the "2nd word"
                    index = Integer.parseInt(splitted[1]);
                    //get the selected task
                    task = taskList.get(index - 1);
                    task.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    break;

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
