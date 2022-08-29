package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An object that handles reading and writing of data to save file.
 *
 */
public class Storage {

    private static File savedFile = null;

    /** Constructor for a Storage object */
    protected Storage() {

    }

    /**
     * Is called upon starting Duke. Attempts to find a save file.
     * If found, calls the ReadData method to read the save file.
     * If not, create a new empty save file.
     *
     * @return Arraylist of tasks that is created when reading contents of save file.
     */
    protected ArrayList<Task> readFileAtStart() {
        File dataDir = new File(System.getProperty("user.dir") + File.separator + "data");

        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        String fullPath = dataDir.getPath() + File.separator + "Save.txt";
        savedFile = new File(fullPath);
        boolean fileCreated = false;
        ArrayList<Task> arrayList = null;

        try {
            fileCreated = savedFile.createNewFile();

            if (!fileCreated) {
                System.out.println("Save file detected, reading it...");
                arrayList = readData();
            } else {
                System.out.println("Creating save file for first time use");
                arrayList = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating save file");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date found in save file!");
        }

        return arrayList;
    }

    /**
     * Is called only when a save file is found.
     * Attempts to parse contents of save file to populate the arraylist of tasks.
     *
     * @return Arraylist of tasks that is created when reading contents of save file.
     * @throws FileNotFoundException If scanner is unable to read save file.
     */
    private ArrayList<Task> readData() throws FileNotFoundException {

        Scanner sc = new Scanner(savedFile);
        ArrayList<Task> arrayList = new ArrayList<>();

        while (sc.hasNextLine()) { //type#boolean#taskname#time

            String[] array = sc.nextLine().split("#");
            boolean isToDo = (array.length == 3 && array[0].equals("T"));
            boolean isDeadlineOrEvent = (array.length == 4 && (array[0].equals("D") || array[0].equals("E")));
            boolean hasCorrectMark = (array[1].equals("1") || array[1].equals("0"));
            boolean isValidTask = isToDo || isDeadlineOrEvent;

            if (!isValidTask || !hasCorrectMark) {
                throw new RuntimeException("Cannot read file due to incorrect input");
            }

            char type = array[0].charAt(0);
            String taskname = array[2];
            boolean isDone = array[1].equals("1");

            if (type == 'T') {
                arrayList.add(new Todo(taskname));
            } else {
                String date = array[3];
                LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MMM-d"));

                if (type == 'E') {
                    arrayList.add(new Event(taskname, d));
                } else {
                    arrayList.add(new Deadline(taskname, d));
                }
            }

            if (isDone) {
                arrayList.get(arrayList.size() - 1).markDone();
            }
        }

        return arrayList;
    }

    /**
     * Attempts to write the tasks in arraylist into the save file.
     * Each task is separated by line in the save file.
     *
     * @param arrayList Current arraylist of tasks to be saved.
     * @throws IOException If unable to write to save file.
     */
    protected void saveDataToFile(ArrayList<Task> arrayList) throws IOException {

        FileWriter fileWriter = new FileWriter(savedFile, false);

        for (Task task : arrayList) {
            fileWriter.write(task.toSavedString() + System.lineSeparator());
        }

        fileWriter.close();
    }

}
