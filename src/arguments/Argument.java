package arguments;

import input.Input;

/** Represents a CLI argument with type T. Implementors provide own implementation of validation
 * @param <T> Type of the argument expected from CLI
 */
public abstract class Argument<T> {
    protected T value;
    protected Input input;
    protected String argumentName;

    public Argument(Input input, String argumentName) {
        this.input = input;
        this.argumentName = argumentName;
    }

    /** This method should process the input and set value for this argument, or throw exception if input is invalid
     * @throws IllegalArgumentException - if input does not contain what this argument needs
     */
    public abstract void validate() throws IllegalArgumentException;

    // get must be abstract because how a particular Argument extracts a value may be different e.g parseInt vs
    // parsing a datetime string, etc.
    public T getParameter() throws IllegalArgumentException {
        validate();
        return value;
    }
}
