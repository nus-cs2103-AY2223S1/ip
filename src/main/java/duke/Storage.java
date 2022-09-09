package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is used to load save data/create a save file if none exist. Storage also updates the saved data after
 * each time duke is run.
 *
 * @author Sean Lam
 */
public class Storage {
    protected ArrayList<Task> itemList = new ArrayList<>();
    protected boolean isLoaded;

    /**
     * Constructor for storage.
     *
     * @param filePath Location of file to load
     */
    public Storage(String filePath) {
        try {
            readFile(filePath);
            isLoaded = true;
        } catch (FileNotFoundException e) {
            //make file
            System.out.println("An error occurred.");
            makeFile();
            isLoaded = false;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update file after duke is run.
     *
     * @param updatedList list to get updates from
     */
    public void updateFile(ArrayList<Task> updatedList) {
        try {
            FileWriter myWriter = new FileWriter("dukeHistory.txt");
            for (int i = 0; i < updatedList.size(); i++) {
                myWriter.write(updatedList.get(i).toFile());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Create file is no file is found.
     */
    public void makeFile() {
        try {
            File blankFile = new File("dukeHistory.txt");
            if (blankFile.createNewFile()) {
                System.out.println("File created: " + blankFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Add task read from file to the list of tasks.
     *
     * @param taskItem Information about the task to add
     */
    public void addToList(String taskItem) throws DukeException{
        String[] details = taskItem.split("[|]");
        Task toAdd;
        switch (details[0]) {
        case "T":
            toAdd = new ToDo(details[2]);
            break;
        case "D":
            toAdd = new Deadline(details[2], details[3], details[4]);
            break;
        case "E":
            toAdd = new Event(details[2], details[3], details[4], details[5]);
            break;
        default:
        //empty;
        throw new DukeException("Error reading file");    
        }

        switch (details[1]) {
        case "0":
            toAdd.setStatusIcon(false);
            break;
        case "1":
            toAdd.setStatusIcon(true);
        }

        itemList.add(toAdd);
    }

    /**
     * Reads the file and makes a list of tasks.
     *
     * @param fileLocation Location of save file
     * @throws FileNotFoundException File is not found at the specified location
     */
    public void readFile(String fileLocation) throws FileNotFoundException, DukeException {
        File dukeHistory = new File(fileLocation);
        Scanner myReader = new Scanner(dukeHistory);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            addToList(data);
        }
        myReader.close();
    }

    /**
     * Returns the list of task created
     *
     * @return list of tasks
     * @throws DukeException File not found
     */
    public ArrayList<Task> load() throws DukeException {
        if (isLoaded) {
            return itemList;
        } else {
            throw new DukeException("File not found");
        }

    }
}
