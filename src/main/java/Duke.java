import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Personal Assistant Chatbot that helps a person keep track of several things like tasks.
 *
 */
public class Duke {

    /**
     * Returns String date in a nicer format.
     *
     * @param date
     * @return String date in nicer format
     */
    public static String getDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandBreakdown = command.split(" ");

            switch(commandBreakdown[0]) {
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        //System.out.println((i+1) + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].toString());
                        System.out.println(String.format("%d. %s", i+1, taskList.get(i).toString()));
                    }
                    break;
                case "mark":
                    int taskNo = Integer.valueOf(commandBreakdown[1])-1;
                    taskList.get(taskNo).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("%s", taskList.get(taskNo).toString()));
                    break;
                case "unmark":
                    taskNo = Integer.valueOf(commandBreakdown[1])-1;
                    taskList.get(taskNo).markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(String.format("%s", taskList.get(taskNo).toString()));
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "todo":
                    String todoName = "";
                    if (commandBreakdown.length == 1) {
                        System.out.println(new EmptyTodoException());
                        break;
                    }
                    for (int i = 1; i < commandBreakdown.length; i++) {
                        todoName = todoName + commandBreakdown[i] + " ";
                    }
                    taskList.add(new ToDo(todoName));
                    System.out.println("added: " + todoName);
                    System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
                    break;
                case "deadline":
                    String[] deadlineSplit = command.split(" /by ");
                    String formattedDate = Duke.getDate(deadlineSplit[1]);
                    Deadline deadline = new Deadline(deadlineSplit[0].substring(9, deadlineSplit[0].length()), formattedDate);
                    taskList.add(deadline);
                    System.out.println("added: " + deadline.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
                    break;
                case "event":
                    String[] eventSplit = command.split(" /at ");
                    formattedDate = Duke.getDate(eventSplit[1]);
                    Event event = new Event(eventSplit[0].substring(6, eventSplit[0].length()), formattedDate);
                    taskList.add(event);
                    System.out.println("added: " + event.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
                    break;
                case "delete":
                    int indToDelete = Integer.valueOf(commandBreakdown[1]) - 1;
                    Task toDelete = taskList.get(indToDelete);

                    taskList.remove(indToDelete);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(toDelete.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", taskList.size()));
                    break;
                default:
                    System.out.println(new UnknownCommandException());
            }

        }

    }
}
