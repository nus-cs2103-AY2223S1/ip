package Rabbit.RabbitException;

public class InvalidInputException extends RabbitException {
    @Override
    public String toString() {
        return "I only get paid answering things within my job scope, ok?";
    }
}
