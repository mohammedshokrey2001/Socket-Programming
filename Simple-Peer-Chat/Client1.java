import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    static DataOutputStream dataOutputStream;
    static DataInputStream dataInputStream;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // Create Reader

        try {
            Socket socket = new Socket("127.0.0.1", 5000);


            while (true) {

                System.out.print("you: ");
                String message = scan.nextLine();
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(message);
                if (message.equals("bye")) {
                    break;
                }

                dataInputStream = new DataInputStream(socket.getInputStream());
                String messageFromClient2 = dataInputStream.readUTF();


                System.out.println("client2: " + messageFromClient2);

                if (messageFromClient2.equals("bye")) {
                    System.out.println("~~~~ you cant complete this conversation");

                    break;
                }
            }
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

}

