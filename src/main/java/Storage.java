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
                if (keyword.equals("T")) {
                    Task newTask = new ToDos(taskDetails);
                    if (isDone.equals("1")) newTask.markAsDone();
                    tasks.add(newTask);
                } else {
                    LocalDateTime dateTime = LocalDateTime.parse(words[3], DATE_TIME_FORMATTER);
                    if (keyword.equals("D")) {
                        Task newTask = new Deadlines(taskDetails, dateTime);
                        if (isDone.equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                    } else if (keyword.equals("E")) {
                        Task newTask = new Events(taskDetails, dateTime);
                        if (isDone.equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                    } else {
                        System.out.println("SoCCat cannot recognise the type of this task: " + keyword);
                    }
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
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description);
                } else if (task instanceof Deadlines) {
                    writer.write("D|");
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description + "|");
                    writer.write(((Deadlines) task).by.format(DATE_TIME_FORMATTER));
                } else if (task instanceof Events) {
                    writer.write("E|");
                    writer.write(task.isDone ? "1|" : "0|");
                    writer.write(task.description + "|");
                    writer.write(((Events) task).duration.format(DATE_TIME_FORMATTER));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }
}
