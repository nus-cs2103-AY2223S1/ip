package duke.task;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.tools.Ui;
import duke.tools.TaskParser;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * List of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public ArrayList<Task> addTask(String input)
            throws TaskNotFoundException, ContentNotFoundException, DateNotFoundException, DateTimeParseException {
        TaskParser taskParser = new TaskParser(input);
        String taskInfo = taskParser.getTaskInfo();

        switch (taskParser.getCommand()) {
            case TODO:
                ToDo todo = ToDo.of(taskInfo, "UI");
                tasks.add(todo);
                Ui.addTaskToast(TaskParser.TASKS.TODO, todo, tasks.size());
                break;
            case DEADLINE:
                Deadline deadline = Deadline.of(taskInfo, "UI");
                tasks.add(deadline);
                Ui.addTaskToast(TaskParser.TASKS.DEADLINE, deadline, tasks.size());
                break;
            case EVENT:
                Event event = Event.of(taskInfo, "UI");
                tasks.add(event);
                Ui.addTaskToast(TaskParser.TASKS.EVENT, event, tasks.size());
                break;
            default:
                throw new TaskNotFoundException(
                        "Command not found: " + taskParser.getCommand());
        }
        return tasks;
    }

    public TaskList loadTask(String[] details) {
        try {
            if (details[0].trim().contentEquals("T")) {
                if (details.length > 1) {
                    ToDo todo = ToDo.of(details[1], "FILE");
                    tasks.add(todo);

                    Ui.loadTaskToast(todo, tasks.size());
                }
            } else if (details[0].trim().contentEquals("D")) {
                if (details.length > 1) {
                    Deadline deadline = Deadline.of(details[1], "FILE");
                    tasks.add(deadline);

                    Ui.loadTaskToast(deadline, tasks.size());
                }
            } else if (details[0].trim().contentEquals("E")) {
                if (details.length > 1) {
                    Event event = Event.of(details[1], "FILE");
                    tasks.add(event);

                    Ui.loadTaskToast(event, tasks.size());
                }
            }
        } catch (DateNotFoundException e) {
            Ui.noDateExceptionToast(e);
        } finally {
            return this;
        }
    }

    public Task get(int i) {

        return tasks.get(i);
    }

    public int size() {

        return tasks.size();
    }

    public void list() {

        Ui.printList(tasks);
    }

    public Task markTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.done();
        return taskMark;
    }

    public Task unmarkTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.notDone();
        return taskMark;
    }

    public Task deleteTask(Integer n) {
        Task deletedTask = tasks.remove(n.intValue() - 1);
        Ui.deleteTaskToast(deletedTask, tasks.size());
        return deletedTask;
    }

    /**
     * Returns ArrayList of tasks that contain the given word.
     * @param word Word user is searching for.
     * @return ArrayList containing tasks that contain the given word.
     */
    public ArrayList<Task> findTasks(String word) {
        ArrayList<Task> searchResult = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.contains(word)) {
                searchResult.add(curr);
            }
        }
        return searchResult;
    }

    public TaskList() {

        tasks = new ArrayList<>();
    }
}
