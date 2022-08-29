package duke;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final static String FILEPATH = "data/Duke.txt";
    
    public Storage() {
    }

    protected ArrayList<Task> loadFromDisk() throws DukeException {

        ArrayList<Task> tasks = new ArrayList<>();
        try { 
            File file = new File(FILEPATH);
            file.getParentFile().mkdirs();
            file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = reader.readLine()) != null) { 
                String[] words = line.split("[|]", 4);
                String keyword = words[0];
                String isDone = words[1];
                String taskDetails = words[2];
                Task task = null;
                
                if (keyword.equals("T")) {
                    task = new ToDos(taskDetails);
                } else {
                    if (keyword.equals("D")) {
                        LocalDateTime dateTime = LocalDateTime.parse(words[3], DATE_TIME_FORMATTER);
                        task = new Deadlines(taskDetails, dateTime);
                    } else if (keyword.equals("E")) {
                        LocalDateTime dateTime = LocalDateTime.parse(words[3], DATE_TIME_FORMATTER);
                        task = new Events(taskDetails, dateTime);
                    } else {
                        System.out.println("SoCCat cannot recognise the type of this task: " + keyword);
                    }
                }
                if (isDone.equals("1") && task != null) {
                    task.markAsDone();
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
        return tasks;
    }

    protected void saveToDisk(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(FILEPATH);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                if (task instanceof ToDos) {
                    writer.write("T|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription());
                } else if (task instanceof Deadlines) {
                    writer.write("D|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription() + "|");
                    writer.write(((Deadlines) task).getBy().format(DATE_TIME_FORMATTER));
                } else if (task instanceof Events) {
                    writer.write("E|");
                    writer.write(task.getStatus() ? "1|" : "0|");
                    writer.write(task.getDescription() + "|");
                    writer.write(((Events) task).getDuration().format(DATE_TIME_FORMATTER));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}
