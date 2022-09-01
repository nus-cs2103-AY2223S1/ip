package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage of data from the user specified relative file path.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private boolean isMarked(String s) {
        return s.equals("X");
    }

    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }


    /**
     * Loads tasks from the data file if the file path exists.
     *
     * @return List of tasks that was saved previously.
     * @throws IOException If there is an issue with accessing the file.
     * @throws DukeException If Duke is unable to process the contents of the file as they are invalid data.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File file = new File(filePath);
        if (!file.exists()) {
            this.createFile(file);
            throw new DukeException("Loading error.");
        } else {
            System.out.println("Saved tasks retrieved!\n");
            ArrayList<Task> tasks = new ArrayList<>(100);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                Scanner temp = new Scanner(text);
                temp.useDelimiter("\\[|\\]\\s*|by:\\s*|at:\\s*|\\s*\\(|\\s*\\)");

                String type = temp.next();
                String ignore = temp.next();
                String marked = temp.next();
                String description = temp.next();

                switch (type) {
                case "D": {
                    String ignore1 = temp.next();
                    String date = temp.next();
                    DateTimeFormatter alt = DateTimeFormatter.ofPattern("MMM d yyyy");
                    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String dateAlt = input.format(alt.parse(date));
                    Deadline d = new Deadline(description, dateAlt);
                    if (isMarked(marked)) {
                        d.mark();
                    }
                    tasks.add(d);
                    break;
                }
                case "E": {
                    String ignore1 = temp.next();
                    String date = temp.next();
                    DateTimeFormatter alt = DateTimeFormatter.ofPattern("MMM d yyyy");
                    DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String dateAlt = input.format(alt.parse(date));
                    Deadline d = new Deadline(description, dateAlt);
                    Event e = new Event(description, dateAlt);
                    if (isMarked(marked)) {
                        e.mark();
                    }
                    tasks.add(e);
                    break;
                }
                case "T": {
                    Todo t = new Todo(description);
                    if (isMarked(marked)) {
                        t.mark();
                    }
                    tasks.add(t);
                    break;
                }
                default: {
                    throw new DukeException("Invalid or no input read.");
                }
                }
            }
            return tasks;
        }
    }

    /**
     * Saves the list of tasks from the current run of the Duke program.
     *
     * @param taskArrayList List of tasks to be saved.
     * @throws IOException If there is an issue with writing into the file.
     */
    public void save(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskArrayList.size(); i++) {
            fw.write(taskArrayList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

}
