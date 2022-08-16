public class AnyaException extends Exception{
    public AnyaException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Ahh Error! " + super.getMessage();
    }
}
