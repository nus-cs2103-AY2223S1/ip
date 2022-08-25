import AlanExceptions.AlanException;
import AlanExceptions.InvalidValueException;

import java.util.List;

public class Executor {
    private final Formatter formatter;
    private final FileFormatter fileFormatter;
    private final Parser parser;
    private AlanIO alanIO;

    public Executor() {
        this.formatter = new Formatter();
        this.fileFormatter = new FileFormatter();
        this.parser = new Parser();
        try {
            this.alanIO = new AlanIO();
        } catch (AlanException e) {
            excException(e.getMessage());
        }
    }

    public void excList(List<Task> taskList) {
        System.out.println(formatter.list(taskList));
    }

    public void excEvent(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.event, userInput);
        Task currentTask = new Event(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excDeadline(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.deadline, userInput);
        Task currentTask = new Deadline(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excTodo(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.todo, userInput);
        Task currentTask = new Todo(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(formatter.addTask(currentTask, taskList.size()));
    }

    public void excMark(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.mark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markDone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList));
            System.out.println(formatter.markDone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excUnmark(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.unmark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markUndone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList));
            System.out.println(formatter.markUndone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excDelete(List<Task> taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.delete, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            taskList.remove(parsedData.getListIndex());
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList));
            System.out.println(formatter.delete(currentTask, taskList.size()));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excException(String errorMsg) {
        System.out.println(formatter.addSeparator(errorMsg));
    }
}
