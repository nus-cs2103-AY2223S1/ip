package command;

import java.text.ParseException;

public abstract class Command {
    public abstract String execute() throws ParseException;
}
