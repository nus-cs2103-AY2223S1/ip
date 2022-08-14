import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Task[] listOfTasks = new Task[100];
        int indexOfNextTask = 0;
        System.out.println("Hello! I'm Don\nHow may I help you?");
        while (sc.hasNextLine()) {
            String reply = sc.nextLine();
            String[] response = reply.split(" ");
            if (response[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (response[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int index = 0; index < indexOfNextTask; index++) {
                    System.out.println(index+1 + "." + listOfTasks[index].toString());
                }
            }
            else if (response[0].equals("mark")) {
                try {
                    int taskIndex = Integer.parseInt(response[1]) - 1;
                    listOfTasks[taskIndex].markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing an index for mark.", e);
                }
            }
            else if (response[0].equals("unmark")) {
                try {
                    int taskIndex = Integer.parseInt(response[1]) - 1;
                    listOfTasks[taskIndex].markAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + listOfTasks[taskIndex].getStatusIcon() + "] " + listOfTasks[taskIndex].getDescription());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing an index for unmark.", e);
                }
            }
            else if (response[0].equals("todo")) {
                try {
                    String toDoTaskDescription = reply.substring(5, reply.length());
                    Todo toDoTask = new Todo(toDoTaskDescription);
                    listOfTasks[indexOfNextTask++] = toDoTask;
                    System.out.println("Got it. I've added this task:\n" + toDoTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e);
                }
            }
            else if (response[0].equals("deadline")) {
                try {
                    String deadlineDescriptionWithDate = reply.substring(9, reply.length());
                    String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                    String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                    listOfTasks[indexOfNextTask++] = deadlineTask;
                    System.out.println("Got it. I've added this task:\n" + deadlineTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.", e);
                }
            }
            else if (response[0].equals("event")) {
                try {
                    String eventDescriptionWithDate = reply.substring(6, reply.length());
                    String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                    String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                    Event eventTask = new Event(eventDescription, eventDate);
                    listOfTasks[indexOfNextTask++] = eventTask;
                    System.out.println("Got it. I've added this task:\n" + eventTask.toString() + "\nNow you have " + indexOfNextTask + (indexOfNextTask == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.", e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                }
            }
            else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
