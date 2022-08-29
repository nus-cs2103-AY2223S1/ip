package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a storage that stores tasks.
 */
public class Storage {

    private File file;

    /**
     * Creates a Storage object.
     * @param filepath String representation of the path of the file.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Loads tasks from the file to a TaskList object.
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> currList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] tempArray = sc.nextLine().split(" \\| ");
                String typeOfTask = tempArray[0];

                switch (typeOfTask) {
                case "T":
                    currList.add(new Todo(tempArray[2], tempArray[1].equals("1")));
                    break;
                case "D":
                    LocalDate tempDate = LocalDate.parse(tempArray[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    Deadline tmpTask = new Deadline(tempArray[2], tempArray[1].equals("1"), tempDate);
                    currList.add(tmpTask);
                    break;
                case "E":
                    currList.add(new Event(tempArray[2], tempArray[1].equals("1"), tempArray[3]));
                    break;
                default:
                    System.out.println("Error: Wrong type of task");
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            this.createFile();
        }
        return currList;
    }

    /**
     * Saves tasks in a TaskList object into the file.
     * @param newTasks New TaskList that is going to be saved into the file.
     * @throws DukeException Throws a DukeException.
     */
    public void save(TaskList newTasks) throws DukeException {

        ArrayList<String> newFile = new ArrayList<>();

        for (int i = 0; i < newTasks.size(); i++) {
            newFile.add(newTasks.getTask(i).toFileString());

        }
        try {
            FileWriter fw = new FileWriter(file);

            fw.write(String.join("\n", newFile));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error: File added wrongly");
        }
    }

    /**
     * Creates a file at the start for first time user.
     */
    private void createFile() {
        this.file.getParentFile().mkdir();
        try {
            if (this.file.createNewFile()) {
                System.out.println("File created: " + this.file.getPath());
            }
        } catch (IOException e) {
            System.out.println("File not successfully created");
        }
    }
}
