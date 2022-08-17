public class calendar_entry_todo extends calendar_entry{

    public calendar_entry_todo(String title){
        super(title);
    }

    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
}