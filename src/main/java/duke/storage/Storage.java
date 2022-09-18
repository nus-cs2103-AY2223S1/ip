package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.loanbook.Contact;
import duke.loanbook.Loanbook;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents storage for tasks.
 *
 * @author Elgin
 */
public class Storage {
    /** Path to file where tasks are stored. */
    private static Path tasksFilePath = Paths.get(System.getProperty("user.dir"), "src", "data", "tasks.txt");

    /** Path to file where loans are stored. */
    private static Path loansFilePath = Paths.get(System.getProperty("user.dir"), "src", "data", "loans.txt");

    /** File reference where tasks are stored. */
    private static File taskFile = new File(tasksFilePath.toString());

    /** File reference where loans are stored. */
    private static File loanFile = new File(loansFilePath.toString());

    /**
     * Constructor for Storage. Create directories and files if missing.
     *
     */
    public Storage() {
        createDirectories("src/data");
        createFiles();
    }

    /**
     * Create directories leading up to the file if it is missing.
     *
     * @param pathToDirectory The path to the directory where the file resides.
     */
    public void createDirectories(String pathToDirectory) {
        String[] directories = pathToDirectory.split("/");

        // Path to directory where file that stores tasks is at
        Path dirPath = Paths.get(System.getProperty("user.dir"), directories);

        // Making sure that missing directories are created.
        File dir = new File(dirPath.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Creates the files if it is missing to store the data.
     *
     */
    public void createFiles() {
        if (!Storage.taskFile.exists()) {
            try {
                this.writeToFile(Storage.tasksFilePath, "", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!Storage.loanFile.exists()) {
            try {
                this.writeToFile(Storage.loansFilePath, "", true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from data/tasks.txt file.
     *
     * @throws FileNotFoundException If file cannot be opened by Scanner.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tempList = new ArrayList<>();

        if (Storage.taskFile.exists() && !Storage.taskFile.isDirectory()) {
            Scanner fileScanner = new Scanner(Storage.taskFile);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(" \\| ");

                switch (details[0]) {
                case "T":
                    tempList.add(new ToDo(details[2], details[1].equals("1")));
                    break;
                case "D":
                    tempList.add(new Deadline(details[2], details[3], details[4], details[1].equals("1")));
                    break;
                case "E":
                    tempList.add(new Event(details[2], details[3], details[4], details[1].equals("1")));
                    break;
                default:
                    throw new DukeException("File contains lines that cannot be validated as a Task.");
                }
            }
        }

        return tempList;
    }

    /**
     * Loads loanbook with contacts from data/loans.txt file.
     *
     * @return An arraylist of all contacts loaded from file.
     * @throws FileNotFoundException If file cannot be opened by scanner.
     */
    public ArrayList<Contact> loadLoanbook() throws FileNotFoundException {
        ArrayList<Contact> tempList = new ArrayList<>();

        if (Storage.loanFile.exists() && !Storage.loanFile.isDirectory()) {
            Scanner fileScanner = new Scanner(Storage.loanFile);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(" \\| ");

                String name = details[0];
                String phoneNumber = details[1];
                double amount = Double.parseDouble(details[2]);
                boolean isOwe = details[3].equals("1");
                tempList.add(new Contact(name, phoneNumber, amount, isOwe));
            }
        }

        return tempList;
    }

    /**
     * Writes to file that contains all the tasks.
     *
     * @param textToAdd The text to be added to the file.
     * @throws IOException If there are errors in input/output to the file.
     */
    private void writeToFile(Path filePath, String textToAdd, boolean isOverwrite) throws IOException {
        FileWriter fw = isOverwrite
                ? new FileWriter(filePath.toString())
                : new FileWriter(filePath.toString(), true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves tasks into a duke.txt file.
     *
     * @param tasks All the tasks to save.
     */
    public void save(TaskList tasks) {
        // Overwrite the file
        try {
            this.writeToFile(Storage.tasksFilePath, "", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Task t : tasks.getTasks()) {
            // 1 denotes task is done, 0 denotes task is not done.
            String taskDone = t.isDone() ? "1" : "0";
            try {
                if (t instanceof ToDo) {
                    this.writeToFile(Storage.tasksFilePath, "T | " + taskDone + " | " + t.getTaskName() + "\n", false);
                } else if (t instanceof Deadline) {
                    this.writeToFile(Storage.tasksFilePath, "D | " + taskDone + " | " + t.getTaskName() + " | "
                            + ((Deadline) t).getDate() + " | " + ((Deadline) t).getTime() + "\n", false);
                } else if (t instanceof Event) {
                    this.writeToFile(Storage.tasksFilePath, "E | " + taskDone + " | " + t.getTaskName() + " | "
                            + ((Event) t).getDate() + " | " + ((Event) t).getTime() + "\n", false);
                } else {
                    throw new DukeException("Invalid event found in save! Aborting...");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Saves loan in a text file.
     *
     * @param loanbook The loanbook that contains all entries of the loan.
     */
    public void saveLoans(Loanbook loanbook) {
        // Overwrite the file
        try {
            this.writeToFile(Storage.loansFilePath, "", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Contact contact : loanbook.getAllContacts()) {
            String line = contact.getName() + " | " + contact.getPhoneNumber() + " | "
                    + contact.getAmount() + " | " + contact.isOwe() + "\n";
            try {
                this.writeToFile(Storage.loansFilePath, line, false);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
