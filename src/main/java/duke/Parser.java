package duke;
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
     * A method to parse the user input and execute the appropriate action accordingly.
     * @param input The user keyboard input after prompt from Duke.
     */
    public void parseUserInput(String input) {
        try {
            String[] parts = input.split(" ", 2);
            String keyword = parts[0];
            processInput(input);
            switch (keyword) {
            case "list":
                System.out.println("Here are your tasks:");
                for (int i = 0; i < currList.getLength(); i++) {
                    System.out.println(i + 1 + ". " + currList.getTaskAt(i).toString());
                }
                break;
            case "mark": //command is mark 2
                Task currTask = currList.getTaskAt(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                currTask.setDone();
                System.out.println("Let's go! I've marked this task as done:");
                System.out.println(currTask);
                break;
            case "unmark":
                Task unmarkTask = currList.getTaskAt(Integer.parseInt(parts[1]) - 1); //the arraylist is 0 indexed, so task 1 is actually 0 index
                unmarkTask.setUndone();
                System.out.println("Oh man! I've marked this task as undone:");
                System.out.println(unmarkTask);
                break;
            case "deadline": //can abstract this whole case to be generalized
                String[] temp = input.split(" /by ", 2);
                String by = temp[1];
                String deadlineDesc = temp[0].split("deadline ")[1];
                Deadline deadlineTask = new Deadline(deadlineDesc, LocalDate.parse(by));
                currList.addTask(deadlineTask);
                deadlineTask.addTaskMessage();
                Ui.taskNumberMessage(currList);
                break;
            case "event": //can abstract this whole case to be generalized
                String[] temp1 = input.split(" /at ", 2);
                String at = temp1[1];
                String eventDesc = temp1[0].split("event ", 2)[1];
                Event eventTask = new Event(eventDesc, LocalDate.parse(at));
                currList.addTask(eventTask);
                eventTask.addTaskMessage();
                Ui.taskNumberMessage(currList);
                break;
            case "todo": //can abstract this whole case to be generalized
                String todoDesc = input.split("todo ")[1];
                Todo todoTask = new Todo(todoDesc);
                currList.addTask(todoTask);
                todoTask.addTaskMessage();
                Ui.taskNumberMessage(currList);
                break;
            case "delete":
                Task toBeDeleted = currList.getTaskAt(Integer.parseInt(parts[1]) - 1);
                System.out.println("Alrighty, this task's gone: ");
                System.out.println(toBeDeleted);
                currList.removeTask(toBeDeleted);
                break;
            case "find":
                String searchWord = parts[1];
                System.out.println("Here are the matching tasks:");
                TaskList matched = currList.matchingItems(searchWord);
                for (int i = 0; i < matched.getLength(); i++) {
                    System.out.println(i + 1 + ". " + matched.getTaskAt(i).toString());
                }
                break;
            default:
                System.out.println(input);
            }

        } catch (EmptyDescriptionException e) {
            System.out.println("Description cannot be empty, try again!");
        } catch (InvalidCommandException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, try again!");
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse this date!");
        }
    }
}
