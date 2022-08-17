import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Sorry, I don't know what that means.\n"
            + "Did you make a mistake? Please note that commands are case-sensitive.";

    private static final TaskList taskList = new TaskList();

    /* Parses a command into an n x 2 array, where n is the number of
       parameters passed by the user. Parameters are seperated by a "/".
       Each parameter is then split into two, the parameter name, and
       the parameter's content.
     */
    private static String[][] parseCommand(String command) {
        return Stream.of(command.trim().split("\\s+/"))
                .map(s -> s.split("\\s+", 2))
                .map(arr -> {
                    if (arr.length == 1) return new String[]{arr[0], null};
                    else return arr;
                })
                .toArray(String[][]::new);
    }

    public static void main(String[] args) {

        Scanner sysIn = new Scanner(System.in);
        boolean exitCalled = false;

        System.out.println(GREETING_MESSAGE);

        while (!exitCalled) {
            try {
                String[][] userParams = parseCommand(sysIn.nextLine());
                String commandName = userParams[0][0];

                switch (commandName) {
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
                        Task newTask = new Task(userParams[0][1]);
                        taskList.addTask(newTask);
                        System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newTask, taskList.getLength());
                        break;
                    }

                    case "todo": {
                        Todo newTodo = new Todo(userParams[0][1]);
                        taskList.addTask(newTodo);
                        System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newTodo, taskList.getLength());
                        break;
                    }

                    case "deadline": {
                        String endTime = null;
                        for (int i = 1; i < userParams.length; i++) {
                            if (userParams[i][0].equals("by")) {
                                endTime = userParams[i][1];
                            }
                        }
                        Deadline newDeadline = new Deadline(userParams[0][1], endTime);
                        taskList.addTask(newDeadline);
                        System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newDeadline, taskList.getLength());
                        break;
                    }

                    case "event": {
                        String rangeTime = null;
                        for (int i = 1; i < userParams.length; i++) {
                            if (userParams[i][0].equals("at")) {
                                rangeTime = userParams[i][1];
                            }
                        }
                        Event newEvent = new Event(userParams[0][1], rangeTime);
                        taskList.addTask(newEvent);
                        System.out.printf("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newEvent, taskList.getLength());
                        break;
                    }

                    case "mark": {
                        try {
                            int markIndex = Integer.parseInt(userParams[0][1]) - 1;
                            if (markIndex >= taskList.getLength() || markIndex < 0) {
                                throw new DukeException("I do not have a task with that number in my list.");
                            } else if (taskList.getTask(markIndex).getIsDone()) {
                                System.out.printf("Sorry, but it seems you have marked this task as done:\n  %s\n", taskList.getTask(markIndex));
                            } else {
                                taskList.getTask(markIndex).setDone(true);
                                System.out.printf("Noice! I've marked this task as done:\n  %s\n", taskList.getTask(markIndex));
                            }
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        }
                        break;
                    }

                    case "unmark": {
                        try {
                            int unmarkIndex = Integer.parseInt(userParams[0][1]) - 1;
                            if (unmarkIndex >= taskList.getLength() || unmarkIndex < 0) {
                                throw new DukeException("I do not have a task with that number in my list.");
                            } else if (taskList.getTask(unmarkIndex).getIsDone()) {
                                taskList.getTask(unmarkIndex).setDone(false);
                                System.out.printf("Alright, I've marked this task as not done:\n  %s\n", taskList.getTask(unmarkIndex));
                            } else {
                                System.out.printf("Sorry, but it seems you haven't marked this task as done:\n  %s\n", taskList.getTask(unmarkIndex));
                            }
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        }
                        break;
                    }

                    default: {
                        throw new DukeException(UNKNOWN_COMMAND_MESSAGE);
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Undocumented exception: " + e);
                System.out.println("Please create an issue on our Github repository, along with the steps to recreate this exception.");
                // Called just in case the exception seriously affects the app.
                System.out.println("Terminating TedBot.....");
                exitCalled = true;
            }
        }
    }
}
