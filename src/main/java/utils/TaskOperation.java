package utils;

public enum TaskOperation {

    TODO("todo"), DEADLINE("deadline"), EVENT("event");

    private String operation;

    TaskOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return this.operation;
    }
}
