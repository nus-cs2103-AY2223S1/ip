package commands;

import byu.*;
import exceptions.InvalidIndex;

public abstract class Command {

    public abstract void execute(ToDoList list, Ui ui) throws InvalidIndex;
    public abstract boolean isExit();
}
