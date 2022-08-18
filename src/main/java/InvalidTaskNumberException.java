public class InvalidTaskNumberException extends DukeException{
    /**
     * The command that threw the exception.
     */
    private String cmd;

    /**
     * The invalid task number entered.
     */
    private String number;

    /**
     * Constructor for an InvalidTaskNumberException.
     * @param cmd The command that threw the exception.
     * @param number The invalid task number entered.
     */
    public InvalidTaskNumberException(String cmd, String number) {
        this.cmd = cmd;
        this.number = number;
    }

    @Override
    public String toString() {
        return super.toString() + (number.equals("") ? "The command " + this.cmd + " requires a task number after!" :
                " The task \"" + this.number + "\" does not exist!");
    }
}
