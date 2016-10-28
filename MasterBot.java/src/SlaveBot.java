/**
 * Created by sahilkaw on 10/20/16.
 */
import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.net.InetAddress;
import java.util.*;

public class SlaveBot {

    public static void main(String [] args){
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        String RegistrationDate = ts.toString().substring(0,10);
        System.out.println("Registration date: "+ RegistrationDate);
        String y = null;
        try {
            y = Inet4Address.getLocalHost().getHostAddress().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String MasterName = args[0];
        int port = Integer.parseInt(args[1]);
        try{


                //getting the Registration Date field
                Socket slave = new Socket(y, port);

                System.out.println(slave);
                //Socket slave = new Socket("192.168.0.13", port);

              /*  System.out.println();
                System.out.println("Slave is connected to " + MasterName + " on port " + port);

                System.out.println();
                System.out.println("My address " + slave.getLocalSocketAddress());
                System.out.println();


                System.out.println("Connected to master " + slave.getRemoteSocketAddress() + " registered at time " + RegistrationDate);
                System.out.println();

                OutputStream writeToMaster = slave.getOutputStream();
                DataOutputStream out = new DataOutputStream(writeToMaster);

                //Sending the
                out.writeUTF("I am registered at  " + slave.getLocalSocketAddress());

                InputStream readFromMaster = slave.getInputStream();
                DataInputStream in = new DataInputStream(readFromMaster);
                System.out.println();
                //Reading the response from Master
                // System.out.println(in.readUTF().toString());
                String x = in.readUTF().toString();
                System.out.println(x);
                if (x.equalsIgnoreCase("connected")) {
                    System.out.println("I am here");
                } else {
                    System.out.println("Not here");
                }

           //}
            */
            //slave.close();
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
}
