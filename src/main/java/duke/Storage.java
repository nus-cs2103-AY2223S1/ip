package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath the string that represents the path of the text file to
     *                 load tasks from or save tasks in
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Task Objects that is loaded from the text file.
     * Returns an empty ArrayList if the text file is empty or does not exist.
     *
     * @return an ArrayList of Task Objects
     * @throws DukeException if there is an error in accessing the file
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (createDukeTextFile()) {
            try {
                File dukeFile = new File(filePath);
                Scanner myReader = new Scanner(dukeFile);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    char taskType = data.charAt(0);

                    switch (taskType) {
                    case 'T':
                        String[] todoSplit = data.split(" // ", 3);
                        Task todo = new Todo(todoSplit[2]);
                        if (todoSplit[1].equals("X")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case 'D':
                        String[] deadlineSplit = data.split(" // ", 4);
                        Task deadline = new Deadline(deadlineSplit[2], LocalDateTime.parse(deadlineSplit[3]));
                        if (deadlineSplit[1].equals("X")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case 'E':
                        String[] eventSplit = data.split(" // ", 4);
                        Task event = new Event(eventSplit[2], LocalDateTime.parse(eventSplit[3]));
                        if (eventSplit[1].equals("X")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw new DukeException("Something is wrong with the text file.");
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("File does not exist.");
            }
        }
        return tasks;
    }

    /**
     * Takes in an ArrayList of Task objects and saves them in the text file.
     *
     * @param tasks An ArrayList of Task objects
     * @throws DukeException if there is an error with writing to the text file
     */
    public void saveData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);

            writer.write("");
            for (Task task : tasks) {
                writer.append(task.getDataFormat()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Something is wrong with the text file.");
        }
    }

    /**
     * Creates a Duke.txt text file.
     *
     * @return true if the text file already exists; false otherwise
     * @throws DukeException if an error occurred during file creation
     */
    private boolean createDukeTextFile() throws DukeException {
        boolean isAlreadyCreated = false;
        File dataFolder = new File("data");
        File dukeFile = new File(filePath);

        try {
            dataFolder.mkdirs();
            if (!dukeFile.createNewFile()) {
                isAlreadyCreated = true;
            }
        } catch (IOException e) {
            throw new DukeException("Something is wrong with the text file.");
        }

        return isAlreadyCreated;
    }
}
