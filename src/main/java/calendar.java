import java.util.ArrayList;

public class calendar {
    private ArrayList<calendar_entry> cache;

    public calendar(){
        this.cache=new ArrayList<>();
    }

    public int add_entry(calendar_entry to_add){
        this.cache.add(to_add);
        return 0;
    }

    @Override
    public String toString(){
        String ans="";
        int index=1;
        for (calendar_entry e: this.cache){
            ans=ans+index+". "+e.toString()+"\n";
            index++;
        }
        return ans;
    }
}
