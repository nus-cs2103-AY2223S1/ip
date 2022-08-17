public class ToDo extends Task{
    public ToDo(String description) throws DukeException{
        super("tempTask");
        try {
            String temp=description.split(" ")[1];
        }catch (IndexOutOfBoundsException ie) {
            throw (new DukeException("OOPS!!! The description of a todo cannot be empty."));
        }
        try {
            super.correctDescrition(description.split(" ", 2)[1]);
        }catch (IndexOutOfBoundsException ie){
            throw (new DukeException("OOPS!!! The description of a todo is still not complete."));
        }
    }
    @Override
    //return the task in a printable format
    public String printTask(){
        return "[T]"+super.printTask();
    }
}
