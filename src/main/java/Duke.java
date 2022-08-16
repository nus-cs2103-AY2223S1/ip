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

        try {
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" ", 2);
                String firstCommand = splitInput[0];

                if (input.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(i + 1 + "." + toDoList.get(i).toString());
                    }
                    System.out.println(horizontalLn);
                } else if (firstCommand.equalsIgnoreCase("mark")) {

                    // Here we assume a valid number is keyed into the 2nd argument
                    // -1 to string as the ArrayList is zero indexed
                    int itemNumber = Integer.parseInt(splitInput[1]) - 1;
                    toDoList.get(itemNumber).markDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(toDoList.get(itemNumber).toString());
                    System.out.println(horizontalLn);

                } else if (firstCommand.equalsIgnoreCase("unmark")) {
                    // Here we assume a valid number is keyed into the 2nd argument
                    // -1 to string as the ArrayList is zero indexed
                    int itemNumber = Integer.parseInt(splitInput[1]) - 1;
                    toDoList.get(itemNumber).markUndone();
                    System.out.println("OK, I've marked this task as not done yet");
                    System.out.println("[" + toDoList.get(itemNumber).getStatusIcon() + "] "
                            + toDoList.get(itemNumber).description);
                    System.out.println(horizontalLn);

                } else {

                    if (firstCommand.equalsIgnoreCase("todo")) {
                        if (splitInput.length == 1) {
                            throw new IncompleteInputException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        ToDo toBeAddedToDo = new ToDo(splitInput[1]);
                        toDoList.add(toBeAddedToDo);
                        System.out.println(toBeAddedToDo);
                    } else if (firstCommand.equalsIgnoreCase("deadline")) {
                        String[] furtherSplitInput = splitInput[1].split("/by", 2);
                        if (furtherSplitInput.length <= 1) {
                            throw new IncompleteInputException("OOPS!! Please state the deadline and use " +
                                    "/by in command");
                        }
                        Deadline toBeAddedDeadline = new Deadline(furtherSplitInput[0], furtherSplitInput[1]);
                        toDoList.add(toBeAddedDeadline);
                        System.out.println(toBeAddedDeadline);
                    } else if (firstCommand.equalsIgnoreCase("event")) {
                        String[] furtherSplitInput = splitInput[1].split("/at", 2);
                        if (furtherSplitInput.length <= 1) {
                            throw new IncompleteInputException("OOPS!! Please state the duration and use " +
                                    "/at in command");
                        }
                        Event toBeAddedEvent = new Event(furtherSplitInput[0], furtherSplitInput[1]);
                        toDoList.add(toBeAddedEvent);
                        System.out.println(toBeAddedEvent);
                    } else if (firstCommand.equalsIgnoreCase("delete")) {
                        int itemNumber = Integer.parseInt(splitInput[1]) - 1;
                        System.out.println("Noted. I've removed this task: " +
                                toDoList.get(itemNumber));
                        toDoList.remove(itemNumber);


                    } else {
                        throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
                    System.out.println(horizontalLn);
                }
            }
        } catch(InvalidInputException e) {
            System.out.println(e.getMessage());
            System.out.println(horizontalLn);
        } catch(IncompleteInputException e) {
            System.out.println(e.getMessage());
            System.out.println(horizontalLn);
        } finally {
            sc.close();
        }

    }
}
