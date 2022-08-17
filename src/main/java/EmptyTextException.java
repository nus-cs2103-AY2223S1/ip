class EmptyTextException extends IllegalArgumentException {

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
