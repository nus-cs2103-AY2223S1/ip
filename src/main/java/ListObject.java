/**
 * Class to store list items with taks and status of completion
 */
public class ListObject{
    private String task;
    private int status;

    ListObject(String task, int status){
        this.task = task;
        this.status = status;
    }

    public String getTask(){
        return this.task;
    }

    public int getStatus(){
        return this.status;
    }

    public String showStatusIndicator(){
        if(this.status==1){
            return "[X] ";
        } else {
            return "[ ] ";
        }
    }

    public void switchStatus(){
        if(this.status==1){
            this.status=0;
        } else {
            this.status=1;
        }
    }

    @Override
    public String toString(){
        return this.showStatusIndicator()+ this.getTask();
    }

}