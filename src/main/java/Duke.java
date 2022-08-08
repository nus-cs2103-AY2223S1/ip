import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {

    private static List<Task> dukeInputs = new ArrayList<>();
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    private static final String LIST_HEADER = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";

    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";

    private static final List<String> PERMISSABLE_TASKS = new ArrayList<>(Arrays.asList("todo", "event", "deadline"));




    public static void main(String[] args) throws InvalidCommandException, EmptyTaskException {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);


            System.out.println("Hello! I'm Duke \n" + "What can I do for you?");

            Scanner sc = new Scanner(System.in);
            String input;
            while (true) {
                // Read the input
                input = sc.nextLine();

                if (input.startsWith("mark")) {
                    //split by space, then the second value
                    int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                    dukeInputs.get(taskIndex).setDone();
                    Task currentTask = dukeInputs.get(taskIndex);

                    System.out.println(DONE_MESSAGE);
                    System.out.println("  " + dukeInputs.get(taskIndex));

                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                    dukeInputs.get(taskIndex).removeDone();
                    Task currentTask = dukeInputs.get(taskIndex);

                    System.out.println(UNDONE_MESSAGE);
                    System.out.println("  " + dukeInputs.get(taskIndex));

                } else if (input.equals("bye")) {
                    System.out.println(ENDING_MESSAGE);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(LIST_HEADER);
                    for (int i = 0; i < dukeInputs.size(); i++) {
                        Task currentTask = dukeInputs.get(i);
                        System.out.println(i + 1 + ". " + currentTask);
                    }
                }

                //handles the addition of tasks
                else {
                    validateTask(input);
                    Task newTask = GenerateTask(input);
                    dukeInputs.add(newTask);
                    System.out.println("Got it. I've added this task:\n" + " " + newTask);
                    System.out.println("Now you have " + dukeInputs.size() + " tasks in the list");

                }
            }
        }

        private static void validateTask(String input) throws InvalidCommandException, EmptyTaskException {
            String tempArr[] = input.split(" ", 0); //splits into words

            //first word is invalid
            if (! PERMISSABLE_TASKS.contains(tempArr[0])) {
                throw new InvalidCommandException("I'm sorry, I don't understand what that means \n"
                       + "Please enter a valid response in the future");
            }

            if (tempArr.length <= 1) {
                throw new EmptyTaskException("The description of a task cannot be empty.");
            }

        }


        private static Task GenerateTask (String input) {
            String tempArr[] = input.split(" ", 2);
            if (input.startsWith("todo")) {
                return new Todo(tempArr[1]);
            } else if (input.startsWith("deadline")) {
                int firstSplit = tempArr[1].indexOf("/by");
                String eventName = tempArr[1].substring(0, firstSplit);
                String date = tempArr[1].substring(firstSplit + 3);
                return new Deadline(eventName, date);
            } else { //must be Event
                int firstSplit = tempArr[1].indexOf("/at");
                String eventName = tempArr[1].substring(0, firstSplit);
                String date = tempArr[1].substring(firstSplit + 3);
                return new Event(eventName, date);
            }
        }
}

