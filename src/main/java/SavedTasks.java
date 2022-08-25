import java.io.File;
import java.io.IOException;
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
                    Deadline d = new Deadline(description, date);
                    if (isMarked(marked)) {
                        d.mark();
                    }
                    tasks.add(d);
                } else if (type.equals("E")) {
                    String ignore1 = temp.next();
                    String date = temp.next();
                    Event e = new Event(description, date);
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
     * @param s
     * @return Whether the task is marked
     */
    private boolean isMarked(String s) {
        return s.equals("X");
    }

    /**
     * Appends the specified string to the file.
     *
     * @param text
     * @throws IOException If something occurs during writing into the file
     */
    public void appendToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(SAVED_FILE_PATH, true);
        fw.write(text + System.lineSeparator());
        fw.close();
    }

    /**
     * Returns the list of tasks retrieved from the text file.
     *
     * @return List of Tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
