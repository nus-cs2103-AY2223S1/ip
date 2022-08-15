import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontalLn = "_____________________________________________________________";

        System.out.println(horizontalLn);
        System.out.printf("Hey there! I'm Bob\nWhat can I do for you?\n");
        System.out.println(horizontalLn);

        List<Task> toDoList = new ArrayList<>();
        int toDoListCount  = 0;

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String firstCommand = splitInput[0];

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")){
                System.out.println(horizontalLn);
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < toDoListCount; i ++) {
                    System.out.println(i+1 + "." + toDoList.get(i).toString());
                }
                System.out.println(horizontalLn);
            } else if (firstCommand.equalsIgnoreCase("mark")) {

                // Here we assume a valid number is keyed into the 2nd argument
                // -1 to string as the ArrayList is zero indexed
                int itemNumber = Integer.parseInt(splitInput[1]) - 1;
                toDoList.get(itemNumber).markDone();
                System.out.println(horizontalLn);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(toDoList.get(itemNumber).toString());
                System.out.println(horizontalLn);

            } else if (firstCommand.equalsIgnoreCase("unmark")) {
                // Here we assume a valid number is keyed into the 2nd argument
                // -1 to string as the ArrayList is zero indexed
                int itemNumber = Integer.parseInt(splitInput[1]) - 1;
                toDoList.get(itemNumber).markUndone();
                System.out.println(horizontalLn);
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println("[" + toDoList.get(itemNumber).getStatusIcon() + "] "
                        + toDoList.get(itemNumber).description);
                System.out.println(horizontalLn);

            } else {

                if (firstCommand.equalsIgnoreCase("todo")) {
                    ToDo toBeAddedToDo = new ToDo(splitInput[1]);
                    toDoList.add(toBeAddedToDo);
                    System.out.println(toBeAddedToDo);
                } else if (firstCommand.equalsIgnoreCase("deadline")) {
                    String[] furtherSplitInput = splitInput[1].split("/by", 2);
                    Deadline toBeAddedDeadline = new Deadline(furtherSplitInput[0], furtherSplitInput[1]);
                    toDoList.add(toBeAddedDeadline);
                    System.out.println(toBeAddedDeadline);
                } else if (firstCommand.equalsIgnoreCase("event")) {
                    String[] furtherSplitInput = splitInput[1].split("/at", 2);
                    Event toBeAddedEvent = new Event(furtherSplitInput[0], furtherSplitInput[1]);
                    toDoList.add(toBeAddedEvent);
                    System.out.println(toBeAddedEvent);
                } else {
                    System.out.println("Please input a valid event type and event");
                    System.out.println(horizontalLn);
                    continue;
                }
                
                toDoListCount++;
                System.out.println("Now you have " + toDoListCount + " tasks in the list.");
                System.out.println(horizontalLn);
            }
        }

        sc.close();
    }
}
