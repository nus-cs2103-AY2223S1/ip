public class ToDo extends ListObject{

    public ToDo(String task, int status) {
        super(task, status);
    }


    @Override
    public String toString(){
        return "\n[T]" + super.toString();
    }

}
