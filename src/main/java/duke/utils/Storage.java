package duke.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.entities.Deadline;
import duke.entities.Event;
import duke.entities.Task;
import duke.entities.Todo;
import duke.enums.Regex;
import duke.exceptions.DukeException;

public class Storage {
    /* Stores the file location string */
    private final String fname;
    private ArrayList<Task> tasks;
    protected DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static Pattern LOAD_REGEX = Pattern.compile(Regex.LOADING_REGEX_WITH_DEADLINE.toString());
    private static final HashMap<String, String> monthToInt = new HashMap<String, String>() {
        {
            put("January", "01");
            put("February", "02");
            put("March", "03");
            put("April", "04");
            put("May", "05");
            put("June", "06");
            put("July", "07");
            put("August", "08");
            put("December", "12");
            put("November", "11");
            put("October", "10");
            put("September", "09");
        }
    };

    public Storage(ArrayList<Task> tasks, String fname) throws DukeException {
        this.tasks = tasks;
        this.fname = createFile(fname);
    }

    /**
     * Creates file with the file name. If the file exists, return its file path.
     * 
     * @param fname The name of the file
     * @return The file path
     * @throws IOException
     * @throws DukeException
     */
    private String createFile(String file_name) throws DukeException {
        try {
            File file = new File(file_name);
            file.createNewFile();
            return file.getPath();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Loads the taskings in the file into the task list
     * 
     * @param fname path of the file to be read
     */
    public void loadFile() throws FileNotFoundException, IOException, DukeException {
        FileReader fr = new FileReader(fname);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        // Read task and add to tasks
        while (line != null) {
            System.out.println(line);
            Matcher m = LOAD_REGEX.matcher(line);
            m.find();
            String task_type = m.group(1);
            String is_marked = m.group(2);
            String description = m.group(4);
            String hour = m.group(6);
            String min = m.group(7);
            String date = m.group(9);
            String month = monthToInt.get(m.group(10));
            String year = m.group(11);
            String deadline = String.format("%s-%s-%s %s:%s", year, month, date, hour, min);
            // Create the task
            Task to_add;
            LocalDateTime time;
            switch (task_type) {
                case "E":
                    time = LocalDateTime.parse(deadline, datetime_format);
                    to_add = new Event(description, time);
                    tasks.add(to_add);
                    break;
                case "D":
                    time = LocalDateTime.parse(deadline, datetime_format);
                    to_add = new Deadline(description, time);
                    tasks.add(to_add);
                    break;
                default:
                    to_add = new Todo(description);
                    tasks.add(to_add);

            }
            if (is_marked.equals("X")) {
                to_add.toggleComplete();
            }
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * Writes the string representation of the task into the save file
     * 
     * @param task to be written
     * @throws IOException
     */
    public void writeToFile(Task task) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(fname));
        StringBuffer inputBuffer = new StringBuffer();
        String line = file.readLine();

        while (line != null) {
            inputBuffer.append(line);
            inputBuffer.append(System.lineSeparator());
            line = file.readLine();
        }
        file.close();
        // Add task
        inputBuffer.append(task.toString());
        inputBuffer.append(System.lineSeparator());

        FileOutputStream fo = new FileOutputStream(fname);
        fo.write(inputBuffer.toString().getBytes());
        fo.close();
    }

    public void markTask(int indx, String newTask) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(fname));
        StringBuffer inputBuffer = new StringBuffer();
        String line = file.readLine();

        while (line != null) {
            if (indx == 0) {
                line = newTask;
            }
            inputBuffer.append(line);
            inputBuffer.append(System.lineSeparator());
            indx -= 1;
            line = file.readLine();
        }
        file.close();
        FileWriter fo = new FileWriter(fname);
        fo.append(inputBuffer.toString());
        fo.flush();
        fo.close();
        ;
    }

    /**
     * Deletes from the task from the saved file
     * 
     * @param task to be deleted
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void deleteFromFile(int indx) throws FileNotFoundException, IOException {
        BufferedReader file = new BufferedReader(new FileReader(fname));
        StringBuffer inputBuffer = new StringBuffer();
        String line = file.readLine();

        while (line != null) {
            if (indx == 0) {
            } else {
                inputBuffer.append(line);
                inputBuffer.append(System.lineSeparator());
            }
            indx -= 1;
            line = file.readLine();
        }
        file.close();
        FileWriter fo = new FileWriter(fname);
        fo.append(inputBuffer.toString());
        fo.flush();
        fo.close();
    }
}