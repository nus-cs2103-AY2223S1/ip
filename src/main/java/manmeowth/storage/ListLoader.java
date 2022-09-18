package manmeowth.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import manmeowth.exception.ManMeowthException;
import manmeowth.list.TaskList;
import manmeowth.task.Deadline;
import manmeowth.task.Event;
import manmeowth.task.TaskId;
import manmeowth.task.Todo;

/**
 * Reads and writes to a skeleton of the current task list while being updated as the list changes.
 *
 * @author WR3nd3
 */
public class ListLoader {
    /** File to read and write list.manmeowth.TaskList tasks to */
    private File listText = new File("data/man_meowth.txt");

    /** list.manmeowth.TaskList to update */
    private TaskList taskList;


    /**
     * Constructor for storage.manmeowth.ListLoader.
     *
     * @param taskList The list.manmeowth.TaskList to be updated with the stored data.
     */
    public ListLoader(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads stored data in the saved list and updates the task list.
     *
     * @throws ManMeowthException If the file fails to load.
     */
    public void load() throws ManMeowthException {
        BufferedReader reader = null;
        boolean toDelete = false;
        try {
            // Create necessary save file if it does not exist
            if (!new File("data").exists()) {
                new File("data").mkdir();
            }
            if (!listText.exists()) {
                listText.createNewFile();
            }

            reader = new BufferedReader(new FileReader(listText));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                // Ignore empty lines in the stored data
                if (currentLine.isBlank()) {
                    continue;
                }
                // Decipher stored data to create tasks to be added to the task list
                String[] inputArray = currentLine.split(" \\| ", 4);
                addTask(inputArray);
            }
        } catch (IOException e) {
            throw new ManMeowthException("Failed to load file");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            toDelete = true;
        } finally {
            String error = "Failed to load file: Unable to read from file";
            if (toDelete) {
                error = "Failed to load file: Unable to decipher contents";
                closeReader(reader, error);
                listText.delete(); // Can only delete file after closing reader
                throw new ManMeowthException(error);
            } else {
                closeReader(reader, error);
            }
        }
    }

    /**
     * Constructs and adds a new task to the taskList.
     *
     * @param inputArray String array of sections of task summary.
     * @throws IllegalArgumentException If command is invalid.
     */
    private void addTask(String[] inputArray) throws IllegalArgumentException {
        String command = inputArray[0];
        Boolean isCompleted = inputArray[1].equals("1");
        String description = inputArray[2];

        switch (TaskId.valueOf(command)) {
        case T:
            taskList.addTask(new Todo(description, isCompleted));
            break;
        case E:
            taskList.addTask(new Event(description, inputArray[3], isCompleted));
            break;
        case D:
            taskList.addTask(new Deadline(description, inputArray[3], isCompleted));
            break;
        default:
            break;
        }
    }

    /**
     * Appends task of given description to saved task list.
     *
     * @param summary String representing summarised description of task.
     * @throws ManMeowthException for failing to write to file.
     */
    public void appendToList(String summary) throws ManMeowthException {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            // Safe to open writer with reader since it is set to append
            writer = new BufferedWriter(new FileWriter(listText, true));
            reader = new BufferedReader(new FileReader(listText));
            String firstLine;
            // If the current stored task list is empty, do not add a new line
            if ((firstLine = reader.readLine()) != null && !firstLine.isBlank()) {
                writer.newLine();
            }
            writer.write(summary);
        } catch (IOException e) {
            throw new ManMeowthException("Failed to complete appending to file");
        } finally {
            closeReader(reader, "Failed to complete reading from file");
            closeWriter(writer, "Failed to complete writing to file");
        }
    }

    /**
     * Marks the task represented by the given summary as complete in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws ManMeowthException for failing to modify the file.
     */
    public void markTask(String summary) throws ManMeowthException {
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 1 + " | " + strArray[2] + "\n";

        String newContent = reformat(summary, newString);
        rewrite(newContent);
    }

    /**
     * Marks the task represented by the given summary as incomplete in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws ManMeowthException for failing to modify the file.
     */
    public void unmarkTask(String summary) throws ManMeowthException {
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 0 + " | " + strArray[2] + "\n";

        String newContent = reformat(summary, newString);
        rewrite(newContent);
    }

    /**
     * Deletes the task represented by the given summary in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws ManMeowthException for failing to modify file.
     */
    public void deleteTask(String summary) throws ManMeowthException {
        String newString = "";

        String newContent = reformat(summary, newString);
        rewrite(newContent);
    }

    /**
     * Writes over the contents of the listText file with the given String.
     *
     * @param newContent String write over the contents of the listText file.
     * @throws ManMeowthException If the file cannot be written to.
     */
    private void rewrite(String newContent) throws ManMeowthException {
        String error = "Failed to complete writing to file";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent);
        } catch (IOException e) {
            throw new ManMeowthException(error);
        } finally {
            closeWriter(writer, error);
        }
    }

    /**
     * Reads the listText file and copies its contents to a String while replacing the first occurrence of
     * the given String oldLine with the String newLine. This new String is returned.
     *
     * @param oldLine String to be replaced.
     * @param newLine String to replace oldLine with.
     * @return String representing the contents of the listText file with one line replaced.
     * @throws ManMeowthException If the file cannot be read from.
     */
    private String reformat(String oldLine, String newLine) throws ManMeowthException {
        String error = "Failed to complete reading from file";
        String newContent = "";
        boolean hasReplaced = false;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder(newContent);

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            // Ignore empty lines in file
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.isBlank()) {
                    if (!hasReplaced && currentLine.equals(oldLine)) {
                        builder.append(newLine);
                        hasReplaced = true;
                    } else {
                        builder.append(currentLine).append("\n");
                    }
                }
            }
            newContent = builder.toString().trim();
        } catch (IOException e) {
            throw new ManMeowthException(error);
        } finally {
            closeReader(reader, error);
        }

        return newContent;
    }

    /**
     * Closes the given BufferedReader.
     *
     * @param reader The BufferedReader to be closed.
     * @param msg String representing the error message if the reader cannot be closed.
     * @throws ManMeowthException If the reader cannot be closed.
     */
    private static void closeReader(BufferedReader reader, String msg) throws ManMeowthException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ManMeowthException(msg);
        }
    }

    /**
     * Closes the given BufferedWriter.
     *
     * @param writer The BufferedWriter to be closed.
     * @param msg String representing the error message if the writer cannot be closed.
     * @throws ManMeowthException If the writer cannot be closed.
     */
    private static void closeWriter(BufferedWriter writer, String msg) throws ManMeowthException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new ManMeowthException(msg);
        }
    }


}
