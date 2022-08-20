package duke.storage;

import duke.command.TaskList;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] words = data.split("\\s*\\|\\s*");
                Task newTask;
                if (words[0].equals("T")) {
                    newTask = new Todo(words[2]);
                } else if (words[0].equals("E")) {
                    newTask = new Event(words[2], words[3]);
                } else {
                    newTask = new Deadline(words[2], LocalDate.parse(words[3]));
                }
                if (words[1].equals("1")) {
                    newTask.setDone();
                }
                tasks.add(newTask);
            }
            myReader.close();
        } catch (IOException e) {
            throw new DukeException();
        }
        return tasks;
    }

    public void saveTasks(TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter(this.filePath);
            StringBuilder output = new StringBuilder();
            ArrayList<Task> tasks = taskList.getTasks();
            for (Task task:tasks) {
                int number;
                if (task.checkDone()) {
                    number = 1;
                } else {
                    number = 0;
                }
                if (task instanceof Todo) {
                    output.append("T | ").append(number).append(" | ").append(task.getDescription()).append("\n");
                } else if (task instanceof Event) {
                    output.append("E | ").append(number).append(" | ").append(task.getDescription()).append(" | ")
                            .append(((Event) task).getTiming()).append("\n");
                } else {
                    output.append("D | ").append(number).append(" | ").append(task.getDescription()).append(" | ")
                            .append(((Deadline) task).getDeadline()).append("\n");
                }
            }
            myWriter.write(output.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
