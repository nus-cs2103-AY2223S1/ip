import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        System.out.println("Hello! I'm Don\nHow may I help you?");
        while (sc.hasNextLine()) {
            String reply = sc.nextLine();
            String[] response = reply.split(" ");
            Response firstWord = Response.valueOf(response[0].toUpperCase());
            switch (firstWord) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case LIST:
                System.out.println("Here are the tasks in your list:");
                for (int index = 0; index < listOfTasks.size(); index++) {
                    System.out.println(index+1 + "." + listOfTasks.get(index).toString());
                }
                break;

            case MARK:
                try {
                    int taskIndex = Integer.parseInt(response[1]) - 1;
                    listOfTasks.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + "[" + listOfTasks.get(taskIndex).getStatusIcon() + "] " + listOfTasks.get(taskIndex).getDescription());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing an index for mark.", e);
                }
                break;

            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(response[1]) - 1;
                    listOfTasks.get(taskIndex).markAsNotDone();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + listOfTasks.get(taskIndex).getStatusIcon() + "] " + listOfTasks.get(taskIndex).getDescription());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing an index for unmark.", e);
                }
                break;

            case TODO:
                try {
                    String toDoTaskDescription = reply.substring(5);
                    Todo toDoTask = new Todo(toDoTaskDescription);
                    listOfTasks.add(toDoTask);
                    System.out.println("Got it. I've added this task:\n" + toDoTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e);
                }
                break;

            case DEADLINE:
                try {
                    String deadlineDescriptionWithDate = reply.substring(9);
                    String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                    String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                    Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                    listOfTasks.add(deadlineTask);
                    System.out.println("Got it. I've added this task:\n" + deadlineTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.", e);
                }
                break;

            case EVENT:
                try {
                    String eventDescriptionWithDate = reply.substring(6);
                    String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                    String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                    Event eventTask = new Event(eventDescription, eventDate);
                    listOfTasks.add(eventTask);
                    System.out.println("Got it. I've added this task:\n" + eventTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.", e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                }
                break;

            case DELETE:
                try {
                    int deleteIndex = Integer.parseInt(response[1]) - 1;
                    Task deletedTask = listOfTasks.get(deleteIndex);
                    listOfTasks.remove(deleteIndex);
                    System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! You're missing an index for delete.", e);
                }
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
