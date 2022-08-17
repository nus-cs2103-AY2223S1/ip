import java.util.HashMap;
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

            /* Handles Keyword Mapping */
            String keywordInput = inputTokens[0];
            Keyword keyword;
            try {
                keyword = Keyword.getKeyword(keywordInput);
            }
            catch (IllegalArgumentException iae) {
                // Invalid Keyword Input
                System.out.println(iae.getMessage());
                continue;
            }

            /* Exits Loop */
            if (keyword == Keyword.BYE) {
                sayGoodbye();
                break;
            }

            /* Handles marking tasks as done or not done */
            if (keyword ==  keyword.MARK || keyword == Keyword.UNMARK) {
               try {
                   String indexString = inputTokens[1]; // Throws AIOOBE
                   markUnmarkTask(taskList, indexString, keyword);
               }
               catch (ArrayIndexOutOfBoundsException e) {
                   System.out.println("\tRemember to add a Task Number!");
               }
               finally {
                   continue;
               }
            }

            /* Handle deletion of tasks */
            if (keyword == Keyword.DELETE) {
                try {
                    String indexString = inputTokens[1]; // Throws AIOOBE
                    deleteTask(taskList, indexString);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("\tRemember to add a Task Number!");
                }
                finally {
                    continue;
                }
            }

            /* Handles outputting of Task List */
            if (keyword == Keyword.LIST) {
                displayTaskList(taskList);
                continue;
            }

            /* Handles creation of new tasks */
            String content;

            // invalid content check
            try {
                // Retrieve input excluding keyword
                content = inputTokens[1]; // Throws AIOOBE
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\tHey! Did you forget to add a task name?");
                continue;
            }

            // Add Task based on taskType
            Task taskAdded = null;

            switch (keyword) {
                case TODO : {
                     taskAdded = new ToDo(content);
                     taskList.add(taskAdded);
                     break;
                }
                case DEADLINE: {
                    String[] taskTokens = content.split("/by "); // delimit over "/by" to retrieve deadline
                    try {
                        // If delimiting regex is not found, taskTokens returns single item array with the original string
                        String taskName = taskTokens[0];
                        String deadline = taskTokens[1]; // Throws AIOOBE
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
                case EVENT : {
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
            }

            /* Handles success message output */
            successMessage(taskAdded, taskList.size());
        }
    }

    /**
     * Outputs greeting message to user.
     */
    private static void greetUser(){
        System.out.println("\tHey there! I'm Tutter! \n\tHow can I help?");
    }

    /**
     * Outputs goodbye message to user.
     */
    private static void sayGoodbye() {
        System.out.println("\tGoodbye!");
    }

    /**
     * Outputs success message to user when task is added successfully.
     * @param task Task object that was added to the task list.
     * @param size Number of tasks in the task list.
     */
    private static void successMessage(Task task, int size) {
        if (task != null) {
            String output = String.format("\tYou have added \"%s\" into your Task List!\n" +
                    "\tYou have %d tasks in your Task List!", task, size);
            System.out.println(output);
        }
    }

    /**
     * Outputs all tasks in task list.
     * @param taskList Task list containing tasks to be outputted.
     */
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

    /**
     * Set task status of task at given index of task list as completed or not completed.
     * @param taskList Task list containing task to be marked.
     * @param indexString String input containing index of task to be marked.
     * @param keyword Keyword value to determine whether to mark or unmark the task.
     */
    private static void markUnmarkTask(ArrayList<Task> taskList, String indexString, Keyword keyword) {
        try {
            int index = Integer.parseInt(indexString); // Throw NFE if invalid int
            Task task = taskList.get(index - 1); // Throws IOOBE if invalid index

            // Mark as done or not done
            if (keyword == Keyword.MARK) {
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
        catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tSorry, that Task Number doesn't look right...");
        }
    }

    /**
     * Deletes task at given index from task list.
     * @param taskList Task list containing task to be deleted.
     * @param indexString String input containing index of task to be deleted.
     */
    private static void deleteTask(ArrayList<Task> taskList, String indexString) {
        try {
            int index = Integer.parseInt(indexString); // Throw NFE if invalid int
            Task task = taskList.get(index - 1); // Throws IOOBE if invalid index
            taskList.remove(index - 1);

            String output = String.format("\tYou have deleted \"%s\" into your Task List!\n" +
                    "\tYou have %d tasks in your Task List!", task, taskList.size());
            System.out.println(output);
        }
        catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("\tSorry, that Task Number doesn't look right...");
        }
    }
}


