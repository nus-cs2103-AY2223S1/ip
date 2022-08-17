package arguments;

import input.Input;

public class DescriptionArgument extends Argument<String> {
    public DescriptionArgument(Input input) {
        super(input, "d");
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (super.value != null) return;

        try {
            String desc = input.getParameter(super.argumentName);
            super.value = desc;

            if (desc.trim().equals("")) {
                throw new EmptyArgumentException("The description should not be empty!");
            }
            System.out.println(desc.trim().equals(""));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("This command needs a non-empty description.");
        } catch (EmptyArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
