public class IncompleteParamException extends DukeException{
    public IncompleteParamException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return String.format("%s Your '%s' is incomplete!",super.toString(),this.getMessage());
    }
}
