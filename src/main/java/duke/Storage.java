package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to handle datafile storage.
 */
class Storage {
    private final String fileName;

    /**
     * Initializes the Storage class with the given file name.
     *
     * @param fileName name of the file.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the data in file and assigns them in param taskList
     *
     * @param taskList taskList to be filled.
     */
    public void fillData(TaskList taskList){
        File file = new File(this.fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (java.io.FileNotFoundException e){
            return;
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                Task task = Task.parseFromString(line);
                taskList.addTask(task);
            } catch (DukeException e) { }
        }
        scanner.close();
    }

    /**
     * Saves the task list to the storage file.
     *
     * @param taskList task list to be saved.
     * @throws IOException when opening a file does not work.
     */
    public void SaveToStorage(TaskList taskList) throws IOException{
        FileWriter writer = new FileWriter(this.fileName);
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                writer.write(taskList.getTask(i) + "\n");
            } catch (DukeException e) {
                //Impossible to happen as we are iterating.
            }
        }
        writer.close();
    }
}
