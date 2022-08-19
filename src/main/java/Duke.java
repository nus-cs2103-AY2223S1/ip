import exceptions.*;
import task.*;
import utils.Input;
import utils.Prompt;
import utils.Utils;

import java.util.Scanner;


/**
 * The {@code Duke} class enables users to store and indicated various
 * types of tasking, check and uncheck them, delete them and view a list
 * of all present tasks. It has a command line interface and does not
 * store data from each run.
 */
public class Duke {

    /**
     * TaskList stores all the current task created by the user.
     */
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Prompt.startPrompt();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String inputString = scanner.nextLine();
                if (inputString.isEmpty()) {
                    continue;
                }
                Input input = Input.formatInput(inputString.trim());
                switch (input.getCommand()) {
                    case BYE:
                        Prompt.endPrompt();
                        return;
                    case LIST:
                        listTasks();
                        break;
                    case CHECK:
                        checkTask(input.getMainData());
                        break;
                    case UNCHECK:
                        uncheckTask(input.getMainData());
                        break;
                    case DELETE:
                        deleteTask(input.getMainData());
                        break;
                    case TODO:
                        addTask(new TaskTodo(input.getMainData()));
                        break;
                    case DEADLINE:
                        addTask(new TaskDeadline(input.getMainData(), input.getSecondaryData()));
                        break;
                    case EVENT:
                        addTask(new TaskEvent(input.getMainData(), input.getSecondaryData()));
                        break;
                    default:
                        break;
                }
            } catch (InvalidCommandException err) {
                System.out.printf("%s is not a valid command\n", err.getMessage());
            } catch (InvalidTaskNameException | InvalidIndexException err) {
                System.out.println(err.getMessage());
            } catch (InvalidSecondaryCommandException err) {
                System.out.printf("Please include %s command and the necessary information\n", err.getMessage());
            } catch (DukeException err) {
                System.out.println("Unhandled Duke Exception");
                System.out.println(err.getMessage());
            } catch (Exception err) {
                System.out.println("Unhandled Exception");
                System.out.println(err.getMessage());
            } finally {
                Prompt.lineDivider();
            }
        }

    }

    /**
     * List all current task in the taskList.
     */
    private static void listTasks() {
        taskList.listTask();
    }

    /**
     * Given an index, mark a task as done.
     *
     * @param index index of the task we would like to mark as done.
     */
    private static void checkTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.checkTask(Integer.parseInt(index));
        taskList.listTask();
    }

    /**
     * Given an index, mark a task as undone.
     *
     * @param index index of the task we would like to mark as undone.
     */
    private static void uncheckTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.uncheckTask(Integer.parseInt(index));
        taskList.listTask();
    }

    /**
     * Given an index, delete a task.
     *
     * @param index index of the task we would like to delete.
     */
    private static void deleteTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.deleteTask(Integer.parseInt(index));
        taskList.listTask();
    }

    /**
     * Given a task, add it to the task list.
     *
     * @param <T>  type of the task we would like to add to the task list.
     * @param task the task we would like to add to the task list.
     */
    private static <T extends Task> void addTask(T task) {
        taskList.addTask(task);
        taskList.listTask();
    }
}
