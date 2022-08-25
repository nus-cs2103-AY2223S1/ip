package doke;

public class DokeException extends RuntimeException{
    String text = null;

    public DokeException(String text) {
        this.text = text;
    }

    public DokeException() {}

    @Override
    public String toString() {
        String temp = text == null
                ? "What language are you talking? JK. Something went wrong."
                : "I'm sorry, your " + text + "'s description can't be empty";
        return "_________________________" + "\n" + temp
                + "\n" + "_________________________";
    }
}
