import AlanExceptions.AlanException;
import AlanExceptions.InvalidValueException;

import java.util.List;

public class Executor {
    private final Formatter formatter;
    private final Parser parser;

    public Executor() {
        this.formatter = new Formatter();
        this.parser = new Parser();
    }

    public <T extends Task> void excList(List<T> taskList) {
        System.out.println(formatter.list(taskList));
    }

    public void excEvent(List<? super Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.event, userInput);
        Task currentTask = new Event(parsedData);
        taskList.add(currentTask);
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excDeadline(List<? super Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.deadline, userInput);
        Task currentTask = new Deadline(parsedData);
        taskList.add(currentTask);
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excTodo(List<? super Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.todo, userInput);
        Task currentTask = new Todo(parsedData);
        taskList.add(currentTask);
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excMark(List<? extends Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.mark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markDone();
            System.out.println(formatter.markDone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excUnmark(List<? extends Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.unmark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markUndone();
            System.out.println(formatter.markUndone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excDelete(List<? extends Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.delete, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            taskList.remove(parsedData.getListIndex());
            System.out.println(formatter.delete(currentTask, taskList.size()));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excException(String errorMsg) {
        System.out.println(formatter.addSeparator(errorMsg));
    }
}
