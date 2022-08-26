package duke.parser;

import duke.command.Command;

public class Parser {

    public static Command parse(String comm) {
        return Command.of(comm);
    }
}
