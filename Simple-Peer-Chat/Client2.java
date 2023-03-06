import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket("127.0.0.1", 5000);


            while (true) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                 dataOutputStream = new DataOutputStream(socket.getOutputStream());

                String messageCameFromClient1 = dataInputStream.readUTF();
                System.out.println("Client1: " + messageCameFromClient1);

                if (messageCameFromClient1.equals("bye")) {
                    System.out.println("~~~~ you cant complete this conversation");
                    break;
                }

                System.out.print("you: ");
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);

                if (message.equals("bye")) {
                    break;
                }
            }


            dataOutputStream.close();
            dataInputStream.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
