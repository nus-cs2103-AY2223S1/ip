package duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.parser.FileParser;
import duke.tasks.TaskList;

/**
 * Loads tasklist from fixed path and saves tasklist from current session into same path.
 */
public class Storage {

    private static final TaskList TASK_LIST = new TaskList();
    private static final FileParser FILE_PARSER = new FileParser(TASK_LIST);

    /**
     * Writes and save current list into tasklist.txt
     */
    public void save() {
        try {
            FileWriter myWriter = new FileWriter("tasklist.txt");
            myWriter.write(TASK_LIST.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasklist.txt file.
     *
     * @return tasklist in tasklist.txt file
     */
    public TaskList load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("tasklist.txt"));
            String line = in.readLine();
            while (line != null) {
                FILE_PARSER.handle(line);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            save();
        } catch (IllegalStateException e) {
            System.out.println("Tasklist is invalid. Writing new file...");
            save();
        } catch (IOException e) {
            System.out.println("Tasklist cannot be read. Writing new file...");
            save();
        }
        return TASK_LIST;
    }
}
