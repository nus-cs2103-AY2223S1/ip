package duke.exceptions;

public class InvalidItemException extends Exception{
    public InvalidItemException(){
        super("Alas! You ask me to paint outside the canvas..." +
                "choose an item number from 0 to your list length! ");
    }
}
