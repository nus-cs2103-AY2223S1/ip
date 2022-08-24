package duke;

class DukeException extends Exception {
    String msg;

    public DukeException(String s) {
        this.msg = s;
    }
}
