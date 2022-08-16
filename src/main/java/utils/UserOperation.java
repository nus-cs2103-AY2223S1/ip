package utils;

public enum UserOperation {
    MARK("mark"), BYE("bye"), LIST("list"), DELETE("delete");

    private String operation;

    UserOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
