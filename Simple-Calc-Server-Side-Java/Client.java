package server_client_calc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Socket socket;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        Scanner scanner = new Scanner(System.in);

        try {

             socket = new Socket("127.0.0.1",10000);
             dataInputStream = new DataInputStream( socket.getInputStream());
             dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("~~~~calculation system~~~ ");
            System.out.println("~~~~input close to stop work");
             while (true) {


                 System.out.print("input the operation like op:first number:second number:   ");
                 String op = scanner.nextLine();

                 if (op.equals("close")){
                     break;
                 }

                 dataOutputStream.writeUTF(op);
                 String data = dataInputStream.readUTF();

                 System.out.println("out put from server: " + data);


             }
            dataOutputStream.writeUTF("close");
            dataOutputStream.close();

            dataInputStream.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
