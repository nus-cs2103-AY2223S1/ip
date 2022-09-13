package Duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    /**
     * Creates Storage instance.
     * @param filepath the file name.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Saves the tasklist.
     * @param items the stringified tasklist.
     */
    public void saveFile(ArrayList<String> items) {
        FileWriter fileWriter = null;

        try {
            File file = new File(filePath);
            fileWriter = new FileWriter(file);

            for (int i = 0; i < items.size(); i++) {
                fileWriter.write(items.get(i));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    /**
     * Loads the file.
     * @return the tasklist.
     * @throws DukeException when file is invalid.
     */
    public TaskList loadFile() throws DukeException {
        BufferedReader reader = null;
        TaskList items = new TaskList(new ArrayList<>());

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                items = addToTaskList(items, line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return items;
    }

    public TaskList addToTaskList(TaskList items, String line) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");
        String[] parse = line.split(" ~ ");
        Task tsk = null;
        switch (parse[0]) {
            case "T":
                tsk = new Todo(parse[2]);
                break;
            case "E":
                tsk = new Event(parse[2], parse[3]);
                break;
            case "D":
                tsk = new Deadline(parse[2], parse[3], formatter);
                break;
            default:
                throw new DukeException("Invalid File");
        }
        if (parse[1].equals("X")) {
            tsk.markAsDone();
        }
        items.addTask(tsk);
        return items;
    }
}

