package yilia.exception;

import yilia.Type;

public class DescriptionEmptyException extends Exception {
    private final Type type;
    public DescriptionEmptyException(Type content) {
        this.type = content;
    }
    @Override
    public String getMessage() {
        return "☹ OOPS!!! The description of " + (type == Type.EVENT ? "an " : "a ")
                + type.toString().toLowerCase() + " cannot be empty.";
    }
}
