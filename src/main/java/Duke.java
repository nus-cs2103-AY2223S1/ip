import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {



    /* Parses a command into an n x 2 array, where n is the number of
       parameters passed by the user. Parameters are seperated by a "/".
       Each parameter is then split into two, the parameter name, and
       the parameter's content.
     */
    private static String[][] parseCommand(String command) {
        return Stream.of(command.trim().split("\\s+/"))
                .map(s -> s.split("\\s+", 2))
                .map(arr -> {
                    if (arr.length == 1) return new String[]{arr[0], null};
                    else return arr;
                })
                .toArray(String[][]::new);
    }

    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("data/save.txt");
        TaskList taskList;
        boolean exitCalled = false;

        try {
            ui.showStorageLoadingMessage();
            taskList = storage.load();
            ui.showReply(String.format("Save file loaded. You currently have %d tasks.\n", taskList.getLength()));
        } catch (DukeException e) {
            ui.showException(e);
            taskList = new TaskList();
        } finally {
            ui.showSeperator();
        }

        ui.showWelcome();
        ui.showSeperator();

        while (!exitCalled) {
            try {
                String[][] userParams = parseCommand(ui.readCommand());
                String commandName = userParams[0][0];

                switch (commandName) {
                    case "list": {
                        StringBuilder reply = new StringBuilder();
                        String[] stringifiedTaskList = taskList.toStringList();
                        reply.append("Here are your tasks that I have recorded:");
                        if (stringifiedTaskList.length == 0) {
                            reply.append("\nCongratulations, you don't need to do anything right now!");
                        }
                        for (int i = 0; i < stringifiedTaskList.length; i++) {
                            reply.append(String.format("\n%02d. %s", i + 1, stringifiedTaskList[i]));
                        }
                        ui.showReply(reply.toString());
                        break;
                    }

                    case "bye": {
                        ui.showGoodbye();
                        exitCalled = true;
                        break;
                    }

                    // TODO: A lot of code repetition for the next three pieces of code... Not sure how to resolve yet.

                    case "todo": {
                        Todo newTodo = new Todo(userParams[0][1]);
                        taskList.addTask(newTodo);
                        ui.showReply(String.format("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded", newTodo, taskList.getLength()));
                        break;
                    }

                    case "deadline": {
                        String endTime = null;
                        for (int i = 1; i < userParams.length; i++) {
                            if (userParams[i][0].equals("by")) {
                                endTime = userParams[i][1];
                                break;
                            }
                        }
                        Deadline newDeadline = new Deadline(userParams[0][1], endTime);
                        taskList.addTask(newDeadline);
                        ui.showReply(String.format("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newDeadline, taskList.getLength()));
                        break;
                    }

                    case "event": {
                        String rangeTime = null;
                        for (int i = 1; i < userParams.length; i++) {
                            if (userParams[i][0].equals("at")) {
                                rangeTime = userParams[i][1];
                                break;
                            }
                        }
                        Event newEvent = new Event(userParams[0][1], rangeTime);
                        taskList.addTask(newEvent);
                        ui.showReply(String.format("Gotcha! I added the following task to the list:\n  %s\nCurrently, I have %d tasks recorded\n", newEvent, taskList.getLength()));
                        break;
                    }

                    case "mark": {
                        try {
                            int markIndex = Integer.parseInt(userParams[0][1]) - 1;
                            if (taskList.getTask(markIndex).getIsDone()) {
                                ui.showReply(String.format("Sorry, but it seems you have marked this task as done:\n  %s", taskList.getTask(markIndex)));
                            } else {
                                taskList.getTask(markIndex).setDone(true);
                                ui.showReply(String.format("Noice! I've marked this task as done:\n  %s", taskList.getTask(markIndex)));
                            }
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.", e);
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("I do not have a task with that number in my list.", e);
                        }
                        break;
                    }

                    case "unmark": {
                        try {
                            int unmarkIndex = Integer.parseInt(userParams[0][1]) - 1;
                            if (taskList.getTask(unmarkIndex).getIsDone()) {
                                taskList.getTask(unmarkIndex).setDone(false);
                                ui.showReply(String.format("Alright, I've marked this task as not done:\n  %s", taskList.getTask(unmarkIndex)));
                            } else {
                                ui.showReply(String.format("Sorry, but it seems you haven't marked this task as done:\n  %s", taskList.getTask(unmarkIndex)));
                            }
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("I do not have a task with that number in my list.", e);
                        }
                        break;
                    }

                    case "delete": {
                        try {
                            int delIndex = Integer.parseInt(userParams[0][1]) - 1;
                            Task delTask = taskList.getTask(delIndex);
                            taskList.deleteTask(delIndex);
                            ui.showReply(String.format("Understood, I've deleted the following task:\n  %s\nYou now have %d tasks remaining.\n", delTask, taskList.getLength()));
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("I do not have a task with that number in my list.", e);
                        }
                        break;
                    }

                    default: {
                        throw new DukeException("Sorry, I don't know what that means.\n"
                                + "Did you make a mistake? Please note that commands are case-sensitive.");
                    }
                }

                // Not the most efficient solution, but reduces code duplication. TODO: Revisit this when making commands into objects.
                storage.save(taskList);
            } catch (DukeException e) {
                ui.showException(e);
            } finally {
                ui.showSeperator();
            }
        }
    }
}
