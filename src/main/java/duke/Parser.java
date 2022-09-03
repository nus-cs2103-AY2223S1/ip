package duke;
import exception.EmptyDescriptionException;
import exception.InvalidCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *  A class that handles parsing and processing of user inputs.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Parser {
    private TaskList currList;

    public Parser(TaskList currList) {
        this.currList = currList;
    }

    /**
     * A method for Duke to process the input given by the user.
     * @param input The input string picked up by the scanner object.
     * @throws EmptyDescriptionException If the input is a recognized command but no appropriate description after that.
     * @throws InvalidCommandException If the input is an unrecognized command.
     */
    public void processInput(String input) throws EmptyDescriptionException, InvalidCommandException {
        ArrayList<String> acceptedKeywords = new ArrayList<>();
        acceptedKeywords.add("list");
        acceptedKeywords.add("bye");
        acceptedKeywords.add("deadline");
        acceptedKeywords.add("event");
        acceptedKeywords.add("todo");
        acceptedKeywords.add("delete");
        acceptedKeywords.add("find");
        acceptedKeywords.add("mark");
        acceptedKeywords.add("unmark");
        String[] parts = input.split(" ", 2);
        String keyword = parts[0];
        if (input.equals("bye") || input.equals("list")) return;
        if (!acceptedKeywords.contains(keyword)) {
            throw new InvalidCommandException();
        }
        else if (parts.length == 1 || parts[1].equals("")) {
            throw new EmptyDescriptionException();
        }
    }


    /**
     * A method to parse the user input and executes the appropriate action accordingly.
     * @param input The user keyboard input after prompt from Duke.
     * @return The resulting duke response as a string.
     */
    public String parseUserInput(String input) {
        if (input.equals("bye")) {
            return Ui.endingMessage();
        }
        try {
            String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            processInput(input);
            String result = "";
            switch (keyword) {
            case "list":
                result += "Here are your tasks:\n";
                for (int i = 0; i < currList.getLength(); i++) {
                    result += i + 1 + ". " + currList.getTaskAt(i).toString() + "\n";
                }
                return result;
            case "mark": //command is mark 2
                Task currTask = currList.getTaskAt(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                currTask.setDone();
                result += "Let's go! I've marked this task as done:\n";
                result += currTask;
                return result;
            case "unmark":
                Task unmarkTask = currList.getTaskAt(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                unmarkTask.setUndone();
                result += "Oh man! I've marked this task as undone:\n";
                result += unmarkTask;
                return result;
            case "deadline": //can abstract this whole case to be generalized
                String[] temp = input.split(" /by ", 2);
                String by = temp[1];
                String deadlineDesc = temp[0].split("deadline ")[1];
                Deadline deadlineTask = new Deadline(deadlineDesc, LocalDate.parse(by));
                currList.addTask(deadlineTask);
                result += Ui.addTaskMessage(deadlineTask);
                result += Ui.getTaskNumberMessage(currList);
                return result;
            case "event": //can abstract this whole case to be generalized
                String[] temp1 = input.split(" /at ", 2);
                String at = temp1[1];
                String eventDesc = temp1[0].split("event ", 2)[1];
                Event eventTask = new Event(eventDesc, LocalDate.parse(at));
                currList.addTask(eventTask);
                result += Ui.addTaskMessage(eventTask);
                result += Ui.getTaskNumberMessage(currList);
                return result;
            case "todo": //can abstract this whole case to be generalized
                String todoDesc = input.split("todo ")[1];
                Todo todoTask = new Todo(todoDesc);
                currList.addTask(todoTask);
                result += Ui.addTaskMessage(todoTask);
                result += Ui.getTaskNumberMessage(currList);
                return result;
            case "delete":
                Task toBeDeleted = currList.getTaskAt(Integer.parseInt(parts[1]) - 1);
                result += "Alrighty, this task's gone:\n";
                result += toBeDeleted;
                currList.removeTask(toBeDeleted);
                return result;
            case "find":
                String searchWord = parts[1];
                result += "Here are the matching tasks:\n";
                TaskList matched = currList.getMatchingItems(searchWord);
                for (int i = 0; i < matched.getLength(); i++) {
                    result += i + 1 + ". " + matched.getTaskAt(i).toString() + "\n";
                }
                return result;
            default:
                assert false; //should not reach here
                return result;
            }

        } catch (EmptyDescriptionException e) {
            return "Description cannot be empty, try again!";
        } catch (InvalidCommandException | IndexOutOfBoundsException e) {
            return "Invalid input, try again!";
        } catch (DateTimeParseException e) {
            return "Cannot parse this date!";
        }
    }
}
