package utils;

public enum UserRequest {
    MARK("mark"), BYE("bye"), LIST("list"),
    DELETE("delete"), TODO("todo"), DEADLINE("deadline"),
    EVENT("event");

    private String operation;

    UserRequest(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
