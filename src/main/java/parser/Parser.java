package parser;
import printer.Printer;
import exception.CommandException;
import exception.EmptyCommandException;
import command.Command;
import command.CommandHandler;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;

public class Parser {
    private boolean isListening = true;
    private final CommandHandler handler;

    public Parser() {
        this.handler = new CommandHandler();
    }

    public void parseText(String text) {
        try {
            List<String> commands = Arrays.stream(text.trim().split(" ", 2))
                    .map(String::trim).collect(Collectors.toList());
            String mainCommand = commands.get(0);
            String description = commands.size() > 1 ? commands.get(1) : "";

            if (mainCommand.equals("bye")) {
                handler.processSingleCommand(Command.BYE);
                this.isListening = false;
            } else if (mainCommand.equals("list")) {
                handler.processSingleCommand(Command.LIST);
            } else if (mainCommand.equals("mark")) {
                handler.editTask(Command.MARK, description);
            } else if (mainCommand.equals("unmark")) {
                handler.editTask(Command.UNMARK, description);
            } else if (mainCommand.equals("delete")) {
                handler.editTask(Command.DELETE, description);
            } else if (mainCommand.equals("todo")) {
                handler.addTask(Command.TODO, description);
            } else if (mainCommand.equals("deadline")) {
                handler.addTask(Command.DEADLINE, description);
            } else if (mainCommand.equals("event")) {
                handler.addTask(Command.EVENT, description);
            } else if (mainCommand.isEmpty()) {
                throw new EmptyCommandException();
            } else {
                throw new CommandException();
            }
        } catch(CommandException e) {
            Printer.print(e.getMessage());
        }
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
