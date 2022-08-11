import java.util.Scanner;

public class Duke {
    private static String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static String NO_TASK_NAME = "No task name defined, please try again";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println(EXIT_MSG);
                scanner.close();
                break;
            }

            if (nextCommand.equals("list")) {
                storage.listTasks();
                continue;
            }

            if (nextCommand.startsWith("mark")) {
                try {
                    Integer targetIndex = Integer.parseInt(nextCommand.substring(5));
                    storage.markTaskAsDone(targetIndex);
                    continue;
                } catch (Exception e) {
                    System.out.println(e + "\nPlease input an integer");
                    continue;
                }
            }

            if (nextCommand.startsWith("unmark") || nextCommand.startsWith("Unmark")) {
                try {
                    Integer targetIndex = Integer.parseInt(nextCommand.substring(7));
                    storage.markTaskAsUnDone(targetIndex);
                    continue;
                } catch (Exception e) {
                    System.out.println(e + "\nPlease input an integer");
                    continue;
                }
            }

            if (nextCommand.startsWith("todo")) {
                try {
                    String taskName = nextCommand.substring(5);
                    if (taskName.isEmpty()) throw new Exception(NO_TASK_NAME);
                    Task taskToAdd = new ToDo(taskName);
                    storage.addTaskToList(taskToAdd);
                    continue;
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }
            }

            if (nextCommand.startsWith("deadline")) {
                try {
                    int lastIdx = nextCommand.lastIndexOf("/");
                    if (lastIdx == -1) throw new Exception("Please follow the format <taskname> /by <datetime>");

                    String mainTask = nextCommand.substring(9, lastIdx);
                    if (mainTask.isEmpty()) throw new Exception(NO_TASK_NAME);

                    String doneBy = nextCommand.substring(lastIdx+4);
                    if (doneBy.isEmpty()) throw new Exception("No deadline given, please try again");

                    Task taskToAdd = new Deadline(mainTask, doneBy);
                    storage.addTaskToList(taskToAdd);
                    continue;
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }
            }

            if (nextCommand.startsWith("event")) {
                try {
                    int lastIdx = nextCommand.lastIndexOf("/");
                    if (lastIdx == -1) throw new Exception("Please follow the format <taskname> /at <date and time>");

                    String mainTask = nextCommand.substring(6, lastIdx);
                    if (mainTask.isEmpty()) throw new Exception(NO_TASK_NAME);

                    String doneAt = nextCommand.substring(lastIdx+4);
                    if (doneAt.isEmpty()) throw new Exception("No date given, please try again");

                    Task taskToAdd = new Event(mainTask, doneAt);
                    storage.addTaskToList(taskToAdd);
                    continue;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            // Handle unknown commands
        }
    }
}