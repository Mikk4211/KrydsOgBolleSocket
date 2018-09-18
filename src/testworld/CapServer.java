package testworld;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CapServer {
    static final int PORT= 4444;

    public static void main(String[] args) throws  Exception {
        ServerSocket serverSocket = new ServerSocket(PORT,2);

        System.out.println("Server is running!");
        System.out.println("Waiting for 1 st player");

        Socket socket1 = serverSocket.accept();
        DataOutputStream dos1 = new DataOutputStream(socket1.getOutputStream());
        DataInputStream dis1 = new DataInputStream(socket1.getInputStream());

        String playername1 = dis1.readUTF();
        dos1.writeUTF("hello "+ playername1+" and welcome, youre player 1");

        System.out.println("Server accepted " + playername1);
        System.out.println("Server is waiting for next opponent");


        Socket socket2 = serverSocket.accept();
        System.out.println("socket2 accepted");
        DataOutputStream dos2 = new DataOutputStream(socket2.getOutputStream());
        DataInputStream dis2 = new DataInputStream(socket2.getInputStream());

        String playername2 = dis2.readUTF();
        dos2.writeUTF("hello "+ playername2+" and welcome, youre player 1");

        System.out.println("Server accepted " + playername2);


        dos1.writeUTF("you can start");
        dos2.writeUTF("you may begin");


        //MATHIAS KODE
        boolean done = false;
        while(!done) {
            byte messageType = dis1.readByte();
            byte messageType2 = dis2.readByte();


            try {
                String line = dis1.readUTF();
                System.out.println(line);
                done = line.equals("done");
                dos1.writeUTF("Serveren har modtaget dit svar : "+ line);

            } catch(Exception e) {
                System.out.println( "fejl");
                done = true;
            }

            try {
                String line2 = dis2.readUTF();
                System.out.println(line2);
                done = line2.equals("done");
                dos2.writeUTF("Serveren har modtaget dit svar : "+ line2);

            } catch(Exception e) {
                System.out.println( "fejl");
                done = true;
            }


        }




    }

}
