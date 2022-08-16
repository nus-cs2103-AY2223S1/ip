/**
 * a specific entry in a calendar
 * over the course of the development process, i will try to make this as close to the ics file format as possible
 * @author albertZhangTJ
 */
public class calendar_entry {
    private String title;

    public calendar_entry(String title){
        this.title=title;
    }

    @Override
    public String toString(){
        return this.title;
    }
}
