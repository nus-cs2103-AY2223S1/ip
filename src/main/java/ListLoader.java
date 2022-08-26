import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reads and writes to a skeleton of the current task list while being updated as the list changes.
 *
 * @author WR3nd3
 */
public class ListLoader {
    /** File to read and write TaskList tasks to */
    private File listText = new File("data/duke.txt");

    /** TaskList to update */
    private TaskList taskList;


    /**
     * Constructor for ListLoader.
     *
     * @param taskList The TaskList to be updated with the stored data.
     */
    public ListLoader(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads stored data in the saved list and updates the task list.
     *
     * @throws DukeException for failing to load the file.
     */
    public void load() throws DukeException {
        BufferedReader reader = null;
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
                String command = inputArray[0];
                Boolean isCompleted = inputArray[1].equals("1") ? true : false;
                String description = inputArray[2];

                switch (Task_Id.valueOf(command)) {
                case T:
                    taskList.addTask(new Todo(description, isCompleted));
                    break;
                case E:
                    taskList.addTask(new Event(description, inputArray[3], isCompleted));
                    break;
                case D:
                    taskList.addTask(new Deadline(description, inputArray[3], isCompleted));
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("Failed to load file");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            try {
                reader.close();
                listText.delete(); //need to delete after close reader
                throw new DukeException("Failed to load file: Unable to decipher contents");
            } catch (IOException e1){
                throw new DukeException("Failed to load file: Unable to decipher contents");
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete loading file");
            }
        }
    }

    /**
     * Appends task of given description to saved task list.
     *
     * @param summary String representing summarised description of task.
     * @throws DukeException for failing to write to file.
     */
    public void appendToList(String summary) throws DukeException {
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
            throw new DukeException("Failed to complete writing to file");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete writing to file");
            }
        }
    }

    /**
     * Marks the task represented by the given summary as complete in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws DukeException for failing to modify the file.
     */
    public void markTask(String summary) throws DukeException {
        String oldContent = "";
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 1 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            // Ignore empty lines in the stored data
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    continue;
                }
                oldContent += currentLine + "\n";
            }

            newContent = oldContent.replaceFirst("\\Q" + summary + "\\E", newString).trim();
        } catch (IOException e) {
            throw new DukeException("Failed to complete reading from file");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete reading from file");
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent);
        } catch (IOException e) {
            throw new DukeException("Failed to complete writing to file");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete writing to file");
            }
        }
    }

    /**
     * Marks the task represented by the given summary as incomplete in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws DukeException for failing to modify the file.
     */
    public void unmarkTask(String summary) throws DukeException {
        String oldContent = "";
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 0 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            // Ignore empty lines in file
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.isBlank()) {
                    continue;
                }
                oldContent += currentLine + "\n";
            }

            newContent = oldContent.replaceFirst("\\Q" + summary + "\\E", newString).trim();
        } catch (IOException e) {
            throw new DukeException("Failed to complete reading from file");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete reading from file");
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent);
        } catch (IOException e) {
            throw new DukeException("Failed to complete writing to file");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete writing to file");
            }
        }
    }

    /**
     * Delete the task represented by the given summary in the saved list.
     *
     * @param summary String representing summarised description of task.
     * @throws DukeException for failing to modify file.
     */
    public void deleteTask(String summary) throws DukeException {
        String newContent = "";
        String[] strArray = summary.trim().split(" \\| ", 3);
        String newString = strArray[0] + " | " + 1 + " | " + strArray[2];
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(listText));
            String currentLine;

            // Ignore empty lines in file
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(summary) || currentLine.isBlank()) {
                    continue;
                }
                newContent += currentLine + "\n";
            }
        } catch (IOException e) {
            throw new DukeException("Failed to complete reading from file");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete reading from file");
            }
        }

        try {
            writer = new BufferedWriter(new FileWriter(listText, false));
            writer.write(newContent.trim());
        } catch (IOException e) {
            throw new DukeException("Failed to complete writing to file");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new DukeException("Failed to complete writing to file");
            }
        }
    }
}
