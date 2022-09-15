package skylark.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import skylark.skylark.SkylarkException;
import skylark.task.Deadline;
import skylark.task.Event;
import skylark.task.Task;
import skylark.task.Todo;

/** Represents a Storage object used for file operations such as read and write. */
public class Storage {
    /** File path of the text file used for storage. */
    private final String filePath;
    private final String regexString = " \\| ";

    /**
     * Returns a Storage object.
     *
     * @param filePath File path of the text file used for storage.
     */
    public Storage(String filePath) {
        assert filePath.length() > 0 : "FilePath should not be empty!";
        this.filePath = filePath;
    }

    /**
     * Reads from the text file in the file path specified. <br><br>
     * Creates the relevant Task objects based on the symbols.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> readFromFile() {
        ArrayList<Task> taskArrayList = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            if (file.isDirectory()) {
                throw new SkylarkException("This path is a directory!");
            }

            createFile(file);

            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    String[] splitStrings = nextLine.split(regexString, -1);

                    if (splitStrings.length < 3) {
                        throw new SkylarkException("Not enough parameters!");
                    }

                    Task currentTask;
                    String desc = splitStrings[2].strip();

                    switch (splitStrings[0]) {
                    case "T":
                        if (splitStrings.length > 3) {
                            String tag = splitStrings[3].strip();
                            currentTask = new Todo(desc, tag);
                        } else {
                            currentTask = new Todo(desc);
                        }
                        break;
                    case "D": {
                        if (splitStrings.length > 4) {
                            String endDate = splitStrings[4].strip();
                            String tag = splitStrings[3].strip();
                            currentTask = new Deadline(desc, endDate, tag);
                        } else {
                            String endDate = splitStrings[3].strip();
                            currentTask = new Deadline(desc, endDate);
                        }
                        break;
                    }
                    case "E": {
                        if (splitStrings.length > 4) {
                            String timing = splitStrings[4].strip();
                            String tag = splitStrings[3].strip();
                            currentTask = new Event(desc, timing, tag);
                        } else {
                            String timing = splitStrings[3];
                            currentTask = new Event(desc, timing);
                        }
                        break;
                    }
                    default:
                        throw new SkylarkException("No suitable task found!");
                    }

                    boolean isDone = Integer.parseInt(splitStrings[1]) == 1;
                    if (isDone) {
                        currentTask.markAsDone();
                    } else {
                        currentTask.markAsUndone();
                    }

                    taskArrayList.add(currentTask);
                }
            } catch (FileNotFoundException fileNotFoundException) {
                throw new SkylarkException("Cannot find file!");
            }
        } catch (SkylarkException skylarkException) {
            return new ArrayList<>();
        }

        return taskArrayList;
    }

    /**
     * Saves the current TaskList into the file path specified by the user.
     *
     * @param taskArrayList ArrayList of Task objects.
     * @throws SkylarkException If an exception is thrown while performing file operations.
     */
    public void saveToFile(ArrayList<Task> taskArrayList) throws SkylarkException {
        assert this.filePath.length() > 0 : "FilePath should not be empty!";
        try {
            File file = new File(this.filePath);

            createFile(file);
            if (file.exists() && !file.canWrite()) {
                boolean isWritable = file.setWritable(true);
                if (!isWritable) {
                    throw new SkylarkException("File is not writable!");
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Task currentTask : taskArrayList) {
                // Reference: https://www.baeldung.com/java-string-newline
                fileWriter.write(currentTask.toStringFile() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException ioException) {
            throw new SkylarkException("IOException occurred when writing to file");
        } catch (SecurityException securityException) {
            throw new SkylarkException("SecurityException occurred when writing to file");
        }
    }
    private void createFile(File file) throws SkylarkException {
        if (file.exists()) {
            return;
        }
        try {
            // https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
            // https://stackoverflow.com/a/4040667
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new SkylarkException("Error creating directory!");
            }

            if (!file.createNewFile()) {
                throw new SkylarkException("Error creating new file!");
            }
        } catch (IOException ioException) {
            throw new SkylarkException("IOException occurred when creating new file");
        }
    }
}
