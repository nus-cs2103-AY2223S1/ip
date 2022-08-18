import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int eleCount = 0;

        while (sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandBreakdown = command.split(" ");

            switch(commandBreakdown[0]) {
                case "list":
                    for (int i = 0; i < eleCount; i++) {
                        //System.out.println((i+1) + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].toString());
                        System.out.println(String.format("%d. %s", i+1, tasks[i].toString()));
                    }
                    break;
                case "mark":
                    int taskNo = Integer.valueOf(commandBreakdown[1])-1;
                    tasks[taskNo].markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("%s", tasks[taskNo].toString()));
                    break;
                case "unmark":
                    taskNo = Integer.valueOf(commandBreakdown[1])-1;
                    tasks[taskNo].markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(String.format("%s", tasks[taskNo].toString()));
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
                    tasks[eleCount] = new ToDo(todoName);
                    eleCount++;
                    System.out.println("added: " + todoName);
                    System.out.println(String.format("Now you have %d tasks in the list", eleCount));
                    break;
                case "deadline":
                    String[] deadlineSplit = command.split(" /by ");
                    Deadline deadline = new Deadline(deadlineSplit[0].substring(9, deadlineSplit[0].length()), deadlineSplit[1]);
                    tasks[eleCount] = deadline;
                    eleCount++;
                    System.out.println("added: " + deadline.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", eleCount));
                    break;
                case "event":
                    String[] eventSplit = command.split(" /at ");
                    Event event = new Event(eventSplit[0].substring(6, eventSplit[0].length()), eventSplit[1]);
                    tasks[eleCount] = event;
                    eleCount++;
                    System.out.println("added: " + event.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", eleCount));
                    break;
                default:
                    System.out.println(new UnknownCommandException());
            }

        }

    }
}
