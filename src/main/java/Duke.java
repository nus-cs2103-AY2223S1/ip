import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] listOfTasks = new Task[100];
        int indexOfNextTask = 0;
        System.out.println("Hello! I'm Don\nHow may I help you?");
        while (sc.hasNextLine()) {
            String response = sc.nextLine();
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int index = 0; index < indexOfNextTask; index++) {
                    System.out.println(index+1 + "." + listOfTasks[index].toString());
                }
            }
            else if (response.length() > 3 && response.substring(0, 4).equals("mark")) {
                int taskIndex = Integer.parseInt(response.substring(5, response.length())) - 1;
                listOfTasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
            }
            else if (response.length() > 5 && response.substring(0, 6).equals("unmark")) {
                int taskIndex = Integer.parseInt(response.substring(7, response.length())) - 1;
                listOfTasks[taskIndex].markAsNotDone();
                System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
            }
            else if (response.length() > 3 && response.substring(0, 4).equals("todo")) {
                String toDoTaskDescription = response.substring(5, response.length());
                Todo toDoTask = new Todo(toDoTaskDescription);
                listOfTasks[indexOfNextTask++] = toDoTask;
                System.out.println("Got it. I've added this task:\n" + toDoTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
            }
            else if (response.length() > 7 && response.substring(0,8).equals("deadline")) {
                String deadlineDescriptionWithDate = response.substring(9, response.length());
                String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                listOfTasks[indexOfNextTask++] = deadlineTask;
                System.out.println("Got it. I've added this task:\n" + deadlineTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
            }
            else if (response.length() > 4 && response.substring(0,5).equals("event")) {
                String eventDescriptionWithDate = response.substring(6, response.length());
                String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                Event eventTask = new Event(eventDescription, eventDate);
                listOfTasks[indexOfNextTask++] = eventTask;
                System.out.println("Got it. I've added this task:\n" + eventTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
            }
        }
    }
}
