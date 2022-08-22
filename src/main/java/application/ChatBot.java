package application;

import taskfilemanager.TaskFileManager;
import datastructure.Pair;

import exception.InvalidCaseException;
import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Task;
import task.TaskList;
import task.ToDo;
import task.Event;

public class ChatBot {
    private final TaskFileManager fileManager = TaskFileManager.of("duke.txt");
    private final TaskList taskList = fileManager.getTaskList();

    private void displayMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.print(message);
        System.out.println("\t____________________________________________________________");
    }

    private void handleGreet() {
        String logo = "\t" + " ,-----.,--.               ,--.    ,--.                  " + "\n" + "\t" + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  " + "\n" + "\t" + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  " + "\n" + "\t" + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) " + "\n" + "\t" + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  " + "\n";
        String message = "\n\t" + "Hello! My name is Chattus" + "." + "\n\t" + "What can I do for you? :)" + "\n";
        this.displayMessage(logo + message);
    }

    private void handleBye() {
        this.handleSave();
        this.displayMessage("\t" + "Bye! Till we next meet!" + "\n");
    }

    private void handleAddTask(Case cs, ArrayList<String> parsedLine) {
        Task task;

        switch (cs) {
        case TODO:
            task = new ToDo(parsedLine.get(0));
            break;
        case DEADLINE:
            task = new Deadline(parsedLine.get(0), parsedLine.get(1));
            break;
        case EVENT:
            task = new Event(parsedLine.get(0), parsedLine.get(1));
            break;
        default:
            throw new InvalidCaseException();
        }

        taskList.add(task);
        this.displayMessage("\t" + "Got it. I've added this task:" + "\n\t\t" + task + "\n" + "\t" + "Now you have " + taskList.size() + " tasks in the list." + "\n");
    }

    private void handleDeleteTask(ArrayList<String> parsedLine) throws InvalidInputException {
        int entry = Integer.parseInt(parsedLine.get(0));
        if (taskList.isInRange(entry)) {
            Task removed = taskList.remove(entry);
            this.displayMessage("\t" + "Noted. I've removed this task:" + "\n\t\t" + "\t" + removed + "\n" + "\t" + "Now you have " + taskList.size() + " tasks left in the list." + "\n");
        } else {
            throw new InvalidInputException();
        }
    }

    private void handleList() {
        this.displayMessage("\t" + "Here are the tasks in your list:" + "\n" + taskList);
    }

    private void handleMark(ArrayList<String> parsedLine) throws InvalidInputException {
        int entry = Integer.parseInt(parsedLine.get(0));
        if (taskList.isInRange(entry)) {
            Task task = taskList.get(entry);
            taskList.markTask(entry);
            this.displayMessage("\t" + "Great! I've marked this task." + "\n\t\t" + task + "\n");
        } else {
            throw new InvalidInputException();
        }
    }

    private void handleUnmark(ArrayList<String> parsedLine) throws InvalidInputException {
        int entry = Integer.parseInt(parsedLine.get(0));
        if (taskList.isInRange(entry)) {
            Task task = taskList.get(entry);
            taskList.unmarkTask(entry);
            this.displayMessage("\t" + "Ok, I've unmarked this task." + "\n\t\t" + task + "\n");
        } else {
            throw new InvalidInputException();
        }
    }

    private void handleUnexpected() {
        this.displayMessage("\t" + "Seems like you've entered something incorrectly, try again!" + "\n");
    }

    private void handleSave() {
        fileManager.save(taskList);
    }

    public void start() {
        this.handleGreet();

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                Pair<Case, ArrayList<String>> parsed = CommandParser.parse(input.nextLine());
                Case cs = parsed.getLeft();
                ArrayList<String> parsedLine = parsed.getRight();

                switch (cs) {
                case BYE:
                    this.handleBye();
                    input.close();
                    return;
                case LIST:
                    this.handleList();
                    break;
                case MARK:
                    this.handleMark(parsedLine);
                    break;
                case UNMARK:
                    this.handleUnmark(parsedLine);
                    break;
                case DELETE:
                    this.handleDeleteTask(parsedLine);
                    break;
                case TODO: case DEADLINE: case EVENT:
                    this.handleAddTask(cs, parsedLine);
                    break;
                }
            } catch (InvalidInputException e) {
                this.handleUnexpected();
            }
        }
    }
}
