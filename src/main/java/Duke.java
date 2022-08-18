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
            try {
                String input = scanner.nextLine();
                //split the input by whitespace
                String[] splitted = input.split("\\s", 2);
                //command is first word of the input
                String command = splitted[0];
                int index;
                Task task;

                switch (command) {
                    case "todo":
                        //No Description Given
                        if (splitted.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        String taskString = splitted[1];
                        task = new ToDo(taskString);
                        taskList.add(task);
                        printTaskAdded(task, taskList.size());
                        break;

                    case "deadline":
                        //No Description Given
                        if (splitted.length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        //some regex to parse the strings correctly
                        //0th index: task, 1st index: deadline
                        String[] splittedDeadline = splitted[1].split("\\s/by\\s", 2);
                        String deadlineTask = splittedDeadline[0];
                        //No Description Given
                        if (deadlineTask.equals("") || deadlineTask.startsWith("/by")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }

                        //No Deadline Given
                        if (splittedDeadline.length == 1) {
                            throw new DukeException("please specify a deadline");
                        }

                        String deadlineDate = splittedDeadline[1];

                        task = new Deadline(deadlineTask, deadlineDate);
                        taskList.add(task);
                        printTaskAdded(task, taskList.size());
                        break;

                    case "event":
                        //No Description Given
                        if (splitted.length < 2) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        //some regex to parse the strings correctly
                        //0th index: event, 1st index: date
                        String[] splittedEvent = input.split("\\s", 2)
                                [1].split("\\s/at\\s", 2);
                        String eventString = splittedEvent[0];

                        //No Description Given
                        if (eventString.equals("") || eventString.startsWith("/at")) {
                            throw new DukeException("The description of an event cannot be empty.");
                        }

                        //No Deadline Given
                        if (splittedEvent.length == 1) {
                            throw new DukeException("please specify a date");
                        }

                        String eventDate = splittedEvent[1];

                        task = new Event(eventString, eventDate);
                        taskList.add(task);
                        printTaskAdded(task, taskList.size());
                        break;

                    case "mark":
                        // No index given
                        if (splitted.length < 2) {
                            throw new DukeException("No Index Given");
                        }
                        //the index should be the "2nd word"
                        index = Integer.parseInt(splitted[1]);
                        //Index out of bounds
                        if (index > taskList.size() || index < 1) {
                            throw new DukeException("Index Is Not Valid");
                        }
                        //get the selected task
                        task = taskList.get(index - 1);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                        break;

                    case "unmark":
                        //No Index Given
                        if (splitted.length < 2) {
                            throw new DukeException("No Index Given");
                        }
                        //the index should be the "2nd word"
                        index = Integer.parseInt(splitted[1]);
                        //Index out of bounds
                        if (index > taskList.size() || index < 1) {
                            throw new DukeException("Index Is Not Valid");
                        }
                        //get the selected task
                        task = taskList.get(index - 1);
                        task.markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task);
                        break;

                    case "delete":
                        //No Index Given
                        if (splitted.length < 2) {
                            throw new DukeException("No Index Given");
                        }
                        //the index should be the "2nd word"
                        index = Integer.parseInt(splitted[1]);
                        //Index out of bounds
                        if (index > taskList.size() || index < 1) {
                            throw new DukeException("Index Is Not Valid");
                        }
                        //get the selected task
                        task = taskList.get(index - 1);
                        //remove the task
                        taskList.remove(task);
                        //print the response to the user
                        System.out.println("Noted. I have removed this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        break;

                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        shouldContinue = false;
                        break;

                    case "list":
                        printTaskList(taskList);
                        break;


                    default:
                        throw new DukeException("Command Not Found!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
