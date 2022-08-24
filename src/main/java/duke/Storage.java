package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected ArrayList<Task> itemList = new ArrayList<>();
    protected boolean isLoaded;

    public Storage(String filePath) {
        try {
            readFile(filePath);
            isLoaded = true;
        } catch (FileNotFoundException e) {
            //make file
            System.out.println("An error occurred.");
            makeFile();
            isLoaded = false;
        }
    }
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

    public void addToList(String taskItem) {
        String[] details = taskItem.split("[|]");
        Task toAdd = new ToDo("");
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

    public void readFile(String fileLocation) throws FileNotFoundException {
        File dukeHistory = new File(fileLocation);
        Scanner myReader = new Scanner(dukeHistory);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            addToList(data);
        }
        myReader.close();
    }

    public ArrayList<Task> load() throws DukeException {
        if (isLoaded) {
            return itemList;
        } else {
            throw new DukeException("File not found");
        }

    }
}
