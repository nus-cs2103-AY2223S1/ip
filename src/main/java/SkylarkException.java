public class SkylarkException extends Exception{

    private String description;

    public SkylarkException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
