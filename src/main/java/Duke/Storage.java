package Duke;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads saved task from file to taskList
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> load() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        ArrayList<Task> contents = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
            String line = reader.readLine();
            while (line != null) {
                String[] parse = line.split(" - ");
                Task t = null;
                if (parse[0].equals("T")) {
                    assert parse.length == 3 : "Invalid ToDo Input";
                    t = new ToDo(parse[2]);
                } else if (parse[0].equals("E")) {
                    assert parse.length == 4 : "Invalid Event Input";
                    t = new Event(parse[2], LocalDate.parse(parse[3], formatter));
                } else if (parse[0].equals("D")) {
                    assert parse.length == 4 : "Invalid Deadline Input";
                    t = new Deadline(parse[2], LocalDate.parse(parse[3], formatter));
                } else {
                    throw new DukeException(Constants.invalidFile);
                }
                if (parse[1].equals("[X]")) {
                    t.setMarked();
                }
                contents.add(t);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return contents;
    }

    /**
     * Writes the stringified tasks to file
     *
     * @param lst list of stringified tasks
     */
    public void save(List<String> lst) {
        try {
            File file = new File(this.filepath);
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < lst.size(); i++) {
                fileWriter.write(lst.get(i));
            }
            fileWriter.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
