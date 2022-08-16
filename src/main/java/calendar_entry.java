/**
 * a specific entry in a calendar
 * over the course of the development process, i will try to make this as close to the ics file format as possible
 * @author albertZhangTJ
 */
public class calendar_entry {
    private String title;
    private boolean is_completed;

    public calendar_entry(String title){
        this.title=title;
        this.is_completed=false;
    }

    public int mark_as_completed(){
        if (this.is_completed){
            return 208;
        }
        this.is_completed=true;
        return 200;
    }

    public int mark_as_incomplete(){
        if (!this.is_completed){
            //I know this is not what exactly http status code 208 means
            //but it is the closest one that I can think of
            return 208;
        }
        this.is_completed=false;
        return 200;
    }

    @Override
    public String toString(){
        String ans="";
        if (this.is_completed){
            ans=ans+"[X] ";
        }
        else {
            ans=ans+"[ ] ";
        }
        return ans+this.title;
    }
}
