package scottie.tasks;

class InvalidTaskDataException extends Exception {
    InvalidTaskDataException(String errorMessage) {
        super(errorMessage);
    }
}
