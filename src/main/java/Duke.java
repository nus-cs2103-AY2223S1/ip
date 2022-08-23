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
                Command runCommand;

                switch (commandName) {
                    case ListCommand.COMMAND_NAME: {
                        runCommand = new ListCommand();
                        break;
                    }

                    case ByeCommand.COMMAND_NAME: {
                        runCommand = new ByeCommand();
                        break;
                    }

                    case TodoCommand.COMMAND_NAME: {
                        runCommand = new TodoCommand(new Todo(userParams[0][1]));
                        break;
                    }

                    case DeadlineCommand.COMMAND_NAME: {
                        String endTime = null;
                        for (int i = 1; i < userParams.length; i++) {
                            if (userParams[i][0].equals("by")) {
                                endTime = userParams[i][1];
                                break;
                            }
                        }
                        runCommand = new DeadlineCommand(new Deadline(userParams[0][1], endTime));
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
                        runCommand = new EventCommand(new Event(userParams[0][1], rangeTime));
                        break;
                    }

                    case "mark": {
                        try {
                            int markIndex = Integer.parseInt(userParams[0][1]) - 1;
                            runCommand = new MarkCommand(markIndex);
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.", e);
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        }
                        break;
                    }

                    case "unmark": {
                        try {
                            int unmarkIndex = Integer.parseInt(userParams[0][1]) - 1;
                            runCommand = new UnmarkCommand(unmarkIndex);
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        }
                        break;
                    }

                    case "delete": {
                        try {
                            int delIndex = Integer.parseInt(userParams[0][1]) - 1;
                            runCommand = new DeleteCommand(delIndex);
                        } catch (NumberFormatException e) {
                            if (userParams[0][1] == null) {
                                throw new DukeException("You must pass an index value.");
                            } else {
                                throw new DukeException("You must pass an integer value. " + userParams[0][1] + " is not an integer.", e);
                            }
                        }
                        break;
                    }

                    default: {
                        runCommand = new UnknownCommand();
                        break;
                    }
                }

                runCommand.exec(taskList, ui, storage);
                exitCalled = runCommand.isTerminator();
            } catch (DukeException e) {
                ui.showException(e);
            } finally {
                ui.showSeperator();
            }
        }
    }
}
