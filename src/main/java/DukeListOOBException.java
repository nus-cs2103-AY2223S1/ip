public class DukeListOOBException extends DukeException{
    private static final String MSG = "The list does not contain an entry of index %d.";

    public DukeListOOBException(int index){
        super(String.format(MSG, index));
    }
}
