package anya;

import java.util.Scanner;

import anya.task.Task;
import anya.task.TaskList;

public class Ui {

    public String getGreetMessage() {
        String message = "Hello! Anya is happy to meet you.\nHow can Anya help?";
        return message;
    }

    public String getExitMessage() {
        String message = "Anya is sad to see you leave. Please be back soon.";
        return message;
    }

    public String getAddTaskMessage(Task task, int taskListLength) {
        String taskAddedMessage = "Anya added: " + task;
        String numTaskMessage = "Anya sees that you have " + taskListLength + " task(s) in your list.";
        return taskAddedMessage + "\n" + numTaskMessage;
    }

    public String getDeleteTaskMessage(Task task) {
        String message = "Anya has removed this task : \n" + task.toString();
        return message;
    }

    public String getListMessage(TaskList taskList) {
        StringBuilder message = new StringBuilder("Anya is getting you your list...\n");
        for (int i = 0; i < taskList.getLength(); i++) {
            int num = i + 1;
            message.append(num + ". " + taskList.getTaskFromIndex(num).toString() + "\n");
        }
        return message.toString();
    }

    public String getMarkTaskMessage(Task task) {
        String message = "Anya has marked this task as done: \n  " + task.toString();
        return message;
    }

    public String getUnmarkTaskMessage(Task task) {
        String message = "Anya has marked this task as uncompleted: \n  " + task.toString();
        return message;
    }

    public String getSavingFileMessage() {
        String message = "Anya is saving your data...";
        return message;
    }

    public String getSaveFileSuccessMessage() {
        String message = "Anya has successfully saved your data!";
        return message;
    }

    public String getLoadingFileMessage() {
        String message = "Anya is loading your saved file...";
        return message;
    }

    public String getLoadFileFailureMessage() {
        String message = "Anya is unable to find your previous saved file. \nAnya has created one for you!";
        return message;
    }

    public String getLoadFileSuccessMessage() {
        String message = "Anya has found and loaded your saved file!";
        return message;
    }

    public String getErrorMessage(String errorMessage) {
        String message = "Anya spotted an error: \n" + errorMessage;
        return message;
    }

    public String deadlineFormatExample() {
        String formatMessage = "Format: <name> /by <dd/MM/yyyy> <HHmm>.";
        String exampleMessage = "Example: return book /by 01/01/2022 2030";
        return formatMessage + "\n" + exampleMessage;
    }

    public String getFilteredTaskMessage(TaskList filteredTasks, String keyword) {
        if (filteredTasks.getLength() == 0) {
            return "Anya couldn't find any matching tasks with keyword: " + keyword;
        } else {
            StringBuilder message = new StringBuilder("Anya has found these matching tasks in your list:\n");
            for (int i = 0; i < filteredTasks.getLength(); i++) {
                int index = i + 1;
                message.append(index + ". " + filteredTasks.getTaskFromIndex(index).toString() + "\n");
            }
            return message.toString();
        }
    }
}
