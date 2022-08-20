public class DescriptionEmptyException extends Exception {

    public DescriptionEmptyException() {
        super("grrrr >:( there is no task name woof woof!");
    }

    public DescriptionEmptyException(String message) {
        super(message);
    }
}
