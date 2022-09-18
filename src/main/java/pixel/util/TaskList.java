package pixel.util;

import java.io.IOException;
import java.util.ArrayList;

import pixel.Pixel;
import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.ToDo;

/**
 * Handles tasks
 */
public class TaskList {

    private final String filePath;

    public TaskList(String filePath) {
        this.filePath = filePath;
    }

    private boolean findDuplicate(Task newTask) {
        for (Task task : Storage.INPUT_TASKS) {
            if (task.toString().equals(newTask.toString())) {
                return true;
            }
        }
        return false;
    }

    public static String listTasks() {
        String output = "Here are the tasks in your list: \n";
        for (int i = 0; i < Pixel.getTaskCount(); i++) {
            Task currentTask = Storage.INPUT_TASKS.get(i);
            output += ((i + 1) + ". " + currentTask + "\n");
        }
        return output;
    }

    public static String listFindResults(ArrayList<Task> findResults) {
        String output ="Here are the matching tasks in your list: \n";
        for (int i = 0; i < findResults.size(); i++) {
            Task currentTask = findResults.get(i);
            output += ((i + 1) + ". " + currentTask + "\n");
        }
        return output;
    }

    // This method creates a new pixel.txt file automatically if no such file exists
    public String handleNewTask(String userInput, Parser.TaskType type) throws IOException, DuplicateEntryException {

        int indexOfSlash = userInput.indexOf("/"); // returns -1 if such a string doesn't exist
        // If there's a "/by" or "/at" in the input string, then the info behind the "/by" or "/at" is the due
        // if there's no "/by" and "/at" string, then due should be empty
        String due = indexOfSlash == -1 ? "" : userInput.substring(indexOfSlash + 4);
        int indexOfEndOfDescription = indexOfSlash == -1 ? userInput.length() : indexOfSlash;
        Task newTask;
        String commandWord = "";

        if (indexOfSlash != -1) {
            if (userInput.substring(indexOfSlash + 1).startsWith("by")) {
                commandWord = "by";
            } else if (userInput.substring(indexOfSlash + 1).startsWith("at")) {
                commandWord = "at";
            } else {
                throw new IncorrectFormatException("Slash should be followed by \"by\" or \"at\"!"); // programme breaks
            }
        }

        switch (type) {
            case TODO: {
            String description = userInput.substring(5, indexOfEndOfDescription).strip();
            newTask = new ToDo(description, due, commandWord); // Stores user input

            break;
        }
            case DEADLINE: { // deadline
            String description = userInput.substring(9, indexOfEndOfDescription).strip();
            newTask = new Deadline(description, due, commandWord); // Stores user input

            break;
        }
            case EVENT: { // event
            String description = userInput.substring(6, indexOfEndOfDescription).strip();
            newTask = new Event(description, due, commandWord); // Stores user input

            break;
        }
        default: //shouldn't reach here
            throw new IncorrectFormatException("Incorrect format of input!"); // programme breaks
        }

        if (findDuplicate(newTask)) {
            throw new DuplicateEntryException("Same task already exists in database!");
        }
        Storage.INPUT_TASKS.add(Pixel.getTaskCount(), newTask);

        // index of last element in ArrayList is always smaller than size
        assert Storage.INPUT_TASKS.size() == (Pixel.getTaskCount() + 1)
            : "Size of ArrayList did not increase by 1 after adding new task";

        // Not so efficient method
        // first delete existing content in old file
        Storage.resetFile(this.filePath);

        // run through all the files in the list and update pixel.txt accordingly
        Storage.appendAllTasksToFile(this.filePath);

        Pixel.addOneToTaskCount();

        return ("Got it. I've added this task: \n"
            + newTask + "\n"
            + "Now you have " + Pixel.getTaskCount() + " tasks in the list.");
    }
}
