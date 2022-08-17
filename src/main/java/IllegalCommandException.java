class IllegalCommandException extends IllegalArgumentException {
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means.";
    }
}
