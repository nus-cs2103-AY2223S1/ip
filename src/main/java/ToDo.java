public class ToDo extends Task {
    public ToDo() {
        super();
    }

    @Override
    public void addName(String userInput) throws DukeException {
        if (userInput.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        super.addName(userInput.substring(5));
    }

    @Override
    public String getStatus() {
        return String.format("[T]%s", super.getStatus());
    }
}
