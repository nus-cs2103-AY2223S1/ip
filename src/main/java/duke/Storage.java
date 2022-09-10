package duke;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private ArrayList<Task> log;
    private BufferedWriter writer;
    private BufferedReader reader;

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Goes through the save file, loading data.
     *
     * @param t TaskList in which data is loaded to.
     * @throws IOException  If an error arises from file access.
     * @throws DukeException If something goes wrong with commands sent to Duke.
     */
    public void load(TaskList t) throws IOException, DukeException {

        //TODO: un-hardcode directory checks with a parser

        //checks whether directory exists, else makes a new one
        File d = new File("data");
        if (!d.getAbsoluteFile().exists()) {
            new File("data").mkdirs();
        }

        File saveFile = new File("data/tasks.txt");
        saveFile.createNewFile();

        // Creating the file reader
        reader = new BufferedReader(new FileReader(filePath));
        File f = new File(filePath);
        if (f.getAbsoluteFile().exists()) {
            tryLoading();
        } else {
            log = new ArrayList<>();
        }
        reader.close();

        t.setLog(log);
    }

    private void tryLoading() throws IOException, DukeException {
        log = new ArrayList<>();
        String s;
        char first;
        while ((s = reader.readLine()) != null) {
            first = s.charAt(0);
            parse(first, s);
        }
    }

    private void parse(char c, String s) throws DukeException {
        if (c == 'T') {
            log.add(Todo.load(s));
        } else if (c == 'D') {
            log.add(Deadline.load(s));
        } else if (c == 'E') {
            log.add(Event.load(s));
        } else {
            throw new DukeException("Something went wrong with reading the save");
        }
    }

    /**
     * Goes through the TaskList, saving all tasks to the file.
     *
     * @param t TaskList to be saved.
     * @throws IOException  If an error occurs from file access.
     */
    public void save(TaskList t) throws IOException {
        log = t.getLog();

        writer = new BufferedWriter(new FileWriter("data\\tasks.txt", false));
        for(int i = 0; i < log.size(); i++) {
            writer.write(log.get(i).saveString());
            writer.newLine();
        }
        writer.close();
    }


}
