package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.comparator.CompletionComparator;
import duke.comparator.DateComparator;
import duke.comparator.NameComparator;
import duke.comparator.TaskTypeComparator;
import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.tools.CommandParser;
import duke.tools.TaskParser;
import duke.tools.Ui;

/**
 * List of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {

        tasks = new ArrayList<>();
    }


    public String addTask(String input)
            throws TaskNotFoundException, ContentNotFoundException, DateNotFoundException, DateTimeParseException {
        TaskParser taskParser = new TaskParser(input);
        String taskInfo = taskParser.getTaskInfo();
        String response = "";

        switch (taskParser.getCommand()) {
        case TODO:
            ToDo todo = ToDo.of(taskInfo, "UI");
            tasks.add(todo);
            response = Ui.addTaskToast(TaskParser.Tasks.TODO, todo, tasks.size());
            break;
        case DEADLINE:
            Deadline deadline = Deadline.of(taskInfo, "UI");
            tasks.add(deadline);
            response = Ui.addTaskToast(TaskParser.Tasks.DEADLINE, deadline, tasks.size());
            break;
        case EVENT:
            Event event = Event.of(taskInfo, "UI");
            tasks.add(event);
            response = Ui.addTaskToast(TaskParser.Tasks.EVENT, event, tasks.size());
            break;
        default:
            throw new TaskNotFoundException(
                    "Command not found: " + taskParser.getCommand());
        }
        return response;
    }

    public TaskList loadTask(String[] details) {
        try {
            loadLogic(details);
        } catch (DateNotFoundException e) {
            System.out.println(Ui.noDateExceptionToast(e));
        } finally {
            return this;
        }
    }

    private void loadLogic(String[] details) throws DateNotFoundException {
        if (details.length <= 1) {
            return;
        }
        if (details[0].trim().contentEquals("T")) {
            ToDo todo = ToDo.of(details[1], "FILE");
            tasks.add(todo);
            System.out.println(Ui.loadTaskToast(todo, tasks.size()));
        } else if (details[0].trim().contentEquals("D")) {
            Deadline deadline = Deadline.of(details[1], "FILE");
            tasks.add(deadline);
            System.out.println(Ui.loadTaskToast(deadline, tasks.size()));
        } else if (details[0].trim().contentEquals("E")) {
            Event event = Event.of(details[1], "FILE");
            tasks.add(event);
            System.out.println(Ui.loadTaskToast(event, tasks.size()));
        }
    }

    public Task get(int i) {
        assert i >= 0 : "i is a positive index";
        return tasks.get(i);
    }

    public int size() {

        return tasks.size();
    }

    public String list() {

        return Ui.printList(tasks);
    }

    public Task markTask(Integer n) {
        assert n > 0 : "n starts indexing from 1";
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.markAsDone();
        return taskMark;
    }

    public Task unmarkTask(Integer n) {
        assert n > 0 : "n starts indexing from 1";
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.markAsNotDone();
        return taskMark;
    }

    public Task deleteTask(Integer n) {
        assert n > 0 : "n starts indexing from 1";
        Task deletedTask = tasks.remove(n.intValue() - 1);
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

    /**
     * Sorts the task list by a sorting criteria.
     * @param criteria Criteria that sort will be done according to.
     * @return List sorted according to criteria.
     */
    public ArrayList<Task> sortTasks(CommandParser.Sorting criteria) {

        switch (criteria) {
        case DATES:
            DateComparator dateComparator = new DateComparator();
            this.tasks.sort(dateComparator);
            return this.tasks;
        case NAME:
            NameComparator nameComparator = new NameComparator();
            this.tasks.sort(nameComparator);
            return this.tasks;
        case TASK:
            TaskTypeComparator taskComparator = new TaskTypeComparator();
            this.tasks.sort(taskComparator);
            return this.tasks;
        case DONE:
            CompletionComparator completionComparator = new CompletionComparator();
            this.tasks.sort(completionComparator);
            return this.tasks;
        default:
            return this.tasks;
        }
    }
}
