package Duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.format.DateTimeFormatter;

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
     * @throws DukeException
     */
    public TaskList loadFile() throws DukeException {
        BufferedReader reader = null;
        TaskList items = new TaskList(new ArrayList<>());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss");

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                String[] parse = line.split(" ~ ");
                Task tsk = null;
                if (parse[0].equals("T")) {
                    tsk = new Todo(parse[2]);
                } else if (parse[0].equals("E")) {
                    tsk = new Event(parse[2], parse[3]);
                } else if (parse[0].equals("D")) {
                    tsk = new Deadline(parse[2], parse[3], formatter);
                } else {
                    throw new DukeException("Invalid File");
                }
                if (parse[1].equals("X")) {
                    tsk.markAsDone();
                }
                items.addTask(tsk);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    return items;
                }
            } else {
                return items;
            }
        }
    }
}

