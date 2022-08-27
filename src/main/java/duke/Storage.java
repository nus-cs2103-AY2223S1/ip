package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for the IO operations of the save file for Duke.
 */
public class Storage {

    private String filePath;
    private File saveFile;

    /**
     * Constructor for Storage.
     * @param filePath filepath of the save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.saveFile = new File(filePath);
    }

    /**
     * Saves current task list to the file in specified filepath.
     *
     * @param taskList the tasklist to be saved in the save file.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.getTasks()) {
                fw.write(task.getSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something is wrong nya! I can't seem to save your task list");
        }
    }

    /**
     * Reads the save file from specified file path to update TaskList of Duke.
     *
     * @return List of tasks saved in save file
     * @throws DukeException if save file can't be found.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try {
            if (this.saveFile.exists()) {
                Scanner sc = new Scanner(saveFile);
                while (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();
                    String[] taskInfo = nextLine.split(" \\| ");
                    Task curr = null;
                    switch (taskInfo[0]) {
                    case "T":
                        curr = new ToDo(taskInfo[2]);
                        taskList.add(curr);
                        break;
                    case "D":
                        curr = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[3]));
                        taskList.add(curr);
                        break;
                    case "E":
                        curr = new Event(taskInfo[2], LocalDate.parse(taskInfo[3]));
                        taskList.add(curr);
                        break;
                    default:
                        break;
                    }
                    if (taskInfo[1].equals("1")) {
                        curr.markAsDone();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry! I can't find your file nya");
        }
        return taskList;
    }

}
