import exceptions.*;
import task.*;
import utils.Input;
import utils.Prompt;
import utils.Utils;

import java.util.Scanner;

public class Duke {

    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Prompt.startPrompt();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            try {
                String inputString = scanner.nextLine();
                if (inputString.length() == 0) {
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

    private static void listTasks() {
        taskList.listTask();
    }

    private static void checkTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.checkTask(Integer.parseInt(index));
        taskList.listTask();
    }

    private static void uncheckTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.uncheckTask(Integer.parseInt(index));
        taskList.listTask();
    }

    private static void deleteTask(String index) throws InvalidIndexException {
        if (Utils.isNotParsable(index)) {
            throw new InvalidIndexException(String.format("%s is not a number", index));
        }
        taskList.deleteTask(Integer.parseInt(index));
        taskList.listTask();
    }

    private static <T extends Task> void addTask(T task) {
        taskList.addTask(task);
        taskList.listTask();
    }
}
