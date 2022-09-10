package main;

import java.util.Scanner;

import exception.InvalidCommandException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private TaskList tasks;
    private Parser parser;
    private Storage storage;
    private Ui ui;

    private boolean isEnd = false;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser(this);
        this.storage = new Storage(this.tasks, this.ui, this.storage);
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        ui.greeting();
            while (!isEnd) {
                try {
                    String[] parsedUserInput = parseCommand(userInput.nextLine());
                    String command = parsedUserInput[0];
                    String arg1 = parsedUserInput[1];
                    String arg2 = parsedUserInput[2];
                    arg2 = arg2.replaceAll(" ","");
                    switch(command) {
                    case COMMAND_LOAD:
                        loadLog(logFileAddress);
                        chat("Tasks have been loaded\n`");
                        break;
                    case COMMAND_LIST:
                        chat("Here are the tasks in your list: \n" + list());
                        break;
                    case COMMAND_BYE:
                        chat(cleanUp());
                        chat("Bye! Hope to see you again!\n");
                        isEnd = true;
                        userInput.close();
                        break;
                    case COMMAND_TODO:
                        if (arg1.equals("")) {
                            error("ToDo task requires a description");
                            break;
                        }
                        ToDo toDoToAdd = new ToDo(arg1);
                        tasks.add(toDoToAdd);
                        chat("Got it, I've added this task:\n " + toDoToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_DEADLINE:            
                        Deadline deadlineToAdd = new Deadline(arg1, arg2);
                        tasks.add(deadlineToAdd);
                        chat("Got it, I've added this task:\n " + deadlineToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_EVENT:          
                        Event eventToAdd = new Event(arg1, arg2);
                        tasks.add(eventToAdd);
                        chat("Got it, I've added this task:\n " + eventToAdd + "\n" + outputNumOfTasks());
                        break;
                    case COMMAND_MARK:
                        mark(Integer.parseInt(arg1));
                        break;
                    case COMMAND_UNMARK:
                        unmark(Integer.parseInt(arg1));
                        break;
                    case COMMAND_DELETE:
                        delete(Integer.parseInt(arg1));
                        break;
                    default:
                        error("No or invalid command given, are you just looking to chat? :D");
                        break;
                    }
                } catch (MissingArgumentException e) {
                    chat(e.getLocalizedMessage() + "\n");
                } catch (InvalidCommandException e) {
                    chat(e.getLocalizedMessage() + "\n");
                } catch (InvalidDateException e) {
                    chat(e.getLocalizedMessage() + "\n");
                } catch (IOException e) {
                    chat (e.getLocalizedMessage() + "\n");
                }
            }
        }

    public static String[] parseCommand(String userCommand) throws InvalidCommandException {
        String[] parsedCommand = {"","",""};
        String splitUserStatement[] = userCommand.split(" ", 2);
        if (!isValidCommand(splitUserStatement[0])) {
            throw new InvalidCommandException("Thats not an available command.");
        }
        String command = splitUserStatement[0];
        parsedCommand[0] = command;
        String userArgs = "";
        if (splitUserStatement.length > 1) {
            userArgs = splitUserStatement[1];
        }
        String[] splitUserArgs = {"",""};
        if (command.equals(COMMAND_DEADLINE)) {
            splitUserArgs = userArgs.split("/by", 2);
        }
        else if (command.equals(COMMAND_EVENT)) {
            splitUserArgs = userArgs.split("/at", 2);
        } else {
            splitUserArgs = userArgs.split("/", 2);
        }
        parsedCommand[1] = splitUserArgs[0];
        if (splitUserArgs.length > 1) {
            parsedCommand[2] = splitUserArgs[1];
        }
        return parsedCommand;

    }

    public static void end() {
        chat("Bye! Hope to see you again!");
        isEnd = true;
    }
}
