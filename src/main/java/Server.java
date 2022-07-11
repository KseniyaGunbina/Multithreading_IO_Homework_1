import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int getFibonacci(int num) {
        int first = 0;
        int second = 1;
        int result = num;
        for (int i = 1; i < num; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(23444);
        while (true){
            try (Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;

                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                    int result = getFibonacci(Integer.valueOf(line));
                    out.println("***** Ваше значение из последовательности Фибоначчи: " + result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
