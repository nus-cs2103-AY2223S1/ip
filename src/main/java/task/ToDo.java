package task;

import exception.MissingArgumentException;

public class ToDo extends Task{
    
    public ToDo(String description) throws MissingArgumentException{
        super("todo", description);
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
    }

    public ToDo(String description, boolean isDone) throws MissingArgumentException{
        super("todo", description, isDone);
        if (description.equals("")) {
            throw new MissingArgumentException("Description is missing");
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
