package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


public class Storage {
    private final String saveFilePath;

    public Storage() {
        this.saveFilePath = System.getProperty("user.dir") + "\\data\\saves\\tasks.txt";
    }

    public void saveDuke(TaskList tasks) throws IOException {
        File save = new File(saveFilePath);
        FileWriter saveWriter = new FileWriter(save);
        String saveString = "";

        for (int i = 0; i < tasks.size(); i++) {
            saveString += tasks.getTask(i) + "\n";
        }
        saveWriter.write(saveString);
        saveWriter.flush();
        saveWriter.close();
    }

    private ToDo loadToDo(boolean taskWasDone, String taskDesc) {
        ToDo todo = new ToDo("todo " + taskDesc);
        if (taskWasDone) {
            todo.markDone();
        } else {
            todo.markUndone();
        }
        return todo;
    }

    private Deadline loadDeadline(boolean taskWasDone, String taskDesc) {
        String[] deadlineSpecifics = taskDesc.split(" \\(by: ");
        Deadline deadline = new Deadline(deadlineSpecifics[0],
                deadlineSpecifics[1].substring(0, deadlineSpecifics[1].length() - 1));
        if (taskWasDone) {
            deadline.markDone();
        } else {
            deadline.markUndone();
        }
        return deadline;
    }

    private Event loadEvent(boolean taskWasDone, String taskDesc) {
        String[] eventSpecifics = taskDesc.split(" \\(at: ");
        Event event = new Event(eventSpecifics[0], eventSpecifics[1].substring(0, eventSpecifics[1].length() - 1));
        if (taskWasDone) {
            event.markDone();
        } else {
            event.markUndone();
        }
        return event;
    }

    private Task loadTaskDifferentiator(String data) throws DukeException {
        String taskType = data.substring(0, 3);
        boolean taskWasDone = data.startsWith("[X]", 4);
        String taskDesc = data.substring(8);
        switch (taskType) {
        case "[T]":
            return loadToDo(taskWasDone, taskDesc);
        case "[D]":
            return loadDeadline(taskWasDone, taskDesc);
        case "[E]":
            return loadEvent(taskWasDone, taskDesc);
        default:
            throw new DukeException("I seem to forgotten what tasks you have asked me to remember."
                    + "Do you know where I left my notes?");
        }
    }

    public ArrayList<Task> load() throws DukeException, IOException {
        File dataDir = new File(System.getProperty("user.dir") + "\\data");
        File savesDir = new File(System.getProperty("user.dir") + "\\data\\saves");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        if (!savesDir.exists()) {
            savesDir.mkdir();
        }

        File save = new File(saveFilePath);
        if (save.exists()) {
            Scanner sc = new Scanner(save);
            ArrayList<Task> res = new ArrayList<>();
            while (sc.hasNextLine()) {
                try {
                    String command = sc.nextLine();
                    res.add(loadTaskDifferentiator(command));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            sc.close();
            return res;
        } else {
            return new ArrayList<>();
        }
    }
}
