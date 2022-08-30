package duke;

import java.time.LocalDateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Saves tasks to file.
     *
     * @param tasklist Tasklist to be saved.
     * @throws DukeException If file is unable to be saved.
     */
    public void save(TaskList tasklist) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasklist.getSize(); i++) {
                fw.write(tasklist.getTask(i).savedData());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Oh no! I'm unable to save the file.");
        }
    }

    /**
     * Loads tasks from file.
     *
     * @return Tasklist containing tasks.
     * @throws DukeException If file cannot be created.
     * @throws FileNotFoundException  If file cannot be found.
     */
    public TaskList load() throws DukeException, FileNotFoundException {
        ArrayList<Task> taskArr = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created");
            }
        } catch (Exception de) {
            throw new DukeException("New file can't be created");
        }

        try {
            File dukeFile = new File(filePath);
            Scanner sc = new Scanner(dukeFile);

            while (sc.hasNextLine()) {
                String[] strArr = sc.nextLine().split("\\|");
                if (strArr[0].contains("T")) {
                    taskArr.add(new ToDos(strArr[2]));
                }
                if (strArr[0].contains("E")) {
                    String str = strArr[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd MMM yyyy HH:mm");
                    LocalDateTime ldt = LocalDateTime.parse(str, formatter);
                    taskArr.add(new Events(strArr[2], ldt));
                }
                if (strArr[0].contains("D")) {
                    String str = strArr[3];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd MMM yyyy HH:mm");
                    LocalDateTime ldt = LocalDateTime.parse(str, formatter);
                    taskArr.add(new Deadlines(strArr[2], ldt));
                }
            }
            sc.close();
            return new TaskList(taskArr);
        } catch (FileNotFoundException fe) {
            throw new FileNotFoundException("Oops! No file is found.");
        }
    }

}
