package task;

import exception.MissingArgumentException;

public class ToDo extends Task{

    private final String MESSAGE_ERROR_MISSING_DESCRIPTION = "Description is missing";
    
    public ToDo(String description) throws MissingArgumentException{
        super("todo", description);
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
    }

    public ToDo(String description, boolean isDone) throws MissingArgumentException{
        super("todo", description, isDone);
        if (description.equals("")) {
            throw new MissingArgumentException(MESSAGE_ERROR_MISSING_DESCRIPTION);
        }
    }

    
    /** 
     * returns string representation of todo task
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
