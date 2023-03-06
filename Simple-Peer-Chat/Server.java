import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            Socket client1 = serverSocket.accept();
            Socket client2 = serverSocket.accept();
            System.out.println("Server is ready for connecting to clients!!---");
            while (true) {


                DataInputStream dis1 = new DataInputStream(client1.getInputStream());
                DataOutputStream dos1 = new DataOutputStream(client1.getOutputStream());



                DataInputStream dis2 = new DataInputStream(client2.getInputStream());
                DataOutputStream dos2 = new DataOutputStream(client2.getOutputStream());


                String messageFrom1to2 = (String) dis1.readUTF();
                dos2.writeUTF(messageFrom1to2);

                String messageFrom2to1 = (String)dis2.readUTF();
                dos1.writeUTF(messageFrom2to1);

                if (messageFrom1to2.equals("bye") ||messageFrom2to1.equals("bye")){
                    dis2.close();
                    dos2.close();
                    dis1.close();
                    dos1.close();
                    client2.close();
                    client1.close();
                    break;
                }


            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
