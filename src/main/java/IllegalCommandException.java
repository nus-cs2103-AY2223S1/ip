class IllegalCommandException extends IllegalArgumentException {

    private final int code = 2;

    public int hashcode() {
        return code;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == code;
    }
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means.";
    }
}
