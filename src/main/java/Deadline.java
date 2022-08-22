public class Deadline extends ListObject{

    String doBy;

    public Deadline(String task, int status) {
        super(task, status);
        this.doBy= "untimed";
    }

    public Deadline(String task, int status, String doBy){
        super(task, status);
        this.doBy = doBy;
    }

    @Override
    public String toString(){
        return "\n[D]" + super.toString() + " (by: " + doBy + ")";
    }

}
