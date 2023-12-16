package study.soket.server;

import study.socket.common.ConnectionService;
import study.socket.common.Message;
import study.socket.common.MyResult;
import java.io.IOException;
import java.net.ServerSocket;



public class ServerConnect implements Runnable{
    private ServerSocket serverSocket;


    public ServerConnect(ServerSocket sv) {
        this.serverSocket = sv;

    }

    @Override
    public void run() {

        ConnectionService service = null;
        try {
            service = new ConnectionService(serverSocket.accept());
        } catch (IOException e) {
            System.out.println("не возможно подключиться к серверу");;
        }
        while (true) {
           try {


                MyResult result = service.readInput();
                if (result.getMessage() != null) {
                    System.out.println(result.getMessage().getText());
                    result.setMessage(new Message("from server"));
                    service.writeInput(result);
                } else if (result.getFile() != null) {
                    result.setMessage(new Message("Загружен новый файл " +
                            result.getFile().getFile().getName() + " " + result.getFile().getDesc()));
                    service.writeInput(result);


                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Ошибка подключение клиента");
            }

        }
    }
    }

