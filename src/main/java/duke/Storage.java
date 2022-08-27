package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * A class that creates the Storage object.
 */
public class Storage {
    private String pathName;
    private File file;
    private TaskList previousTaskList;

    /**
     * A constructor that intialises the Storage object.
     *
     * @param pathName Path name of the text file where the list of tasks is stored.
     * @param previousTaskList ArrayList of tasks.
     * @throws IOException
     */
    public Storage(String pathName, TaskList previousTaskList) throws IOException {
        this.pathName = pathName;
        this.file = new File(pathName);
        this.previousTaskList = previousTaskList;

        if (!Files.exists(Path.of(pathName))) {
            file.createNewFile();
        }
    }

    /**
     * Updates the text file where the list of tasks is stored.
     *
     * @throws IOException
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("taskList.txt", false);
        fw.write(this.previousTaskList.printList());
        fw.close();
    }

    /**
     * Loads the list of tasks from the text file.
     *
     * @throws IOException
     */
    public void loadUpData() throws IOException {
        Scanner sc = new Scanner(this.file);

        if(!sc.hasNextLine()) {
            return;
        }

        String header = sc.nextLine();

        while(sc.hasNextLine()) {
            String task = sc.nextLine();
            char type = task.charAt(3);
            char status = task.charAt(6);

            if (type == 'E') {
                int at = task.indexOf("(at:");
                String timingWithBracket = task.substring(at + 5, task.lastIndexOf(")"));
                String description = task.substring(9, at - 1) + " " + "/at" + timingWithBracket;

                this.previousTaskList.addTask(new Event(" " + task.substring(9, at - 1), timingWithBracket));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            } else if (type == 'D') {
                int by = task.indexOf("(by:");
                String timingWithBracket = task.substring(by + 5, task.lastIndexOf(")"));
                String description = task.substring(9, by - 1) + " " + "/by" + timingWithBracket;

                this.previousTaskList.addTask(new Deadline(" " + task.substring(9, by - 1),
                        timingWithBracket));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            } else {
                String description = task.substring(9);

                this.previousTaskList.addTask(new ToDo(" " + description));
                this.writeToFile();

                if (status == 'X') {
                    this.previousTaskList.getTaskAtCurrentIndex().markAsDone();
                    writeToFile();
                }

                this.previousTaskList.incrementIndex();
            }
        }
    }

    /**
     * Returns string of list of tasks with the keyword
     *
     * @param keyword Keyword to find the relevant tasks
     * @return List of tasks with the keyword
     * @throws FileNotFoundException If file does not exist
     */
    public String findTasks(String keyword) throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);

        String output = "Here are the matching tasks in your list:\n";
        int index = 1;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(keyword)) {
                line = index + line.substring(1);
                output += (line + "\n");
                index += 1;
            }
        }

        return output;
    }
}

