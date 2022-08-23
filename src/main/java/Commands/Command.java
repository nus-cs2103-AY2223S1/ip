package Commands;
import DaveExceptions.DaveException;

interface Command {

    public String execute() throws DaveException;

}
