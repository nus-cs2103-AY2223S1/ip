public class DukeException extends Exception{
    public DukeException(String errorMessage){
        super(errorMessage);
    }

    public static class DukeToDoException extends DukeException{

        public DukeToDoException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class DukeCommandException extends DukeException {
        public DukeCommandException(String errorMessage) {
            super(errorMessage);
        }
    }
}
