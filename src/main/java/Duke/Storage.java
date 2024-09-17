package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * Reads the duke.txt file and uploads tasks to TaskList in Duke application.
     *
     * @param receivingList TaskList to receive tasks from duke.txt file.
     */
    public void readFromFile(TaskList receivingList) {
        try {
            File file = new File("data/duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                textToList(task, receivingList);
            }
        } catch (IOException e) {
            // Creates the data directory and duke.txt if either do not exist
            createFile();
        }
    }

    /**
     * Reads the duke.txt file and adds the corresponding task to the TaskList.
     *
     * @param input line from duke.txt file
     * @param receivingList TaskList for Duke.
     */
    public void textToList(String input, TaskList receivingList) {
        String[] line = input.split("~");
        String label = line[0];
        boolean isDone = line[1].equals("1");
        String description = line[2];
        Task newTask;
        switch (label) {
        case "T":
            newTask = new ToDo(description);
            if (isDone) {
                newTask.setDone();
            }
            receivingList.addTaskWithoutOutput(newTask);
            break;
        case "D":
            newTask = new Deadline(description, line[3]);
            if (isDone) {
                newTask.setDone();
            }
            receivingList.addTaskWithoutOutput(newTask);
            break;
        case "E":
            newTask = new Event(description, line[3]);
            if (isDone) {
                newTask.setDone();
            }
            receivingList.addTaskWithoutOutput(newTask);
            break;
        default:
            break;
        }
    }

    /**
     * Creates a directory and the text file to store the data if either does not exist.
     */
    public void createFile() {
        try {
            File directory = new File("./data");
            directory.mkdir();
            File myObj = new File("data/duke.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates the duke.txt file with TaskList from Duke application.
     *
     * @param list TaskList to be uploaded to duke.txt file.
     */
    public void updateStorage(TaskList list) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            for (int x = 0; x < list.size(); x++) {
                writer.write(list.get(x).toWrite() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
