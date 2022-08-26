class InvalidIndexException extends Exception {
    InvalidIndexException() {
        super("Oops, it looks like your indexing is wrong.");
    }
}