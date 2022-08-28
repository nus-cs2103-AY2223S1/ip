package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    private final Jamie jamie;

    public Parser(Jamie jamie) {
        this.jamie = jamie;
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); !input.equals("bye"); input = sc.nextLine()) {
            if (input.equals("list")) {
                jamie.ui.printTaskList(jamie.tasks);
            } else if (input.startsWith("mark ")) {
                try {
                    int number = Integer.parseInt(input.substring(5));
                    jamie.tasks.mark(number);
                    jamie.ui.markingUi("mark", jamie.tasks.getTask(number - 1));
                } catch (DukeException e) {
                    jamie.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.missingNumberAfterCommand("mark");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    jamie.tasks.unMark(number);
                    jamie.ui.markingUi("unmark", jamie.tasks.getTask(number - 1));
                } catch (DukeException e) {
                    jamie.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.missingNumberAfterCommand("unmark");
                }
            } else if (input.startsWith("todo ")) {
                try {
                    input = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.someThingMissingError("todo");
                    continue;
                }
                ToDo toDo = new ToDo(input);
                jamie.tasks.addTask(toDo);
                jamie.ui.addTasksUi(toDo);
                jamie.ui.numberOfTasksUi(jamie.tasks);
            } else if (input.startsWith("deadline ")) {
                int end = input.indexOf("/by ");
                String textInput;
                String byInput;
                try {
                    textInput = input.substring(9, end);
                    byInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.someThingMissingError("deadline");
                    continue;
                }
                Deadline deadline = new Deadline(textInput, byInput);
                jamie.tasks.addTask(deadline);
                jamie.ui.addTasksUi(deadline);
                jamie.ui.numberOfTasksUi(jamie.tasks);
            } else if (input.startsWith("event ")) {
                int end = input.indexOf("/at ");
                String textInput;
                String atInput;
                try {
                    textInput = input.substring(6, end);
                    atInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.someThingMissingError("event");
                    continue;
                }
                Event event = new Event(textInput, atInput);
                jamie.tasks.addTask(event);
                jamie.ui.addTasksUi(event);
                jamie.ui.numberOfTasksUi(jamie.tasks);
            } else if (input.startsWith("delete ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    Task removed = jamie.tasks.deleteTask(number);
                    jamie.ui.deleteUi(removed);
                    jamie.ui.numberOfTasksUi(jamie.tasks);
                } catch (DukeException e) {
                    jamie.ui.noSuchTaskError();
                } catch (StringIndexOutOfBoundsException e) {
                    jamie.ui.missingNumberAfterCommand("delete");
                }
            } else if (input.startsWith("find ")) {
                try {
                    String keyWord = input.substring(5);
                    TaskList tempList = jamie.tasks.find(keyWord);
                    jamie.ui.printTaskList(tempList);
                } catch (DukeException e) {
                    jamie.ui.noSuchTaskError();
                }
            } else {
                jamie.ui.invalidCommand();
            }
        }

        try {
            FileWriter writer = new FileWriter("Data/JamieTasks.txt", false);
            writer.write(jamie.tasks.taskListToText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
