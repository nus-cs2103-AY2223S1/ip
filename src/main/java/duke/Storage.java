package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String pathFile;

    Storage(String pathFile) {
        this.pathFile = pathFile;
    }

    List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<Task>();
        try {
            File taskFile = new File(pathFile);
            Scanner taskReader = new Scanner(taskFile);
            while (taskReader.hasNextLine()) {
                String data = taskReader.nextLine();
                String[] parts = data.split("\\|");
                String taskType = parts[0];
                int status = Integer.valueOf(parts[1]);
                String task = parts[2];
                if (taskType.equals("D") | taskType.equals("E")) {
                    String dateTime = parts[3];
                    if (taskType.equals("D")) {
                        String[] splitTime = dateTime.split("\\s+");
                        LocalDate date = LocalDate.parse(splitTime[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        LocalTime time = LocalTime.parse(splitTime[2], DateTimeFormatter.ofPattern("HHmm"));
                        Deadline newDeadline = new Deadline(task, LocalDateTime.of(date, time));
                        tasks.add(newDeadline);
                        if (status == 1) {
                            newDeadline.mark();
                        }
                    } else {
                        String[] splitTime = dateTime.split("\\s+");
                        LocalDate date = LocalDate.parse(splitTime[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        LocalTime time = LocalTime.parse(splitTime[2], DateTimeFormatter.ofPattern("HHmm"));
                        Event newEvent = new Event(task, LocalDateTime.of(date, time));
                        tasks.add(newEvent);
                        if (status == 1) {
                            newEvent.mark();
                        }
                    }
                } else if (taskType.equals("T")) {
                    Task newTask = new Todo(task);
                    tasks.add(newTask);
                    if (status == 1) {
                        newTask.mark();
                    }
                }
            }
            taskReader.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found!");
        }
    }

    //here after reading each input we write back to our file to update our task list
    // To do this we manually overwrite our current file content
    // by passing our updated TaskList as our argument
    void save(TaskList tasks) throws DukeException {
        try {
            PrintWriter prw = new PrintWriter(new File(pathFile));
            for (Task task : tasks.getTasks()) {
                prw.println(task.writeToFile());
            }
            prw.close();
        } catch (Exception E) {
            System.out.println("File cannot be written to!");
        }
    }
}
