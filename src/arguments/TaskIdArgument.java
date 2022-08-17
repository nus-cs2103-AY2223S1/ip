package arguments;

import input.Input;

public class TaskIdArgument extends Argument<Integer> {
    public TaskIdArgument(Input input) {
        super(input, "id");
    }

    // This method exists so we can process arbitary no. of arguments and get error messages to show to user
    @Override
    public void validate() throws IllegalArgumentException {
        // validate was already called
        if (super.value != null) return;

        try {
            String idParam = input.getParameter(super.argumentName);
            super.value = Integer.parseInt(idParam);
        }

        catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Oops! Task number should be an integer :)");
        }

        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Oops! This command needs a task number");
        }

    }

    @Override
    public Integer getParameter() throws IllegalArgumentException {
        validate();
        return super.value;
    }
}
