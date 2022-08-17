public class InvalidInputException extends DukeException{
    public InvalidInputException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return String.format("%s I'm sorry, but I don't know what that means :-(",super.toString());
    }
}
