package util;

import alanExceptions.AlanException;
import alanExceptions.InvalidValueException;
import tasks.*;

public class Executor {
    private final Ui ui;
    private final FileFormatter fileFormatter;
    private final Parser parser;
    private Storage alanIO;

    public Executor() {
        this.ui = new Ui();
        this.fileFormatter = new FileFormatter();
        this.parser = new Parser();
        try {
            this.alanIO = new Storage();
        } catch (AlanException e) {
            excException(e.getMessage());
        }
    }

    public void excList(TaskList taskList) {
        System.out.println(ui.list(taskList.getTaskList()));
    }

    public void excEvent(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.event, userInput);
        Task currentTask = new Event(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    public void excDeadline(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.deadline, userInput);
        Task currentTask = new Deadline(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    public void excTodo(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.todo, userInput);
        Task currentTask = new Todo(parsedData);
        taskList.add(currentTask);
        // Append new task to save File
        alanIO.append(fileFormatter.formatTask(currentTask));
        System.out.println(ui.addTask(currentTask, taskList.size()));
    }

    public void excMark(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.mark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markDone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.markDone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excUnmark(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.unmark, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            currentTask.markUndone();
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.markUndone(currentTask));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excDelete(TaskList taskList, String userInput) throws AlanException {
        ParsedData parsedData = parser.parse(InputType.delete, userInput);
        try {
            Task currentTask = taskList.get(parsedData.getListIndex());
            taskList.remove(parsedData.getListIndex());
            // Append new task to save File
            alanIO.write(fileFormatter.formatTaskList(taskList.getTaskList()));
            System.out.println(ui.delete(currentTask, taskList.size()));
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidValueException();
        }
    }

    public void excException(String errorMsg) {
        System.out.println(ui.addSeparator(errorMsg));
    }
}
