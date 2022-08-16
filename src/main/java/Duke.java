import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static String NO_TASK_NAME = "No task name defined, please try again";
    private static String NO_INDEX_SPECIFIED = "No index specified, try again";
    public static void main(String[] args) throws StorageHandler.InvalidStorageFilePathException, IOException, StorageHandler.StorageOperationException {
        Scanner scanner = new Scanner(System.in);
        StorageHandler storageHandler = new StorageHandler();
        Storage storage = storageHandler.loadSavedData();
        System.out.println(GREETING);

        while (scanner.hasNext()) {
            String nextCommand = scanner.nextLine();

            try {
                if (nextCommand.equals("bye")) {
                    System.out.println(EXIT_MSG);
                    scanner.close();
                    break;
                }

                if (nextCommand.equals("list")) {
                    storage.listTasks();
                    continue;
                }

                if (nextCommand.startsWith("delete")) {
                    if (nextCommand.length() < 8) throw new DukeException(NO_INDEX_SPECIFIED);

                    Integer targetIndex = Integer.parseInt(nextCommand.substring(7));
                    storage.removeTaskFromList(targetIndex);
                    continue;
                }

                if (nextCommand.startsWith("mark")) {
                    if (nextCommand.length() < 6) throw new DukeException(NO_INDEX_SPECIFIED);

                    Integer targetIndex = Integer.parseInt(nextCommand.substring(5));
                    storage.markTaskAsDone(targetIndex);
                    continue;
                }

                if (nextCommand.startsWith("unmark") || nextCommand.startsWith("Unmark")) {
                    if (nextCommand.length() < 8) throw new DukeException(NO_INDEX_SPECIFIED);

                    Integer targetIndex = Integer.parseInt(nextCommand.substring(7));
                    storage.markTaskAsUnDone(targetIndex);
                    continue;
                }

                if (nextCommand.startsWith("todo")) {
                    if (nextCommand.length() < 6) throw new DukeException(NO_INDEX_SPECIFIED);

                    String taskName = nextCommand.substring(5);
                    if (taskName.isEmpty()) throw new DukeException(NO_TASK_NAME);
                    Task taskToAdd = new ToDo(taskName);
                    storage.addTaskToList(taskToAdd);
                    continue;
                }

                if (nextCommand.startsWith("deadline")) {
                    int lastIdx = nextCommand.lastIndexOf("/");
                    if (lastIdx == -1)
                        throw new DukeException("Please follow the format <taskname> /by <datetime>");

                    String mainTask = nextCommand.substring(9, lastIdx);
                    if (mainTask.isEmpty()) throw new DukeException(NO_TASK_NAME);

                    String doneBy = nextCommand.substring(lastIdx + 4);
                    if (doneBy.isEmpty()) throw new DukeException("No deadline given, please try again");

                    Task taskToAdd = new Deadline(mainTask, doneBy);
                    storage.addTaskToList(taskToAdd);
                    continue;
                }

                if (nextCommand.startsWith("event")) {
                    int lastIdx = nextCommand.lastIndexOf("/");
                    if (lastIdx == -1)
                        throw new DukeException("Please follow the format <taskname> /at <date and time>");

                    String mainTask = nextCommand.substring(6, lastIdx);
                    if (mainTask.isEmpty()) throw new DukeException(NO_TASK_NAME);

                    String doneAt = nextCommand.substring(lastIdx + 4);
                    if (doneAt.isEmpty()) throw new DukeException("No date given, please try again");

                    Task taskToAdd = new Event(mainTask, doneAt);
                    storage.addTaskToList(taskToAdd);
                    continue;
                }
                // Handle unknown commands
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println(e + "\n");
            }
        }
    }
}