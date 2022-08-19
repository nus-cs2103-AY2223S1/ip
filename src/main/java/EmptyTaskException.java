class EmptyTaskException extends Exception {
    EmptyTaskException() {
        super("You cannot have an empty Task!\n");
    }
}