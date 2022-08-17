class EndProgramException extends Exception {

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
