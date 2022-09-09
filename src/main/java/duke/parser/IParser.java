package duke.parser;

import duke.commands.BaseCommand;
import duke.exceptions.ParseException;

public interface IParser<T extends BaseCommand> {
    T parse(String arguments) throws ParseException;
}
