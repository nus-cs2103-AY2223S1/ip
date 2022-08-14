public class SkylarkException extends Exception{
    public SkylarkException(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but Skylark don't know what that means :-(";
    }
}
