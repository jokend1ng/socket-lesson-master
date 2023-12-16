package study.socket.client;

import study.socket.common.ConnectionService;
import study.socket.common.Message;
import study.socket.common.MyResult;
import study.socket.common.SenderFile;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.file.*;
import java.util.Scanner;


public class Client implements Runnable {
    private InetSocketAddress remoteAddress;
    private MyResult result = new MyResult();


    public Client(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;

    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Thread th1 =new Thread(()-> {
        while (true) {

            result.setMessage(null);
            result.setFile(null);
            System.out.println("Введите текст путь к файлу или /exit");
            String text = scanner.nextLine();
            if (text.equals("/exit")) break;
            if (text.endsWith(".txt")) {

                System.out.println("Введите длину описания");
                int n = scanner.nextInt();
                System.out.println("Введите размер файла");
                int m = scanner.nextInt();
                byte [] array = null;
                try {
                    array = Files.readAllBytes(Paths.get(text));
                } catch (IOException e) {
                    System.out.println("не возмоджно прочитать файл");
                }
                result.setFile(new SenderFile(new File(text), n, m, text,array));


            } else {
                result.setMessage(new Message(text));
            }
        }
        });

        Thread th2 = new Thread(()->{
            try (Socket socket = new Socket()) {
                socket.connect(remoteAddress);
                try (ConnectionService service = new ConnectionService(socket)) {
                    service.writeInput(result);
                    MyResult result1 = service.readInput();
                    System.out.println(result1.getMessage().getText());
                }

            } catch (IOException e) {
                System.out.println("Сервер перестал отвечать");
            }

        });
    }


}
