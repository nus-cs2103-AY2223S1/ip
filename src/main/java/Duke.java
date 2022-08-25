import duke.*;
import duke.main.Parser;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.command.Command;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final Scanner scan = new Scanner( System.in );
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadInTasks());
        } catch (IOException ioe) {
            System.exit(0);
        } catch (DukeException de) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.printIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
//                getInput(scan);
                String inData;
                inData = scan.nextLine();
                Command command = Parser.parseCommand(inData);
                isExit = command.isExitCommand();
                command.execute(tasks, ui, storage);
            } catch (DukeException de) {
                ui.printDukeException(de);
            } catch (IOException ie) {
                ui.printIoException(ie);
            }
        }

    }

//    public static void getInput(Scanner scan) throws duke.DukeException, IOException {
//        String inData;
//        try {
//            inData = scan.nextLine();
//        } catch (Exception NoSuchElementException) {
//            return;
//        }
//
//        String[] input = inData.split(" ", 2);
//        switch (input[0]) {
//            case("bye"):
//                // store existing tasks into save file
//                storage.update(tasks);
//                ui.printExit();
//                break;
//            case("mark"):
//                duke.task.Task taskToMark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
//                taskToMark.markAsDone();
//                ui.printMarkDone(taskToMark);
//                storage.update(tasks);
//                getInput(scan);
//                break;
//            case("unmark"):
//                duke.task.Task taskToUnmark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
//                taskToUnmark.markAsNotDone();
//                ui.printMarkNotDone(taskToUnmark);
//                storage.update(tasks);
//                getInput(scan);
//                break;
//            case("list"):
//                ui.printListOfTasks(tasks);
//                getInput(scan);
//                break;
//            case("todo"):
//                    if (input.length == 1) {
//                        throw new duke.DukeException("The description of a todo cannot be empty.");
//                    }
//                    duke.task.Todo curTodo = new duke.task.Todo(input[1]);
//                    addToTasklist(curTodo);
//                    getInput(scan);
//                    break;
//            case("deadline"):
//                String[] deadlineString = input[1].split("/by ", 2);
//                if (deadlineString.length == 1) {
//                    throw new duke.DukeException("The 'by date' of a deadline cannot be empty.");
//                }
//                duke.task.Deadline curDeadline = new duke.task.Deadline(deadlineString[0], LocalDate.parse(deadlineString[1]));
//                addToTasklist(curDeadline);
//                getInput(scan);
//                break;
//            case("event"):
//                String[]eventString = input[1].split("/at ", 2);
//                if (eventString.length == 1) {
//                    throw new duke.DukeException("The 'at date' of a event cannot be empty.");
//                }
//                duke.task.Event curEvent = new duke.task.Event(eventString[0], LocalDate.parse(eventString[1]));
//                addToTasklist(curEvent);
//                getInput(scan);
//                break;
//            case("delete"):
//                deleteTask(Integer.parseInt(input[1]) - 1);
//                getInput(scan);
//                break;
//            default:
//                throw new duke.DukeException("I'm sorry, but I don't know what that means :-(");
//        }
//
//    }


//    public static void addToTasklist(duke.task.Task taskToAdd) throws IOException {
//        tasks.addTask(taskToAdd);
//        ui.printAdd(taskToAdd, tasks);
//        storage.update(tasks);
//    }
//
//    public static void deleteTask(int index) throws IOException {
//        ui.printDelete(index, tasks);
//        tasks.removeTask(index);
//        storage.update(tasks);
//    }

}
