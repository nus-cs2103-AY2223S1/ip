package common;

import commands.DeadlineCommand;
import commands.EventCommand;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Utility class that handles reading and writing from an external storage (.txt) file.
 */
public class Storage {
    private final String storageName;
    private final String storageDirname;
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param storageName    Name of storage file. Should be a .txt file.
     * @param storageDirName Name of directory storing the storage file.
     */
    public Storage(String storageName, String storageDirName) {
        this.storageName = storageName;
        this.storageDirname = storageDirName;
        this.filePath = String.format("%s%s%s", this.storageDirname, File.separator, this.storageName);
    }


    /**
     * Attempts to create a directory and/or storage file if it does not exist.
     *
     * @throws IOException Thrown when an error creating the storage file occurs.
     */
    public String initializeStorage() throws IOException {
        Path filePath = Paths.get(this.filePath);
        Path dirPath = Paths.get(storageDirname);
        String res = "";

        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
            res += ChatResponse.returnChatCreateNewDirectory(this.storageDirname);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
            res += ChatResponse.returnChatCreateNewStorage(this.storageName);
        }
        return res;
    }

    /**
     * Writes a given task list to storage, with the delimiter used being "#".
     *
     * @param taskList Task list to save to storage.
     */
    public void writeToStorage(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).getEncodedValue() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     * Reads from an existing storage file and populates an empty TaskList object with Task objects
     * generated from storage data.
     *
     * @param taskList The TaskList object to populate.
     * @throws IOException Thrown when reading errors occur.
     */
    public void readFromStorage(TaskList taskList) throws IOException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] encodedTask = s.nextLine().split("#");
            switch (encodedTask[0]) {
                case "[T]": {
                    taskList.addTask(new ToDo(encodedTask[1], Boolean.parseBoolean(encodedTask[2])));
                    break;
                }
                case "[D]": {
                    taskList.addTask(new Deadline(encodedTask[1], Boolean.parseBoolean(encodedTask[2]), DeadlineCommand.parseDeadlineDatetimeFromStorage(encodedTask[3])));
                    break;
                }
                case "[E]": {
                    taskList.addTask(new Event(encodedTask[1], Boolean.parseBoolean(encodedTask[2]), EventCommand.parseEventDatetimeFromStorage(encodedTask[3]), EventCommand.parseEventDatetimeFromStorage(encodedTask[4])));
                    break;
                }
                default:
                    throw new IOException();
            }
        }
    }
}
