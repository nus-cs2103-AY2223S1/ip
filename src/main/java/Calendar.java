import java.util.ArrayList;

public class Calendar {
    private ArrayList<CalendarEntry> cache;

    public Calendar(){
        this.cache=new ArrayList<>();
    }

    public int add_entry(CalendarEntry to_add){
        this.cache.add(to_add);
        return 200;
    }

    public CalendarEntry get_entry(int index){
        return cache.get(index-1);
    }

    public CalendarEntry delete_entry(int index){
        if (index>this.cache.size()){
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event "+index+" in the current calendar\n");
        }
        return this.cache.remove(index-1);
    }

    public int mark_as_done(int index){
        if (index>this.cache.size()){
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event "+index+" in the current calendar\n");
            //return 417;
        }
        return this.get_entry(index).markAsCompleted();
    }

    public int mark_as_undone(int index){
        if (index>this.cache.size()){
            throw new IndexOutOfBoundsException("Es tut mir leid. There is no event "+index+" in the current calendar\n");
            //return 417;
        }
        return this.get_entry(index).markAsIncomplete();
    }

    @Override
    public String toString(){
        String ans="";
        int index=1;
        for (CalendarEntry e: this.cache){
            ans=ans+index+". "+e.toString()+"\n";
            index++;
        }
        return ans;
    }
}
