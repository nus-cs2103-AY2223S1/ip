public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private final String stringRepresentation;
    TaskType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
