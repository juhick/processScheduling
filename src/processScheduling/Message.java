package processScheduling;

public class Message {
    private String message;
    Message(){
        message = "";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message += message;
    }
}
