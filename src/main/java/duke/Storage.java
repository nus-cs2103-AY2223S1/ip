package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage that stores the command list.
 */
public class Storage {
    protected File file;
    private String input;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
    }

    @Override
    public String toString() {
        if (input != null && !input.isEmpty()) {
            return "Storage[input=" + input + "]";
        } else {
            return "Storage[input=not set]";
        }
    }

    /**
     * Creates the directory and the file if not already exist.
     */
    public void initialiseFile() {
        File directory = new File(this.file.getParent());
        if (!directory.exists()) {
            directory.mkdir();
        }
        assert directory.exists() : "The directory should already exist";
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            assert file.exists() : "The file should already exist";
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Stores the current list command in the file.
     *
     * @param list A list of String type.
     */
    public void push(String list) throws IOException {
        initialiseFile();
        FileWriter fileWriter = new FileWriter(file);
        this.input = list;
        fileWriter.write(list);
        fileWriter.close();
    }

    /**
     * Returns a String representation of the list.
     *
     * @return String
     */
    public String load() throws IOException {
        try {
            File myObj = new File("data/tasks.txt");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "There are no tasks in your list.";
        }
    }
}