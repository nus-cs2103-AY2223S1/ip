package alpha;

import alpha.command.Mark;

public class AlphaException extends Exception{
    public AlphaException(String message) {
        super(message);
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof AlphaException) {
            AlphaException a = (AlphaException) obj;
            return (a.getMessage().equals(this.getMessage()));
        }
        return false;
    }
}
