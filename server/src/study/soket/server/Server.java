package study.soket.server;

import study.socket.common.SenderFile;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class Server implements Runnable{
    private int port;
   private MyExecutorService myExecutorService;

    public Server(int port) {
        this.port = port;

        this.myExecutorService = new MyExecutorService(0, 8, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен");
            myExecutorService.execute(new ServerConnect(serverSocket));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
