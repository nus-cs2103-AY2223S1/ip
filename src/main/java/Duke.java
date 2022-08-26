import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import static java.util.stream.Collectors.joining;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        wrapPrint(greeting);
        ArrayList<Task> taskList = loadTaskListFromFile();
        Scanner in = new Scanner(System.in);
        while(true) {
            try {
                if (repeat(in, taskList)) {
                    break;
                }
            } catch (DukeException e) {
                wrapPrint(leftPad("☹ OOPS!!! " + e.getLocalizedMessage()));
            }
        }
    }

    private static void wrapPrint(String toPrint) {
        System.out.println("    ____________________________________________________________");
        System.out.println(toPrint);
        System.out.println("    ____________________________________________________________");
    }

    private static void printAddTaskSuccessfully(ArrayList<Task> taskList) {
        String taskString = "tasks";
        if (taskList.size() == 1) {
            taskString = "task";
        }
        wrapPrint(leftPad("Got it. I've added this task:\n")
                + leftPad("  " + taskList.get(taskList.size() - 1).toString())
                + leftPad(String.format("\n" + leftPad("Now you have %d %s in the list."), taskList.size(), taskString)));
    }

    private static ArrayList<Task> loadTaskListFromFile() {
        Path saveLocation = Paths.get("data/tasks.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Files.lines(saveLocation).forEach((taskString) -> {
                String type = taskString.split(",")[0];
                switch (type) {
                case "T":
                    taskList.add(Task.fromSaveString(taskString));
                    break;
                case "E":
                    taskList.add(Event.fromSaveString(taskString));
                    break;
                case "D":
                    taskList.add(Deadline.fromSaveString(taskString));
                    break;
                default:
                    throw new RuntimeException("Tried to read unexpected save data.");
                }
            });
        } catch (RuntimeException e) {
            // TODO Notify the user save data is corrupted.
            return new ArrayList<>();
        } catch (IOException ignored) {
            // Save file does not exist, start afresh.
            return new ArrayList<>();
        }
        return taskList;
    }

    private static void saveTaskListToFile(ArrayList<Task> taskList) throws IOException {
        String toSave = taskList.stream().map((task) -> task.saveData()).collect(joining("\n"));
        Files.createDirectories(Paths.get("data"));
        Path saveLocation = Paths.get("data/tasks.txt");
        try {
            Files.createFile(saveLocation);
        } catch (FileAlreadyExistsException ignored) {
        }
        Files.write(saveLocation, toSave.getBytes());
    }

    private static String leftPad(String toPrint) {
        return "     " + toPrint;
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    private static boolean repeat(Scanner in, ArrayList<Task> taskList) throws DukeException {
        String input = in.nextLine();
        String[] inputSplit = input.split(" ");
        switch(inputSplit[0]) {
            case "bye":
                try {
                    saveTaskListToFile(taskList);
                } catch (IOException e) {
                    wrapPrint(leftPad("IOException: " + e.toString()));
                }
                wrapPrint(leftPad("Bye. Hope to see you again soon!"));
                return true;
            case "list":
                StringBuilder listString = new StringBuilder(leftPad("Here are the tasks in your list:\n"));
                for (int i = 0; i < taskList.size(); i++) {
                    listString.append(leftPad(String.format("%d.", i + 1)));
                    listString.append(taskList.get(i).toString());
                    listString.append("\n");
                }
                if (listString.length() > 0) {
                    listString.setLength(listString.length() - 1);
                }
                wrapPrint(listString.toString());
                break;
            case "mark":
            case "unmark":
                // Error handling
                if (inputSplit.length < 2) {
                    throw new DukeException("You did not specify what task number to mark as done. Unable to mark task.");
                } else if (!isNumeric(inputSplit[1])) {
                    throw new DukeException(String.format("Invalid task number provided: %s. Unable to mark task.", inputSplit[1]));
                }
                int index = Integer.parseInt(inputSplit[1]) - 1;
                if (index == -1 || index >= taskList.size()) {
                    throw new DukeException(String.format("Task number %d not found! Unable to mark task.", index + 1));
                }
                // Mark/unmark operations
                if (inputSplit[0].equals("mark")) {
                    taskList.get(index).markDone();
                    wrapPrint(leftPad("Nice! I've marked this task as done:\n  ")
                            + leftPad(taskList.get(index).toString()));
                } else {
                    taskList.get(index).unmarkDone();
                    wrapPrint(leftPad("OK, I've marked this task as not done yet:\n  ")
                            + leftPad(taskList.get(index).toString()));
                }
                break;
            case "todo":
                // Error handling
                if (inputSplit.length < 2) {
                    throw new DukeException("Please provide a description for your todo.");
                }
                // Task creation operation
                taskList.add(new Task(String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length))));
                printAddTaskSuccessfully(taskList);
                break;
            case "deadline":
            case "event":
                String[] inputSlashSplit = input.split("/");
                // Error handling
                if (inputSlashSplit.length < 2 || inputSlashSplit[1].split(" ").length < 2) {
                    throw new DukeException(String.format("Please specify a time for your %s.", inputSplit[0]));
                }
                if (inputSlashSplit[0].split(" ").length < 2) {
                    throw new DukeException(String.format("Please provide a description for your %s.", inputSplit[0]));
                }
                // Task creation operation
                String time = input.split("/")[1];
                String[] description = inputSlashSplit[0].split(" ");
                if (inputSplit[0].equals("deadline")) {
                    taskList.add(new Deadline(String.join(" ", Arrays.copyOfRange(description, 1, description.length)), time, false));
                } else {
                    taskList.add(new Event(String.join(" ", Arrays.copyOfRange(description, 1, description.length)), time, false));
                }
                printAddTaskSuccessfully(taskList);
                break;
            case "delete":
                // Error handling
                if (inputSplit.length < 2) {
                    throw new DukeException("You did not specify what task number to delete.");
                } else if (!isNumeric(inputSplit[1])) {
                    throw new DukeException(String.format("Invalid task number provided: %s. Unable to delete task.", inputSplit[1]));
                }
                // Delete operation
                index = Integer.parseInt(inputSplit[1]) - 1;
                if (index == -1 || index >= taskList.size()) {
                    throw new DukeException(String.format("Task number %d not found! Unable to delete task.", index + 1));
                }
                Task deleted = taskList.remove(index);
                wrapPrint(leftPad("Noted. I've removed this task:\n  ")
                        + leftPad(deleted.toString()));
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
