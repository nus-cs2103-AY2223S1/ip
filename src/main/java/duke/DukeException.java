package duke;

class DukeException extends Exception {
    private String msg;

    public DukeException(String s) {
        this.msg = s;
    }

    public String getMsg() {
        return this.msg;
    }
}
