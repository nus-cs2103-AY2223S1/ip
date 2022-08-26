package duke.storage;

import duke.parser.FileParser;
import duke.tasks.*;

import java.io.*;

public class Storage {

    private static final TaskList TASK_LIST = new TaskList();
    private static final FileParser FILE_PARSER = new FileParser(TASK_LIST);

    public void save() {
        try {
            FileWriter myWriter = new FileWriter("data/tasklist.txt");
            myWriter.write(TASK_LIST.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public TaskList load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("data/tasklist.txt"));
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
