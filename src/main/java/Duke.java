import java.util.Scanner;

public class Duke {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Sorry, I don't know what that means.\n"
            + "Did you make a mistake? Please note that commands are case-sensitive.";

    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);

        while (!exitCalled) {
            /* Splits a user query into two, the command and its arguments,
               assumed to be seperated by whitespace (can be multiple characters).
             */
            String[] userQuery = sysIn.nextLine().split("\\s+", 2);

            if (userQuery.length == 0) {
                // TODO: Add Exception when this occurs.
                System.out.println(UNKNOWN_COMMAND_MESSAGE);
                continue;
            }

            switch (userQuery[0]) {
                case "list": {
                    String[] stringifiedTaskList = taskList.toStringList();
                    System.out.println("Here are your tasks that I have recorded:");
                    if (stringifiedTaskList.length == 0) {
                        System.out.println("Congratulations, you don't need to do anything right now!");
                    }
                    for (int i = 0; i < stringifiedTaskList.length; i++) {
                        System.out.printf("%02d. %s\n", i + 1, stringifiedTaskList[i]);
                    }
                    break;
                }

                case "bye": {
                    System.out.println(GOODBYE_MESSAGE);
                    exitCalled = true;
                    break;
                }

                // TODO: A lot of code repetition for the next four pieces of code... Not sure how to resolve yet.
                case "add": {
                    Task newTask = new Task(userQuery[1]);
                    taskList.addTask(newTask);
                    System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newTask, taskList.getLength());
                    break;
                }

                case "todo": {
                    Todo newTodo = new Todo(userQuery[1]);
                    taskList.addTask(newTodo);
                    System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newTodo, taskList.getLength());
                    break;
                }

                case "deadline": {
                    // Matches the String by the /by keyword and splits it.
                    String[] userParams = userQuery[1].split("\\s+/by\\s+", 2);
                    // TODO: Add Exception when a parameter isn't passed
                    Deadline newDeadline = new Deadline(userParams[0], userParams[1]);
                    taskList.addTask(newDeadline);
                    System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newDeadline, taskList.getLength());
                    break;
                }

                case "event": {
                    // Matches the String by the /at keyword and splits it.
                    String[] userParams = userQuery[1].split("\\s+/at\\s+", 2);
                    // TODO: Add Exception when a parameter isn't passed
                    Event newEvent = new Event(userParams[0], userParams[1]);
                    taskList.addTask(newEvent);
                    System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newEvent, taskList.getLength());
                    break;
                }

                case "mark": {
                    // TODO: Check for non-integer inputs.
                    int markIndex = Integer.parseInt(userQuery[1]) - 1;
                    if (markIndex >= taskList.getLength() || markIndex < 0) {
                        System.out.println("I do not have a task with that number in my list.");
                    } else if (taskList.getTask(markIndex).getIsDone()) {
                        System.out.printf("Sorry, but it seems you have marked this task as done:\n  %s\n", taskList.getTask(markIndex));
                    } else {
                        taskList.getTask(markIndex).setDone(true);
                        System.out.printf("Noice! I've marked this task as done:\n  %s\n", taskList.getTask(markIndex));
                    }
                    break;
                }

                case "unmark": {
                    // TODO: Check for non-integer inputs.
                    int unmarkIndex = Integer.parseInt(userQuery[1]) - 1;
                    if (unmarkIndex >= taskList.getLength() || unmarkIndex < 0) {
                        System.out.println("I do not have a task with that number in my list.");
                    } else if (taskList.getTask(unmarkIndex).getIsDone()) {
                        taskList.getTask(unmarkIndex).setDone(false);
                        System.out.printf("Alright, I've marked this task as not done:\n  %s\n", taskList.getTask(unmarkIndex));
                    } else {
                        System.out.printf("Sorry, but it seems you haven't marked this task as done:\n  %s\n", taskList.getTask(unmarkIndex));
                    }
                    break;
                }

                default: {
                    System.out.println(UNKNOWN_COMMAND_MESSAGE);
                }
            }
        }
    }
}
