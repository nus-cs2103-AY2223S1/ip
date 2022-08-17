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

    private static void printTaskAdded(Task task, int taskNumber) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskNumber + " tasks in the list.");
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
                case "todo":
                    //get the task by splitting input
                    String taskString = input.split("\\s", 2)[1];
                    task = new ToDo(taskString);
                    taskList.add(task);
                    printTaskAdded(task, taskList.size());
                    break;

                case "deadline":
                    //some regex to parse the strings correctly
                    //0th index: task, 1st index: deadline
                    String[] splittedDeadline = input.split("\\s", 2)
                            [1].split("\\s/by\\s", 2);
                    String deadlineTask = splittedDeadline[0];
                    String deadlineDate = splittedDeadline[1];

                    task = new Deadline(deadlineTask, deadlineDate);
                    taskList.add(task);
                    printTaskAdded(task, taskList.size());
                    break;

                case "event":
                    //some regex to parse the strings correctly
                    //0th index: event, 1st index: date
                    String[] splittedEvent = input.split("\\s", 2)
                            [1].split("\\s/at\\s", 2);
                    String eventString = splittedEvent[0];
                    String eventDate = splittedEvent[1];

                    task = new Event(eventString, eventDate);
                    taskList.add(task);
                    printTaskAdded(task, taskList.size());
                    break;

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
                   System.out.println("Error: Not A Valid Command!!!");
                    break;
            }
        }

    }
}
