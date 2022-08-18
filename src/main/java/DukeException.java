class DukeException extends Exception{
    private DukeException(String text){
        super(text);
    }
    public static final DukeException idTooBig = new DukeException("id is larger than current list size");
    public static final DukeException invalidCommand = new DukeException("Invalid command");
    public static final DukeException invalidArgument =  new DukeException("Invalid argument");
    public static final DukeException taskAlreadyMarked =  new DukeException("Task already marked as done");
    public static final DukeException taskAlreadyUnmarked =  new DukeException("Task already marked as not done");
};

