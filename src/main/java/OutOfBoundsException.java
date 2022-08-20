public class OutOfBoundsException extends Exception {

    public OutOfBoundsException(int index) {
        super("grrrr >:( there is no number " + index + " item in the list woof woof!");
    }
}
