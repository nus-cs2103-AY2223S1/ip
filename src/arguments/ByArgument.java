package arguments;

import input.Input;

public class ByArgument extends Argument<String> {
    public ByArgument(Input input) {
        super(input, "by");
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (super.value != null) return;

        try {
            String by = input.getParameter(super.argumentName);
            super.value = by;

            if (by.trim().equals("")) {
                throw new EmptyArgumentException("The by date should not be empty!");
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("This command needs a non-empty by argument.");
        } catch (EmptyArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
