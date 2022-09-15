package betago;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import betago.tasks.Task;

/**
 * Storage class that loads and saves data file.
 */
public class Storage {
    private final TaskList tasks;
    private String fileName;

    /**
     * Constructor for Storage.
     * Initialises TaskList variable.
     */
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if data/betago.txt file exists.
     * If it exists, calls the convertFile method accordingly to read file.
     * Else, create an empty betago.txt file.
     * Errors in creating the file or invalid text in the data file will print errors accordingly.
     */
    public void loadDefaultFile() {
        try {
            File dir = new File("data");
            File f = new File("data/betago.txt");
            this.fileName = "data/betago.txt";
            if (!dir.exists()) {
                dir.mkdir();
                f.createNewFile();
            } else if (!f.exists()) {
                f.createNewFile();
            } else {
                convertFile(f);
            }
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        } catch (IOException e) {
            Ui.printInputOutputError();
        } catch (DukeException e) {
            Ui.printLoadFileError();
        }
    }

    /**
     * Reads the data file and converts the entries into their respective tasks.
     * @param f The file to be read from.
     * @throws DukeException If there is an invalid data entry in the data file.
     * @throws FileNotFoundException If the file f does not exist.
     */
    public void convertFile(File f) throws DukeException, FileNotFoundException {
        Scanner sc1 = new Scanner(f);
        while (sc1.hasNextLine()) {
            String str = sc1.nextLine();
            if (str.charAt(0) == 'T') {
                this.tasks.loadTodo(str);
            } else if (str.charAt(0) == 'D') {
                this.tasks.loadDeadline(str);
            } else if (str.charAt(0) == 'E') {
                this.tasks.loadEvent(str);
            } else {
                throw new DukeException("The data in the file is invalid and cannot be read.");
            }
        }
        sc1.close();
    }

    /**
     * Checks if specific file exists.
     * If it exists, calls the convertFile method accordingly to read file.
     * @param str Input from the user containing load command and filename.
     * @throws DukeException If file does not exist, is not a .txt file or contains invalid entries.
     */
    public String loadNewFile(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        if (inputs.length != 2) {
            throw new DukeException("Please enter the file that you want to load in the format: 'load (filename)'");
        }
        try {
            String pathName = "data/" + inputs[1];
            File f = new File(pathName);
            if (!f.exists()) {
                throw new DukeException("Apologies Human. The specific file does not exist!\n"
                        + "Do ensure the specific file is stored in the data folder.");
            } else if (!pathName.toLowerCase().endsWith(".txt")) {
                throw new DukeException("Please ensure the data file to be loaded is a .txt file!");
            }
            this.tasks.refreshList(); // Current implementation clears list even if there are invalid entries!
            convertFile(f);
            this.fileName = pathName;
            return "The data in " + pathName + " has been successfully loaded!";
        } catch (FileNotFoundException e) {
            throw new DukeException("Error in finding specific file.");
        }
    }
    /**
     * Saves tasks in TaskList into data/betago.txt file.
     */
    public void saveItems() {
        try {
            FileWriter fw = new FileWriter(this.fileName, false);
            for (int i = 0; i < this.tasks.getSize(); i++) {
                Task temp = this.tasks.get(i);
                fw.write(temp.saveTask());
            }
            fw.close();
        } catch (IOException e) {
            Ui.printSaveError();
        }
    }

}
