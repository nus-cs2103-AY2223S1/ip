package yilia.exception;

public class TimeFormatException extends Exception{
    @Override
    public String getMessage() {
        return "Please input a date in the correct format";
    }
}
