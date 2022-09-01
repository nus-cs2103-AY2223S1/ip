package arguments;

import java.util.ArrayList;
import java.util.List;

import exceptions.DukeException;
import input.Input;



/**
 * Represents a CLI argument with type T. Implementors provide own implementation of validation
 * @param <T> Type of the argument expected from CLI
 */
public abstract class Argument<T> {
    protected T value;
    protected Input input;
    protected String argumentName;

    protected Argument(Input input, String argumentName) {
        this.input = input;
        this.argumentName = argumentName;
    }

    /**
     * This method should process the input and set value for this argument, or throw exception if input is invalid
     * @throws exceptions.DukeException - if input does not contain what this argument needs
     */
    public abstract void validate() throws DukeException;

    /**
     * @return Argument passed in of type T
     * @throws DukeException if any errors occur during validation
     */
    public T getParameter() throws DukeException {
        validate();
        return value;
    }

    /**
     * Validate a variable number of Argument objects, collecting any error messages into a List to return
     * @param args Variable number of argument objects
     * @return List of error messages from validation
     */
    public static List<String> validateArguments(Argument... args) {
        List<String> msgs = new ArrayList<>();

        for (Argument arg: args) {
            try {
                arg.validate();
            } catch (DukeException ex) {
                msgs.add(ex.getMessage());
            }
        }

        return msgs;
    }
}
