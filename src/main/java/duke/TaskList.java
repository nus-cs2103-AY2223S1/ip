package duke;

import duke.exceptions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }


    public TaskList(List<? extends Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }
    
    public TaskList(ArrayList<? extends Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public TaskList findTask(String keyword) throws NoMatchingKeywordException {
        assert !keyword.isBlank(): "keyword should not be blank";
        List<Task> filtered = this.taskList.stream()
                .filter(task -> task.isMatchKeyword(keyword))
                .collect(Collectors.toList());
        TaskList filteredTaskList = new TaskList(filtered);
        
        if (filteredTaskList.size() == 0) {
            throw new NoMatchingKeywordException(keyword);
        }
        return new TaskList(filtered);
    }

    public TaskList sortDeadlineChronologically(Order order) {
        Comparator<Deadline> deadlineComparator = (d1, d2) ->
                d1.compareChronologically(d2);

        Stream<Deadline> deadlineStream = taskList
                .stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task);
        if (order == Order.decreasing) {
            List<Deadline> result = deadlineStream
                    .sorted(deadlineComparator.reversed())
                    .collect(Collectors.toList());
            return new TaskList(result);
        }
        List<Deadline> result = deadlineStream
                .sorted(deadlineComparator)
                .collect(Collectors.toList());
        return new TaskList(result);
    }

    public TaskList sortDeadlineLexicographically(Order order) {
        Comparator<Deadline> deadlineComparator = (d1, d2) ->
                d1.compareLexicographically(d2);
        Stream<Deadline> deadlineStream = taskList.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task);
        if (order == Order.decreasing) {
            List<Deadline> result = deadlineStream
                    .sorted(deadlineComparator.reversed())
                    .collect(Collectors.toList());
            return new TaskList(result);
        }
        List<Task> result = deadlineStream
                .sorted(deadlineComparator)
                .collect(Collectors.toList());
        return new TaskList(result);
    }
    public TaskList sortEventChronologically(Order order) {
        Comparator<Event> eventComparator = (e1, e2) ->
                e1.compareChronologically(e2);

        Stream<Event> eventStream = taskList
                .stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task);
        if (order == Order.decreasing) {
            List<Event> result = eventStream
                    .sorted(eventComparator.reversed())
                    .collect(Collectors.toList());
            return new TaskList(result);
        }
        List<Event> result = eventStream
                .sorted(eventComparator)
                .collect(Collectors.toList());
        return new TaskList(result);
    }

    public TaskList sortEventLexicographically(Order order) {
        Comparator<Event> eventComparator = (e1, e2) ->
                e1.compareLexicographically(e2);
        Stream<Event> eventStream = taskList.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task);
        if (order == Order.decreasing) {
            List<Event> result = eventStream
                    .sorted(eventComparator.reversed())
                    .collect(Collectors.toList());
            return new TaskList(result);
        }
        List<Task> result = eventStream
                .sorted(eventComparator)
                .collect(Collectors.toList());
        return new TaskList(result);
    }

    public TaskList sortLexicographically(TypeOfTask typeOfTask, Order order) throws CannotSortException {
        if (typeOfTask == TypeOfTask.deadline) {
            return sortDeadlineLexicographically(order);
        }
        if (typeOfTask == TypeOfTask.event) {
            return sortEventLexicographically(order);
        }
        throw new CannotSortException("CANNOT SORT TODO LEXICOGRAPHICALLY");
    }

    public TaskList sortChronologically(TypeOfTask typeOfTask, Order order) throws CannotSortException {
        if (typeOfTask == TypeOfTask.deadline) {
            return sortDeadlineChronologically(order);
        }
        if (typeOfTask == TypeOfTask.event) {
            return sortEventChronologically(order);
        }
        throw new CannotSortException("CANNOT SORT TODO CHRONOLOGICALLY");
    }

    public Task markStatus(int task) throws DukeException {
        assert task >= 0: "task index should be more than or equal to 0";
        try {
            Task curr = taskList.get(task - 1);
            if (curr.isDone()) {
                throw new TaskMarkedException(task);
            }
            curr.toggleStatus();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new CannotFindTaskException();
        }
    }

    public Task unmarkStatus(int task) throws DukeException {
        assert task >= 0: "task index should be more than or equal to 0";
        try {
            Task curr = taskList.get(task - 1);
            if (!curr.isDone()) {
                throw new TaskUnmarkedException(task);
            }
            curr.toggleStatus();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new CannotFindTaskException();
        }
    }

    public Task deleteTask(int task) throws DukeException {
        assert task >= 0: "task index should be more than or equal to 0";
        try {
            Task curr = taskList.get(task - 1);
            taskList.remove(task - 1);
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new CannotFindTaskException();
        }
    }

    @Override
    public String toString() {
        String result = "";
        Object[] taskArr = this.taskList.toArray();
        for (int i = 0; i < this.taskList.size(); i++) {
            result += (i + 1)
                    + ". "
                    + taskArr[i].toString()
                    + "\n";
        }
        return result;
    }

    public int size() {
        return taskList.size();
    }
    public String numOfTask() {
        if (taskList.size() > 1) {
            return "YOU HAVE "
                    + taskList.size()
                    + " TASKS!";
        } else {
            return "YOU HAVE "
                    + taskList.size()
                    + " TASK!";
        }
    }

    public String generateSave() {
        String result = taskList
                .stream()
                .map(task -> task.toSaveVersion())
                .reduce("", (res, task) -> res + task);
        return result;
    }
}
