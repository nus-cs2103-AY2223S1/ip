package BetaGo;

import BetaGo.Exceptions.InvalidDataFileException;
import BetaGo.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class that loads and saves data file.
 */
public class Storage {
    private TaskList tasks;

    /**
     * Constructor for Storage.
     * Initialises TaskList variable.
     */
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Checks if data/duke.txt file exists.
     * If it exists, read the data file and create the TaskList accordingly.
     * Else, create an empty data/duke.txt file.
     * Errors in creating the file or invalid text in the data file will print errors accordingly.
     */
    public void loadFile() {
        try {
            File dir = new File("data");
            File f = new File("data/duke.txt");
            if (dir.exists()) {
                if (f.exists()) {
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
                            Ui.printLoadFileError();
                        }
                    }
                    sc1.close();
                } else {
                    f.createNewFile();
                }
            } else {
                dir.mkdir();
                f.createNewFile();
            }
        } catch (FileNotFoundException e) {
            Ui.printFileNotFoundError();
        } catch (IOException e) {
            Ui.printInputOutputError();
        } catch (InvalidDataFileException e) {
            Ui.printLoadFileError();
        }
    }

    /**
     * Saves tasks in TaskList into data/duke.txt file.
     */
    public void saveItems() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            for(int i = 0; i < this.tasks.size(); i++) {
                Task temp = this.tasks.get(i);
                fw.write(temp.saveTask());
            }
            fw.close();
        } catch (IOException e) {
            Ui.printSaveError();
        }
    }

}
