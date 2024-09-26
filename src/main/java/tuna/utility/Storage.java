package tuna.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import tuna.TunaException;
import tuna.task.Deadline;
import tuna.task.Event;
import tuna.task.Task;
import tuna.task.Todo;

/**
 * Represents a storage to handle file loading and saving.
 */
public class Storage {
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DATE_TIME_INDEX = 1;
    private static final int TASK_STATUS_INDEX = 1;

    /**
     * Loads the file contents located in the file path.
     *
     * @param folderPath folder path of the data file.
     * @param filePath file path of the data file.
     * @param tasks task list to handle task related functionalities.
     * @throws TunaException exception thrown when data file has incorrect formatting.
     */
    public void loadFileContents(String folderPath, String filePath, TaskList tasks) throws TunaException {
        assert !folderPath.equals("");
        assert !filePath.equals("");
        try {
            File directory = new File(folderPath);
            directory.mkdir();
            File file = new File(filePath);
            if (!file.createNewFile()) {
                processFile(file, tasks);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void processFile(File file, TaskList tasks) throws TunaException, IOException {
        Scanner scanner = new Scanner(file);
        parseFileContents(scanner, tasks);
    }

    private void parseFileContents(Scanner scanner, TaskList tasks) throws TunaException {
        int index = 0;
        while (scanner.hasNext()) {
            String[] input = scanner.nextLine().split("\\|");
            switch (input[0]) {
            case "T":
                tasks.addTodo(String.join(" ", Arrays.copyOfRange(input, 2, input.length)));
                break;
            case "E":
                String[] eventDetails = parseTaskDetails(input, "/at");
                tasks.addEvent(eventDetails[TASK_DESCRIPTION_INDEX], eventDetails[TASK_DATE_TIME_INDEX]);
                break;
            case "D":
                String[] deadlineDetails = parseTaskDetails(input, "/by");
                tasks.addDeadLine(deadlineDetails[TASK_DESCRIPTION_INDEX], deadlineDetails[TASK_DATE_TIME_INDEX]);
                break;
            default:
                throw new TunaException("Oops! Seems like the data file is not formatted correctly");
            }
            if (isCompletedTask(input[TASK_STATUS_INDEX])) {
                tasks.markItem(index);
            }
            index++;
        }
    }

    private String[] parseTaskDetails(String[] input, String keyword) {
        int limit = findElem(input, keyword);
        String taskDescription = String.join(" ", Arrays.copyOfRange(input, 2, limit));
        String dateTime = String.join(" ", Arrays.copyOfRange(input, limit + 1, input.length));
        return new String[]{taskDescription, dateTime};
    }

    private boolean isCompletedTask(String statusIcon) {
        return statusIcon.equals("X");
    }

    /**
     * Saves the tasks in the task list into the data file located in the specified file path.
     *
     * @param filePath file path of the data file.
     * @param tasks task list containing tasks to be saved.
     * @throws TunaException exception thrown when error occurs in processing the tasks.
     */
    public void saveFileContents(String filePath, TaskList tasks) throws TunaException {
        assert !filePath.equals("");
        try {
            FileWriter fw = new FileWriter(filePath);
            saveTasks(fw, tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveTasks(FileWriter fw, TaskList tasks) throws IOException, TunaException {
        for (int i = 0; i < tasks.getTotalTasks(); i++) {
            Task task = tasks.getTask(i);
            switch (task.getTaskType()) {
            case "T":
                Todo todo = (Todo) task;
                fw.write(todo.getTaskType() + "|" + todo.getStatusIcon() + "|" + todo.getDescription()
                        + System.lineSeparator());
                break;
            case "E":
                Event event = (Event) task;
                fw.write(event.getTaskType() + "|" + event.getStatusIcon() + "|" + event.getDescription()
                        + "|/at|" + event.getStringRepresentationOfDateTime() + System.lineSeparator());
                break;
            case "D":
                Deadline deadline = (Deadline) task;
                fw.write(deadline.getTaskType() + "|" + deadline.getStatusIcon() + "|"
                        + deadline.getDescription() + "|/by|" + deadline.getStringRepresentationOfDateTime()
                        + System.lineSeparator());
                break;
            default:
                throw new TunaException("Oops! Seems like something went wrong in the task list");
            }
        }
    }

    private static <T> int findElem(T[] arr, T elem) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }
}
