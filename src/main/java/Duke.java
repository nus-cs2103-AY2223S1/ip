import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
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

//    public static void getInput(Scanner scan) throws DukeException, IOException {
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
//                Task taskToMark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
//                taskToMark.markAsDone();
//                ui.printMarkDone(taskToMark);
//                storage.update(tasks);
//                getInput(scan);
//                break;
//            case("unmark"):
//                Task taskToUnmark = tasks.getTasks().get(Integer.parseInt(input[1]) - 1);
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
//                        throw new DukeException("The description of a todo cannot be empty.");
//                    }
//                    Todo curTodo = new Todo(input[1]);
//                    addToTasklist(curTodo);
//                    getInput(scan);
//                    break;
//            case("deadline"):
//                String[] deadlineString = input[1].split("/by ", 2);
//                if (deadlineString.length == 1) {
//                    throw new DukeException("The 'by date' of a deadline cannot be empty.");
//                }
//                Deadline curDeadline = new Deadline(deadlineString[0], LocalDate.parse(deadlineString[1]));
//                addToTasklist(curDeadline);
//                getInput(scan);
//                break;
//            case("event"):
//                String[]eventString = input[1].split("/at ", 2);
//                if (eventString.length == 1) {
//                    throw new DukeException("The 'at date' of a event cannot be empty.");
//                }
//                Event curEvent = new Event(eventString[0], LocalDate.parse(eventString[1]));
//                addToTasklist(curEvent);
//                getInput(scan);
//                break;
//            case("delete"):
//                deleteTask(Integer.parseInt(input[1]) - 1);
//                getInput(scan);
//                break;
//            default:
//                throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        }
//
//    }


//    public static void addToTasklist(Task taskToAdd) throws IOException {
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
