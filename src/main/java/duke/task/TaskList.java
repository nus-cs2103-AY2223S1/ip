package duke.task;

import duke.DukeException;
import duke.util.DateAndTimeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final int TASKTYPE = 0;
    private final int ISTASKDONE = 1;
    private final int TASKDESCRIPTION = 2;
    private final int TASKTIME = 3;

    private ArrayList<Task> tasks;
    private BufferedReader br;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public TaskList(BufferedReader br) throws DukeException {
        this.br = br;
        this.tasks = load();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public String taskListToSaveString() {
        StringBuilder saveString = new StringBuilder();
        for (Task task : this.tasks) {
            saveString.append(task.storedTaskString()).append("\n");
        }
        return saveString.toString();
    }

    private ArrayList<Task> load() throws DukeException {
        ArrayList<Task> storedTasks = new ArrayList<>(100);
        try {
            String line = this.br.readLine();
            while (line != null) {
                String[] taskArr = line.split("\\|");
                switch (taskArr[TASKTYPE]) {
                case "T":
                    Todos todo = new Todos(taskArr[TASKDESCRIPTION]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        todo.markAsDone();
                    }
                    storedTasks.add(todo);
                    break;
                case "D":
                    Deadlines deadline = new Deadlines(taskArr[TASKDESCRIPTION], taskArr[TASKTIME],
                            DateAndTimeParser.validateAndParse(taskArr[TASKTIME]));
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        deadline.markAsDone();
                    }
                    storedTasks.add(deadline);
                    break;
                case "E":
                    Events event = new Events(taskArr[TASKDESCRIPTION], taskArr[TASKTIME]);
                    if (Boolean.parseBoolean(taskArr[ISTASKDONE])) {
                        event.markAsDone();
                    }
                    storedTasks.add(event);
                    break;
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException("Failure in reading file, creating new save file");
        }
        return storedTasks;
    }
}
