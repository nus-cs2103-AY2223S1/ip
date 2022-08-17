public class ToDo extends Task{
    public ToDo(String description){
        super(description.split(" ",2)[1]);
    }
    @Override
    //return the task in a printable format
    public String printTask(){
        return "[T]"+super.printTask();
    }
}
