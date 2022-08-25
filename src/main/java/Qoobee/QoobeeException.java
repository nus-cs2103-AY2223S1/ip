package Qoobee;

public class QoobeeException extends Exception {

    public QoobeeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + super.getMessage();
    }
}
