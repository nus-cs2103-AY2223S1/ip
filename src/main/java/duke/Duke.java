package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.DukeException.*;
import duke.Parser.CommandType;
import duke.Parser.Parser;
import duke.Storage.Cache;
import duke.Storage.TaskList;
import duke.Tasks.Task;

public class Duke {
    public static TaskList taskList;

    public Duke () {
        taskList = new TaskList();
    }

    public static void main(String[] args) throws IOException, DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Cache cache = new Cache(System.getProperty("user.dir") + "\\data\\duke.txt");
        taskList = cache.printPath();
        run();
        cache.update(taskList);
    }

    /**
     * Main duke.Ui function:
     * 1. Greetings on opening.
     * 2. bye: to exit.
     * 3. list: to print task list.
     * 4. Else: to add task.
     */
    public static void run() throws IncomplateCommandException, TaskOutOfBoundException,
            NoSuchCommandException, TaskCompletionException, DateTimeFormatException {
        Scanner in = new Scanner(System.in);
        boolean lastCommandOrNot = false;
        Task task;
        String command, suggestion;
        String[] commandList;
        Integer taskIndex = null, i;
        CommandType type;

        Ui.greet();
        while (!lastCommandOrNot) {
            command = in.nextLine().trim();
            commandList = command.split(" ", 2);
            command = (Parser.multipleVariable(commandList[0])) ? commandList[0] : command;
            type = CommandType.map(command);

            try {
                switch (type) {
                    case BYE: {
                        Ui.bye();
                        lastCommandOrNot = true;
                        break;
                    }
                    case MARK:
                    case UNMARK: {
                        taskIndex = Parser.strToInt(commandList[1]) - 1;
                        task = taskList.get(taskIndex);
                        switch (type) {
                            case MARK: {
                                if (!task.checkDone())
                                    Ui.mark(task);
                                else {
                                    System.out.println("     ☹ OOPS!!! The task specified is already done");
                                    throw new TaskCompletionException("");
                                }
                                break;
                            }
                            case UNMARK: {
                                if (task.checkDone())
                                    Ui.unmark(task);
                                else {
                                    System.out.println("     ☹ OOPS!!! The task specified is not done yet");
                                    throw new TaskCompletionException("");
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case DELETE: {
                        taskIndex = Parser.strToInt(commandList[1]) - 1;
                        task = taskList.get(taskIndex);
                        Ui.delete(task);
                        taskList.remove(task);
                        for (i = taskIndex; i < taskList.countTask(); i++) {
                            taskList.get(i).updateRemoval();
                        }
                        break;
                    }
                    case LIST: {
                        Ui.list(taskList);
                        break;
                    }
                    case TODO: {
                        taskList.add(Ui.addToDo(commandList[1]));
                        break;
                    }
                    case DEADLINE: {
                        taskList.add(Ui.addDeadline(commandList[1]));
                        break;
                    }
                    case EVENT: {
                        taskList.add(Ui.addEvent(commandList[1]));
                        break;
                    }
                    default:
                        throw new NoSuchCommandException("     ☹ Unfortunately, but I cannot understand what that means :-(");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (command.equals("mark") || command.equals("unmark")) {
                    System.out.println("     ☹ OOPS!!! The index of a task cannot be empty.");
                    suggestion = "Try again with a task index";
                } else {
                    System.out.println("     ☹ OOPS!!! The description of a task cannot be empty.");
                    suggestion = "Try again with a task description that you wanna track";
                }
                throw new IncomplateCommandException(e.getMessage(), suggestion);

                //IndexOutOfBoundsException mark 1 > totalNumber
                //check mark/ unmark
            } catch (IndexOutOfBoundsException e) {
                if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                    System.out.println("     ☹ OOPS!!! You have less than " + (taskIndex + 1) + " task(s).");
                    suggestion = "Try again with a task index";
                }
                throw new TaskOutOfBoundException(e.getMessage());
            }
        }
    }
}