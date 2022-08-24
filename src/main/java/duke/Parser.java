package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;


/**
 * Parses in the user input and executes the corresponding actions
 */
public class Parser {
    private final String command;
    private final Ui ui;

    public Parser(String command, Ui ui) {
        this.command = command;
        this.ui = ui;
    }

    /**
     * Parses the command and matches it with the corresponding action to be taken
     * Executes the action on the task list given
     * @param tasks ArrayList of current tasks stored on the chatbot
     * @return boolean true if bye command entered and chatbot should be stopped, false otherwise
     */
    public boolean executeCommand(TaskList tasks) {
        String[] commands = command.split(" ");
        try {
            if (command.equals("bye")) {
                ui.showBye();
                return true;
            } else if (command.equals("list")) {
                ui.printCorrectMessage(Ui.Commands.LIST, tasks, 0);
            } else if (commands[0].equals("mark")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("mark");
                }
                int index = Integer.parseInt(commands[1]) - 1;
                tasks.markIndex(index);
                ui.printCorrectMessage(Ui.Commands.MARK, tasks, index);
            } else if (commands[0].equals("unmark")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("unmark");
                }
                int index = Integer.parseInt(commands[1]) - 1;
                tasks.unmarkIndex(index);
                ui.printCorrectMessage(Ui.Commands.UNMARK, tasks, index);
            } else if (commands[0].equals("todo")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("todo");
                }
                String[] newCommands = Arrays.copyOfRange(commands, 1, commands.length);
                String newCommand = String.join(" ", newCommands);
                tasks.add(new Todo(newCommand));
                ui.printCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
            } else if (commands[0].equals("event")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("event");
                }
                int atMarker = 1;
                for (int i = 0; i < commands.length; i++) {
                    if (commands[i].equals("/at")) {
                        atMarker = i;
                    }
                }
                String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                String newCommand = String.join(" ", newCommands);
                String[] time = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                String timeString = String.join(" ", time);
                try {
                    LocalDate timeStringParsed = LocalDate.parse(timeString);
                    tasks.add(new Event(newCommand,
                            timeStringParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                    ui.printCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
                } catch (DateTimeParseException error) {
                    ui.printErrorMessage("datetime");
                }
            } else if (commands[0].equals("deadline")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("deadline");
                }
                int atMarker = 1;

                for (int i = 0; i < commands.length; i++) {
                    if (commands[i].equals("/by")) {
                        atMarker = i;
                    }
                }
                String[] newCommands = Arrays.copyOfRange(commands, 1, atMarker);
                String newCommand = String.join(" ", newCommands);
                String[] deadline = Arrays.copyOfRange(commands, atMarker + 1, commands.length);
                String deadlineString = String.join(" ", deadline);
                try {
                    LocalDate deadlineParsed = LocalDate.parse(deadlineString);
                    tasks.add(new Deadline(newCommand,
                            deadlineParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                    ui.printCorrectMessage(Ui.Commands.TASK, tasks, tasks.getSize() - 1);
                } catch (DateTimeParseException e) {
                    ui.printErrorMessage("datetime");
                }
            } else if (commands[0].equals("delete")) {
                if (commands.length == 1) {
                    throw new EmptyDescriptionException("delete");
                }
                int index = Integer.parseInt(commands[1]) - 1;
                tasks.delete(index);
                ui.printCorrectMessage(Ui.Commands.DELETE, tasks, index);
            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException | EmptyDescriptionException e) {
            ui.printErrorMessage(e.getMessage());
        }
        return false;
    }
}
