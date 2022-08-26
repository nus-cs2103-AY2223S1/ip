import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class SavedTasks {
    /** Relative location that the output file is created and stored */
    private final String SAVED_FILE_PATH = "data/saved_tasks.txt";

    /** List that keeps track of tasks */
    private final ArrayList<Task> tasks = new ArrayList<>(100);

    public SavedTasks() throws IOException, DukeException {
        File file = new File(SAVED_FILE_PATH);
        if (!file.exists()) {
            System.out.println("No prior tasks saved. Please input your tasks...");
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            System.out.println("Saved tasks retrieved. Here they are...");
            Scanner sc =  new Scanner(file);
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                Scanner temp = new Scanner(text);
                temp.useDelimiter("\\[|\\]\\s*|by:\\s*|at:\\s*|\\(|\\)");

                String type = temp.next();
                String ignore = temp.next();
                String marked = temp.next();
                String description = temp.next();
                if (type.equals("D")) {
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
                } else if (type.equals("E")) {
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
                } else if (type.equals("T")) {
                    Todo t = new Todo(description);
                    if (isMarked(marked)) {
                        t.mark();
                    }
                    tasks.add(t);
                } else {
                    throw new DukeException("Invalid or no input read.");
                }
            }
        }
    }

    /**
     * Returns a boolean that represents if the Task is marked or not.
     *
     * @param s Input string to be tested.
     * @return Whether the task is marked
     */
    private boolean isMarked(String s) {
        return s.equals("X");
    }

    /**
     * Returns the list of tasks retrieved from the text file.
     *
     * @return List of Tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Writes data from a list to the file.
     *
     * @param arr List that records each line to be written into the file.
     * @throws IOException If something occurs during writing into the file.
     */
    public void writeToFile(ArrayList<Task> arr) throws IOException {
        FileWriter fw = new FileWriter(SAVED_FILE_PATH);
        for(int i = 0; i < arr.size(); i++) {
            fw.write(arr.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }
}
