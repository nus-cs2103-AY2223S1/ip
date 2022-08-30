package duke;

import java.util.Scanner;

/**
 * The class that deals with user commands.
 */
public class Parser {
    private final Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Starts the parser object and carry out the user commands.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); !input.equals("bye"); input = sc.nextLine()) {
            if (input.equals("list")) {
                duke.ui.printTaskList(duke.tasks);
            } else if (input.startsWith("mark ")) {
                try {
                    int number = Integer.parseInt(input.substring(5));
                    duke.tasks.mark(number);
                    duke.ui.markingUi("mark", duke.tasks.getTask(number - 1));
                } catch (DukeException e) {
                    duke.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.missingNumberAfterCommand("mark");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    duke.tasks.unMark(number);
                    duke.ui.markingUi("unmark", duke.tasks.getTask(number - 1));
                } catch (DukeException e) {
                    duke.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.missingNumberAfterCommand("unmark");
                }
            } else if (input.startsWith("todo ")) {
                try {
                    input = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.someThingMissingError("todo");
                    continue;
                }
                ToDo toDo = new ToDo(input);
                duke.tasks.addTask(toDo);
                duke.ui.addTasksUi(toDo);
                duke.ui.numberOfTasksUi(duke.tasks);
            } else if (input.startsWith("deadline ")) {
                int end = input.indexOf("/by ");
                String textInput;
                String byInput;
                try {
                    textInput = input.substring(9, end);
                    byInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.someThingMissingError("deadline");
                    continue;
                }
                Deadline deadline = new Deadline(textInput, byInput);
                duke.tasks.addTask(deadline);
                duke.ui.addTasksUi(deadline);
                duke.ui.numberOfTasksUi(duke.tasks);
            } else if (input.startsWith("event ")) {
                int end = input.indexOf("/at ");
                String textInput;
                String atInput;
                try {
                    textInput = input.substring(6, end);
                    atInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.someThingMissingError("event");
                    continue;
                }
                Event event = new Event(textInput, atInput);
                duke.tasks.addTask(event);
                duke.ui.addTasksUi(event);
                duke.ui.numberOfTasksUi(duke.tasks);
            } else if (input.startsWith("delete ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    Task removed = duke.tasks.deleteTask(number);
                    duke.ui.deleteUi(removed);
                    duke.ui.numberOfTasksUi(duke.tasks);
                } catch (DukeException e) {
                    duke.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    duke.ui.missingNumberAfterCommand("delete");
                }
            } else if (input.startsWith("find ")) {
                try {
                    String keyWord = input.substring(5);
                    TaskList tempList = duke.tasks.find(keyWord);
                    duke.ui.printTaskList(tempList);
                } catch (DukeException e) {
                    duke.ui.noSuchTaskError();
                }
            } else {
                duke.ui.invalidCommand();
            }
        }
        duke.storage.save(duke.tasks);
    }
}
