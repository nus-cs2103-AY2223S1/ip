package duke;

import duke.command.*;
import duke.exception.*;
import duke.command.Command;
import duke.task.TasksController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser classes parse user's input of commands
 */
public class Parser {
    private final ArrayList<String> legalCommands = new ArrayList<>();
    private final ArrayList<String> legalTasks = new ArrayList<>();

    public Parser() {
        legalCommands.add("add");
        legalCommands.add("delete");
        legalCommands.add("list");
        legalCommands.add("mark");
        legalCommands.add("unmark");
        legalCommands.add("find");
        legalCommands.add("exit");

        legalTasks.add("ToDo");
        legalTasks.add("Event");
        legalTasks.add("Deadline");
    }

    private String parseCommand(String inputText) throws InvalidCommandException {
        String[] splitCommand = inputText.split("/p");
        String commandText = splitCommand[0].strip();
        if (!legalCommands.contains(commandText)) {
            throw new InvalidCommandException("ERROR");
        }
        return commandText;
    }

    private String parseTask(String inputText) throws InvalidTaskException {
        String[] splitCommand = inputText.split("/p");
        String taskText = splitCommand[1].strip();
        if (!legalTasks.contains(taskText)) {
            throw new InvalidTaskException("ERROR");
        }
        return taskText;
    }

    private String parseContent(String inputText) throws EmptyContentException {
        String[] splitCommand = inputText.split("/p");
        String taskContent = splitCommand[2].strip();
        if (taskContent.isBlank()) {
            throw new EmptyContentException("ERROR");
        }
        return taskContent;
    }

    private String parseTime(String inputText) throws InvalidTimeException {
        String[] splitCommand = inputText.split("/p");
        String time = splitCommand[3].strip();
        if (!checkTimeFormat(time)) {
            throw new InvalidTimeException("ERROR");
        }
        return time;
    }

    private int parseIndex(String inputText) {
        String[] splitCommand = inputText.split("/p");
        return Integer.parseInt(splitCommand[1].strip());
    }

    private ArrayList<String> parseKeyword(String inputText) throws TooManyKeywordsException {
        String[] splitCommand = inputText.split("/p");
        String[] keywords = splitCommand[1].split(" ");
        ArrayList<String> trimmedKeywords = new ArrayList<>();
        for (String keyword: keywords) {
            if (!keyword.isBlank()) {
                trimmedKeywords.add(keyword.trim());
            }
        }
        if (trimmedKeywords.size() > 3) {
            throw new TooManyKeywordsException("ERROR");
        }
        return trimmedKeywords;
    }

    /**
     * Checks the format of user's input of time
     * @param s time string
     * @return boolean value
     * @throws InvalidTimeException if the format is invalid
     */
    public boolean checkTimeFormat(String s) throws InvalidTimeException {
        Pattern pattern1 = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        Pattern pattern2 = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher matcher1 = pattern1.matcher(s);
        Matcher matcher2 = pattern2.matcher(s);
        if (!(matcher1.find() && matcher2.find())) {
            throw new InvalidTimeException("ERROR");
        }
        return true;
    }

    /**
     * Parses user's input of commands and returns a Duke command
     * @param inputText user's input
     * @param controller tasks controller
     * @param storage duke storage
     * @return corresponding Duke command
     */
    public String parse(String inputText, TasksController controller, Storage storage) {
        String response = "";
        Command command = null;
        try {
            String commandText = parseCommand(inputText);
            switch (commandText) {
                case "add":
                    String task = parseTask(inputText);
                    String content = parseContent(inputText);
                    switch (task) {
                        case "ToDo":
                            command = new CreateToDoCommand();
                            response = command.execute(controller, content, "", -1, storage, "");
                            break;
                        case "Event": {
                            String taskTime = parseTime(inputText);
                            command = new CreateEventCommand();
                            response = command.execute(controller, content, taskTime, -1, storage, "");
                            break;
                        }
                        case "Deadline": {
                            String taskTime = parseTime(inputText);
                            command = new CreateDeadlineCommand();
                            response = command.execute(controller, content, taskTime, -1, storage, "");
                            break;
                        }
                    }
                    break;
                case "list":
                    command = new ShowTasksCommand();
                    response = command.execute(controller, "", "", -1, storage, "");
                    break;
                case "delete":
                    int deleteIndex = parseIndex(inputText) - 1;
                    command = new DeleteTaskCommand();
                    response = command.execute(controller, "", "", deleteIndex, storage, "");
                    break;
                case "mark":
                    int markIndex = parseIndex(inputText) - 1;
                    command = new MarkTaskCommand();
                    response = command.execute(controller, "", "", markIndex, storage, "");
                    break;
                case "unmark":
                    int unmarkIndex = parseIndex(inputText) - 1;
                    command = new UnmarkTaskCommand();
                    response = command.execute(controller, "", "", unmarkIndex, storage, "");
                    break;
                case "find":
                    ArrayList<String> keywords = parseKeyword(inputText);
                    command = new FindCommand();
                    if (keywords.size() == 1) {
                        response = command.execute(controller, "", "", -1, storage, keywords.get(0));
                    } else if (keywords.size() == 2) {
                        response = command.execute(controller, "", "", -1, storage, keywords.get(0),
                                keywords.get(1));
                    } else {
                        response = command.execute(controller, "", "", -1, storage, keywords.get(0),
                                keywords.get(1), keywords.get(2));
                    }
                    break;
                case "exit":
                    command = new ExitCommand();
                    response = command.execute(controller, "", "", -1, storage, "");
                    break;
            }
        } catch (InvalidCommandException ice) {
            response = "Your command is invalid. Please try again...";
        } catch (InvalidTaskException ite) {
            response = "Your task is invalid. Please try again...";
        } catch (EmptyContentException ece) {
            response = "Your task content cannot be empty. Please try again...";
        } catch (InvalidTimeException ite) {
            response = "Your time format is invalid. Please try again...";
        } catch (TooManyKeywordsException mke) {
            response = "Too many keywords. Please try again...";
        }
        return response;
    }
}
