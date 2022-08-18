import DataStructures.Pair;
import Exceptions.*;
import Task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    private final TaskList taskList = new TaskList();

    private void displayMessage(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.print(message);
        System.out.println("\t____________________________________________________________");
    }

    private void handleGreet() {
        String logo =
                  "\t" + " ,-----.,--.               ,--.    ,--.                  " + "\n"
                + "\t" + "'  .--./|  ,---.  ,--,--.,-'  '-.,-'  '-.,--.,--. ,---.  " + "\n"
                + "\t" + "|  |    |  .-.  |' ,-.  |'-.  .-''-.  .-'|  ||  |(  .-'  " + "\n"
                + "\t" + "'  '--'\\|  | |  |\\ '-'  |  |  |    |  |  '  ''  '.-'  `) " + "\n"
                + "\t" + " `-----'`--' `--' `--`--'  `--'    `--'   `----' `----'  " + "\n";
        String message = "\n\t" + "Hello! My name is Chattus" + "."
                       + "\n\t" + "What can I do for you? :)" + "\n";
        this.displayMessage(logo + message);
    }

    private void handleBye() {
        String message = "\t" + "Bye! Till we next meet!" + "\n";
        this.displayMessage(message);
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

        this.taskList.add(task);
        this.displayMessage("\t" + "Got it. I've added this task:" + "\n\t\t" + task + "\n"
                          + "\t" + "Now you have " + this.taskList.size() + " tasks in the list." + "\n");
    }

    private void handleList() {
        this.displayMessage("\t" + "Here are the tasks in your list:" + "\n" + this.taskList);
    }

    private void handleMark(ArrayList<String> parsedLine) throws InvalidInputException {
        int entry = Integer.parseInt(parsedLine.get(0));
        if (this.taskList.inRange(entry)) {
            Task task = this.taskList.get(entry);
            this.taskList.markTask(entry);
            this.displayMessage("\t" + "Great! I've marked this task." + "\n\t\t" + task + "\n");
        } else {
            throw new InvalidInputException();
        }
    }

    private void handleUnmark(ArrayList<String> parsedLine) throws InvalidInputException {
        int entry = Integer.parseInt(parsedLine.get(0));
        if (this.taskList.inRange(entry)) {
            Task task = this.taskList.get(entry);
            this.taskList.unmarkTask(entry);
            this.displayMessage("\t" + "Ok, I've unmarked this task." + "\n\t\t" + task + "\n");
        } else {
            throw new InvalidInputException();
        }
    }

    private void handleUnexpected() {
        this.displayMessage("\t" + "Seems like you've entered something incorrectly, try again!" + "\n");
    }

    public void start() {
        this.handleGreet();

        Scanner input = new Scanner(System.in);

        while(true) {
            try {
                Pair<Case, ArrayList<String>> parsed = LineParser.parse(input.nextLine());
                Case cs = parsed.left();
                ArrayList<String> parsedLine = parsed.right();

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
