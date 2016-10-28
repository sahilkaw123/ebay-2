/**
 * Created by sahilkaw on 10/26/16.
 */

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketConnection {
    int portNo;
    String webN;
    int trgtPNo;
    public static int a = 0;
    public static Map<Integer, Socket> map = new HashMap<Integer, Socket>();

    /**
     * @param args
     */

    /*public SocketConnection(int portNo, String webN){
        this.portNo = portNo;
        this.webN = webN;

    } */
    /*public static void main(String[] args) {
        // TODO Auto-generated method stub

        SocketConnection tl= new SocketConnection();
        //tl.connect();

    } */
    public static void connect(int portNo, int trgtPNo, String webN) {

        try {

            //String x = Inet4Address.getLocalHost().getHostAddress().toString();

            String x = Inet4Address.getLocalHost().getHostAddress().toString();
            InetAddress addr = InetAddress.getByName(x);
            System.out.println(addr);
            // String socket = "socket" + a;
            //map.put(portNo,socket);

            // System.out.println(socket);
            //System.out.println("XXX"+ map.get(portNo));
            Socket socket = new Socket(webN, trgtPNo, addr, portNo);
            System.out.println(socket);
            //a = a+1;
            map.put(portNo, socket);
            System.out.println("XXX" + map.get(portNo));
            System.out.println("Connected to:  " + socket.getRemoteSocketAddress());
            System.out.println("Listening on " + socket.getLocalSocketAddress());
            System.out.println("connected on " + socket.getLocalPort());
            System.out.println("connected on  " + socket.isConnected());
            System.out.println("connected on  " + socket.getLocalSocketAddress());
            System.out.println("Inet address" + socket.getInetAddress());

            // PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //writer.println("GET /");
            //String line;
            //while ((line = reader.readLine()) != null) {
            //  System.out.println(line);
            //socket.close();
            //socket.shutdownInput();
            //socket.shutdownOutput();

            //System.out.println("connected on  "+  socket.isConnected());

            //  }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(int portNo, int trgtPNo, String webN) {
        try {
        System.out.println("I am here");
        Socket x = map.get(portNo);

            x.close();
           System.out.println(x.isClosed());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

