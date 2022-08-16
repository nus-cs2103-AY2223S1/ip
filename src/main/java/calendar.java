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

    public calendar_entry get_entry(int index){
        return cache.get(index-1);
    }

    public int mark_as_done(int index){
        if (index>this.cache.size()){
            return 417;
        }
        return this.get_entry(index).mark_as_completed();
    }

    public int mark_as_undone(int index){
        if (index>this.cache.size()){
            return 417;
        }
        return this.get_entry(index).mark_as_incomplete();
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
