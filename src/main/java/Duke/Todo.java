package Duke;

/**
 * The class is an extension of class Task
 * @author LimWeiJun
 */
public class Todo extends Task {
    public Todo(String desc, boolean done){
        super(desc, done);
    }

    /**
     * @return a string of the text
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * @return of type char
     */
    @Override
    public char getType() {
        return 'T';
    }

    /**
     * @return of type String
     */
    @Override
    public String getOriginalDetail() {
        return null;
    }

    /**
     * @return of type String
     */
    @Override
    public String getFormattedDetail() {
        return null;
    }
}
