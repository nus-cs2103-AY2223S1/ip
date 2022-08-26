package duke;

/**
 * Duke Exception class to model a self-created exception
 */
class DukeException extends Exception {
    private String msg;

    /**
     * Constructs an instance of DukeException
     * @param s error message
     */
    public DukeException(String s) {
        this.msg = s;
    }

    public String getMsg() {
        return this.msg;
    }
}
