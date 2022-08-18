import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String EXITCOMMAND = "bye";
    private static final String LINE = "\n========================================================";
    private static final String WELCOMEMESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXITMESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!" + LINE;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + LINE + "\n" + WELCOMEMESSAGE + LINE);

        // ArrayList to store user inputs
        List<Task> listOfInputs = new ArrayList<>();
        // Array to parse the input content
        String[] inputArray = null;

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase(EXITCOMMAND)) { // Stops loop if command is 'bye'
            try {
                inputArray = input.split(" ");
                // Check if user input is 'list'
                if (input.equalsIgnoreCase("list")) { // List all inputs typed so far
                    if (listOfInputs.size() == 0) { // List is empty
                        System.out.println(LINE + "\nYour list is empty! Why not add a task to it first?" + LINE);
                        input = sc.nextLine();
                        continue;
                    } else {
                        System.out.println(LINE + "\nHere are the tasks in your list:");
                        for (int i = 0; i < listOfInputs.size(); i++) {
                            Task curTask = listOfInputs.get(i);
                            System.out.println((i + 1) + "." + curTask.toString());
                        }
                        System.out.println(LINE);
                    }
                    input = sc.nextLine();
                    continue;
                    // Check if user input is to mark a task
                } else if (inputArray.length == 2 & inputArray[0].equalsIgnoreCase("mark")) {
                    int taskNum = Integer.parseInt(inputArray[1]) - 1;
                    Task taskChosen = listOfInputs.get(taskNum);
                    taskChosen.markAsDone();
                    System.out.println(LINE + "\nOkay, I have marked this task as done: \n"
                            + taskChosen + LINE);
                    input = sc.nextLine();
                    continue;
                    // Check if user input is to unmark a task
                } else if (inputArray.length == 2 & inputArray[0].equalsIgnoreCase("unmark")) {
                    int taskNum = Integer.parseInt(inputArray[1]) - 1;
                    Task taskChosen = listOfInputs.get(taskNum);
                    taskChosen.markAsUndone();
                    System.out.println(LINE + "\nOkay, I have marked this task as not done: \n"
                            + taskChosen + LINE);
                    input = sc.nextLine();
                    continue;
                }

                switch (inputArray[0].toUpperCase()) {
                    case "TODO":
                        if (inputArray.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty" + LINE);
                        }
                        String[] taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                        Todo newToDo = new Todo(String.join(" ", taskDesc));
                        listOfInputs.add(newToDo);
                        System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newToDo
                                + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                        input = sc.nextLine();
                        continue;
                    case "DEADLINE":
                        taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                        taskDesc = String.join(" ", taskDesc).split("/by");
                        Deadline newDeadline = new Deadline(taskDesc[0], taskDesc[1]);
                        listOfInputs.add(newDeadline);
                        System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newDeadline
                                + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                        input = sc.nextLine();
                        continue;
                    case "EVENT":
                        taskDesc = Arrays.copyOfRange(inputArray, 1, inputArray.length);
                        taskDesc = String.join(" ", taskDesc).split("/at");
                        Event newEvent = new Event(taskDesc[0], taskDesc[1]);
                        listOfInputs.add(newEvent);
                        System.out.println(LINE + "\nGot it! I have added this task to your list:\n  " + newEvent
                                + "\nNow you have " + listOfInputs.size() + " tasks in the list." + LINE);
                        input = sc.nextLine();
                        continue;
                }
                throw new DukeException("I'm sorry, but I don't know what that means" + LINE);
            } catch (DukeException err) {
                System.out.println(LINE + "\n:( OOPS! " + err.getMessage());
                input = sc.nextLine();
            }
        }

        System.out.println(EXITMESSAGE);
    }
}
