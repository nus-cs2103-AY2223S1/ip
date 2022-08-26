package duke.storage;

import duke.parser.FileParser;
import duke.tasks.*;

import java.io.*;

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
