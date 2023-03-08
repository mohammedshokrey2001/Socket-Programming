package server_client_calc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {

    public static void main(String[] args) {
        DataInputStream dataInputStream;
        ServerSocket serverSocket;
        DataOutputStream dataOutputStream;
        Socket clientSocket;
        try {
            serverSocket = new ServerSocket(10000);
            System.out.println("~~~~server is ready for accept clients~~~");
            clientSocket = serverSocket.accept();

            while (true) {


                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

                String dataFromClient = new String(dataInputStream.readUTF());
                if (dataFromClient.equals("close")){
                    break;
                }
                System.out.println("data came from client connected to port "+clientSocket.getPort()+": "+dataFromClient);
                String[] fullOp= dataFromClient.split(":");

                Integer dataCalcuated = calcData(Integer.parseInt(fullOp[1]), Integer.parseInt(fullOp[2]), fullOp[0]);

                System.out.println("data sent to client connected to port "+clientSocket.getPort()+": "+dataCalcuated);

                dataOutputStream.writeUTF(dataCalcuated.toString());


            }
            System.out.println("server will not accept new clients ");

            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static Integer calcData(Integer n1, Integer n2, String op) {

        switch (op) {
            case "+": {
                return n1 + n2;

            }
            case "-": {
                return n1 - n2;
            }
            case "*": {
                return n1 * n2;
            }
            case "/": {

                if (n2 != 0) {
                    return n1 / n2;
                }
                return Integer.MAX_VALUE;
            }
        }
    return Integer.MAX_VALUE;
    }


}
