import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        if (createFile()) {
            try {
                File f = new File(filePath);
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String data = s.nextLine();
                    String[] info = data.split("//");
                    String type = info[0];
                    String done = info[1];
                    String name = info[2];
                    if (type.equals("T")) {
                        Task todo = new Todo(name);
                        if (done.equals("1")) {
                            todo.mark();
                        }
                        tasks.add(todo);
                    } else if (type.equals("D")) {
                        Task deadline = new Deadline(name, LocalDate.parse(info[3]));
                        if (done.equals("1")) {
                            deadline.mark();
                        }
                        tasks.add(deadline);
                    } else if (type.equals("E")) {
                        String date = info[3];
                        String time = info[4];
                        String dt = date + " " + time;
                        Task event = new Event(name, LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        if (done.equals("1")) {
                            event.mark();
                        }
                        tasks.add(event);
                    } else {
                        throw new DukeException("Unknown Task type in text file");
                    }
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("File does not exist");
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String text = tasks.toData();
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving output file");
        }
    }

    private boolean createFile() throws DukeException {
        boolean created = false;
        File data = new File("data");
        File f = new File(filePath);
        try {
            data.mkdir();
            if (!f.createNewFile()) {
                created = true;
            }
        } catch (IOException e) {
            throw new DukeException("Error creating text file");
        }
        return created;

    }
}
