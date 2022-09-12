package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class stores and load the information into a text document.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor for a Storage class.
     *
     * @param filePath The relative path to the text document.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * To create the text document in the relevant file if it does not exist.
     *
     * @throws IOException If the relative path to the text document is invalid.
     */
    public void load() throws IOException {
        String dir = this.filePath.substring(0, this.filePath.lastIndexOf("/"));
        File dataDir = new File(dir);
        dataDir.mkdir();
        File newDuke = new File(filePath);
        newDuke.createNewFile();
    }

    /**
     * Writes tasks from the arraylist into the text document.
     *
     * @param textToAdd String information of the task.
     * @throws IOException If the relative path to the text document is invalid.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Formats the string of the task.
     *
     * @param task Task in the arraylist.
     * @return The formatted string.
     */
    public String fileFormatString(Task task) {
        String temp = task.getStatusIcon();
        if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return "D | " + temp + " | "
                    + deadlineTask.getDescription() + " | " + deadlineTask.dateTimeString()
                    + deadlineTask.formatTagsForFile();
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return "E | " + temp + " | "
                    + eventTask.getDescription() + " | " + eventTask.dateTimeString()
                    + eventTask.formatTagsForFile();
        } else {
            TodoTask todoTask = (TodoTask) task;
            return "T | " + temp + " | " + todoTask.getDescription()
                    + todoTask.formatTagsForFile();
        }
    }

    /**
     * Adds tasks from the text document into the arraylist.
     *
     * @param tasks The list added into.
     * @throws FileNotFoundException If the file does not exist.
     * @throws DukeException If information from the task in invalid.
     */
    public void addDukeToList(TaskList tasks) throws FileNotFoundException, DukeException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        assert this.filePath != null;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] keywords = line.split(" \\| ");
            switch (keywords[0]) {
            case "D":
                setTaskIntoList(Parser.Type.DEADLINE, keywords, tasks);
                break;
            case "E":
                setTaskIntoList(Parser.Type.EVENT, keywords, tasks);
                break;
            case "T":
                setTaskIntoList(Parser.Type.TODO, keywords, tasks);
                break;
            default:
                throw new DukeException("Error! Task not given.");
            }
        }
    }

    /**
     * Determines if task is marked or unmarked.
     *
     * @param symbol Mark symbol if any.
     * @param task The task to mark or unmark.
     */
    public void verifyMarkTask(String symbol, Task task) {
        if (symbol.equals("X")) {
            task.mark();
        } else {
            task.unMark();
        }
    }

    /**
     * Set task into the list.
     *
     * @param type Task type.
     * @param keywords The array of words separated by | symbol.
     * @param tasks The list added into.
     * @throws DukeException If task cannot be made.
     */
    public void setTaskIntoList(Parser.Type type, String[] keywords, TaskList tasks) throws DukeException {
        switch (type) {
        case DEADLINE:
            DeadlineTask deadlineTask = new DeadlineTask(keywords[2], keywords[3]);
            verifyMarkTask(keywords[1], deadlineTask);
            addTagsToTask(4, keywords.length, deadlineTask, keywords);
            tasks.addStorageToList(deadlineTask);
            break;
        case EVENT:
            EventTask eventTask = new EventTask(keywords[2], keywords[3]);
            verifyMarkTask(keywords[1], eventTask);
            tasks.addStorageToList(eventTask);
            addTagsToTask(4, keywords.length, eventTask, keywords);
            break;
        case TODO:
            TodoTask todoTask = new TodoTask(keywords[2]);
            verifyMarkTask(keywords[1], todoTask);
            addTagsToTask(3, keywords.length, todoTask, keywords);
            tasks.addStorageToList(todoTask);
            break;
        default:
            assert false : "Adding tasks from text into the list should not reach this error.";
        }
    }

    /**
     * Add all the tags back into the task.
     *
     * @param start The index of the first tag
     * @param end The index of the last tag.
     * @param task The task where all the tags are added into.
     * @param keywords the array containing the tags.
     */
    public void addTagsToTask(int start, int end, Task task, String[] keywords) {
        for (int i = start; i < end; i++) {
            task.addTag(keywords[i]);
        }
    }
}
