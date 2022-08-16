import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Helper Fields
        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();

        // Greets User
        greetUser();

        while (true) {
            input = sc.nextLine();
            String[] inputTokens = input.split(" ", 2); // Delimit over " " to extract first keyword

            // Exits Loop
            if (input.equals("bye")) {
                break;
            }

            // Handles marking tasks as done or not done
            if (inputTokens[0].equals("mark") || inputTokens[0].equals("unmark")) {
                int index;
                try {
                    index = Integer.parseInt(inputTokens[1]); // Throw NFE if invalid int
                    Task task = taskList.get(index - 1); // Throws IOOBE if invalid index

                    // Mark as done or not done
                    if (inputTokens[0].equals("mark")) {
                        task.markAsDone();
                        String taskListString = String.format("\tGood Job! The following task " +
                                "has been marked as done:\n\t%s", task);
                        System.out.println(taskListString);
                    } else {
                        task.markAsNotdone();
                        String taskListString = String.format("\tOkay! The following task " +
                                "has been marked as not done:\n\t%s", task);
                        System.out.println(taskListString);
                    }
                }
                catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("\tSorry, that Task Number doesn't look right...");
                }
                finally {
                    continue;
                }
            }

            // Outputs Task List
            if (input.equals("list")) {
                displayTaskList(taskList);
                continue;
            }

            // Add into task list
            String taskType = inputTokens[0]; // Deadlines, Events or ToDos
            Task taskAdded = null;
            switch (taskType) {
                case ("todo") : {
                    taskAdded = new ToDo(inputTokens[1]);
                    taskList.add(taskAdded);
                    break;
                }
                case ("deadline") : {
                    String content = inputTokens[1]; // Retrieve input excluding keyword
                    String[] taskTokens = content.split("/by "); // delimit over "/by" to retrieve deadline
                    try {
                        // If delimiting regex is not found, taskTokens returns single item array with the original string
                        String taskName = taskTokens[0];
                        String deadline = taskTokens[1];
                        taskAdded = new Deadline(taskName, deadline);
                        taskList.add(taskAdded);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\tDeadline not found! Please input in the following format: " +
                                "deadline <Task Name> /by <Deadline> ");
                    }
                    finally {
                        break;
                    }
                }
                case ("event") : {
                    String content = inputTokens[1]; // Retrieve input excluding keyword
                    String[] taskTokens = content.split("/at "); // delimit over "/at" to retrieve deadline
                    try {
                        // If delimiting regex is not found, taskTokens returns single item array with the original string
                        String taskName = taskTokens[0];
                        String eventTiming = taskTokens[1];
                        taskAdded = new Event(taskName, eventTiming);
                        taskList.add(taskAdded);
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\tEvent Timing not found! Please input in the following format: " +
                                "event <Task Name> /at <Event Timing> ");
                    }
                    finally {
                        break;
                    }
                }
                default : {
                    System.out.println("\tSorry, I didn't understand that keyword");
                }
            }

            // Handles output
            successMessage(taskAdded, taskList.size());
        }

        // Exit Statement
        sayGoodbye();
    }

    private static void greetUser(){
        System.out.println("\tHey there! I'm Tutter! \n\tHow can I help?");
    }

    private static void sayGoodbye() {
        System.out.println("\tGoodbye!");
    }

    private static void successMessage(Task task, int size) {
        if (task != null) {
            String output = String.format("\tYou have added \"%s\" into your Task List!\n" +
                    "\tYou have %d tasks in your Task List!", task, size);
            System.out.println(output);
        }
    }

    private static void displayTaskList(ArrayList<Task> taskList) {
        int i = 1;
        for (Task t : taskList) {

            // Reach end of list
            if (t == null) {
                break;
            }

            String taskListString = String.format("\t%d. %s", i, t);
            System.out.println(taskListString);
            i++;
        }
    }
}


