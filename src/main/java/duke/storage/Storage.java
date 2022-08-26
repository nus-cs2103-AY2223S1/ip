package duke.storage;

import duke.DukeException;
import duke.task.TaskList;
import duke.parser.DateParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> storedTasks;
    private String filePath = "./data/sheep.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String storedTask = sc.nextLine();
                String[] splitTask = storedTask.split(" \\| ");
                Task task;
                if (splitTask[0].equals("T")) {
                    task = new Todo(splitTask[2]);
                } else if (splitTask[0].equals("D")) {
                    if (DateParser.isDateValid(splitTask[2])) {
                        task = new Deadline(splitTask[2], DateParser.convertToLocalDate(splitTask[2]));
                    } else {
                        System.out.println("OOPS!!! Look like your date format is incorrect");
                        continue;
                    }
                } else {
                    task = new Event(splitTask[2], splitTask[3]);
                }
                if(splitTask[1].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch(FileNotFoundException e) {
            throw new DukeException("Cannot find the given file.");
        }
    }

    public void storeTasks(TaskList tasks) {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            String storedTasks = "";
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                if (i < tasks.getNumberOfTasks() - 1) {
                    storedTasks += tasks.convertTaskToMemoryString(i) + "\n";
                } else {
                    storedTasks += tasks.convertTaskToMemoryString(i);
                }
            }
            fw.write(storedTasks);
            fw.close();
        } catch (IOException e) {
            String fileDirectory = filePath.replace(filePath.substring(filePath.lastIndexOf("/")), "");
            File file = new File(fileDirectory);
            if (file.mkdir()) {
                storeTasks(tasks);
            } else {
                System.out.println("OOPS!!! There is an error occurred when trying to store the tasks");
            }
        }
    }
}
