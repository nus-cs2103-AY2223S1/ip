class EndProgramException extends Exception {

    private final int code = 1;

    public int hashcode() {
        return code;
    }
    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == code;
    }
    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
