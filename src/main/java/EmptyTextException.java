class EmptyTextException extends IllegalArgumentException {


    private final int code = 3;

    public int hashcode() {
        return code;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == code;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
