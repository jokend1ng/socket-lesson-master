package study.socket.common;

public class MyResult {
    private Message message;
    private SenderFile file;

    public MyResult() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public SenderFile getFile() {
        return file;
    }

    public void setFile(SenderFile file) {
        this.file = file;
    }
}
