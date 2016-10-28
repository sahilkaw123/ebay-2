/**
        * Created by sahilkaw on 10/20/16.
        */
// server side programming - MasterBot

        import com.sun.prism.shader.Solid_ImagePattern_Loader;

        import java.io.*;
        import java.net.*;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.Arrays;
        import java.util.Scanner;
        import java.util.Date;
        import java.sql.Timestamp;
        import java.util.Scanner;
        import java.util.*;
        import java.net.InetAddress;


//A slave program which accepts the requests from the Master to connect to the ip address at a particular port
public class MasterBot extends Thread {
    private ServerSocket serverSocket;
    public static int cnt =0;
    public static String Ip_addr;
    public static arr  [] Objarr = new arr [200000];
    public static int count = 0;

    public MasterBot(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                String Ip_addr1 = Inet4Address.getLocalHost().getHostAddress().toString();
                //Listening on the port
              //  System.out.println();
              //  System.out.println(Inet4Address.getLocalHost().getHostAddress());
              //  System.out.println("Master is listening on the port " + serverSocket.getLocalSocketAddress());
              //  System.out.println("Master is listening on the port " + serverSocket.getLocalPort());

                //accepting the incoming clients
                    for(int i =0; i<200000;i++) {
                        Date date = new Date();
                        long time = date.getTime();
                        Timestamp ts = new Timestamp(time);
                        String RD = ts.toString().substring(0,10);

                        Socket Master = serverSocket.accept();

                        //System.out.println(Master);
                       // int port1 = serverSocket.getLocalPort();

                        String x = Integer.toString(i);
                        String client = "Slave" + x;
                        String remoteAddr = Master.getRemoteSocketAddress().toString();
                        System.out.println(remoteAddr);
                        String[] portNo1 = remoteAddr.split(":");
                        //System.out.println(portNo1[1]);
                        String portNo = portNo1[1];
                        //System.out.println("YYY" + portNo);
                        int portNoInt = Integer.parseInt(portNo);
                        Objarr[i] = new arr(client, portNoInt, RD);
                        //System.out.println(portNoInt);
                        //a[i] = portNoInt;

                        //String ip = "127.0.0.1";


                        Ip_addr = Ip_addr1;

                        String ip = Ip_addr;
                        String ipAddr = ip + Objarr[i].getX();
                        //System.out.println(Objarr[i].getName() +" " + ip+ " "+   Objarr[i].getX()+" " + Objarr[i].getDate());
                        count++;
                        Master.close();
                    }
                    /*System.out.println();
                    //Incoming client listening port
                    System.out.println("Connected to the port to the slave" + Master.getRemoteSocketAddress());
                    count++;
                    //Input message from the Client
                    System.out.println();

                    DataInputStream in = new DataInputStream(Master.getInputStream());

                    //Printing the message
                    System.out.println();
                    System.out.println("The input message " + in.readUTF());
                    System.out.println();


                    //Sending the message to the slave
                    DataOutputStream out = new DataOutputStream(Master.getOutputStream());

                    System.out.println("nnn" + Master.getLocalSocketAddress());

                    //Mesasge sent to the Slave from the Master
                    out.writeUTF("Connected"); // + Master.getLocalSocketAddress() + " to slave ");
                    System.out.println("total" + count);

                Master.close(); */

            } catch (IOException s) {
                s.printStackTrace();
                break;
            }

        }

    }

    public static void main (String [] args){
        try

        {

        if (args.length == 2) {
            String inpt = args[0];
            int port1 = Integer.parseInt(args[1]);

            // System.out.println(args.length);
            if (inpt.equalsIgnoreCase("-p")) {
                if(port1 > 1024) {

                    int port = Integer.parseInt(args[1]);
                    try {
                        Thread t = new MasterBot(port);
                        t.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    while (true) {
                        Scanner sc = new Scanner(System.in);

                        String command = sc.nextLine();
                        String[] cmnd = command.split(" ");
                        if (cmnd.length == 1) {
                            if (command.equalsIgnoreCase("list")) {
                                System.out.println(count);
                                for (int i = 0; i < count; i++) {
                                    //String ip = "127.0.0.1";
                                    String ip = Ip_addr;
                                    System.out.println(Objarr[i].getName() + " " + ip + " " + Objarr[i].getX() + " " + Objarr[i].getDate());
                                }
                            } else {
                                System.out.println("The input command is not correct, Do you want to enter list");
                            }

                        }
                        else{
                            if (cmnd[0].equalsIgnoreCase("connect")){
                                String Ip_addr1 = Inet4Address.getLocalHost().getHostAddress().toString();
                                if(cmnd[1].equalsIgnoreCase("all")){
                                    System.out.println("Connect all");
                                    String webName = cmnd[2];
                                    String portNoTrgt = cmnd[3];
                                    int trgtPNo = Integer.parseInt(portNoTrgt);
                                    System.out.println(webName);
                                    System.out.println(trgtPNo);


                                    for(int y = 0; y< count; y++){
                                        SocketConnection.connect(Objarr[y].getX(), trgtPNo, webName);

                                    }

                                }
                                else{


                                        Ip_addr = Ip_addr1;

                                    if(cmnd[1].equalsIgnoreCase(Ip_addr)){
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        int trgtPNo = Integer.parseInt(portNoTrgt);

                                        //System.out.println(Objarr[cnt].getX());
                                        SocketConnection.connect(Objarr[cnt].getX(), trgtPNo, webName);
                                        cnt++;
                                        //System.out.println(cnt);
                                        //System.out.println(Objarr[cnt].getX());
                                    }
                                    else {
                                        int sNamelen = cmnd[1].length();
                                        System.out.println(sNamelen);
                                        String slName = cmnd[1].substring(0,5);
                                        System.out.println(slName);
                                        String numb = cmnd[1].substring(5,sNamelen);

                                        int num = Integer.parseInt(numb);
                                        //System.out.println(num);
                                       // System.out.println(slName +  "xxx" + Objarr[0].getName() +" " +  count);
                                        if(slName.equalsIgnoreCase("slave") && (num < count)){
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            int trgtPNo = Integer.parseInt(portNoTrgt);

                                            for(int d = 0; d <count; d++){
                                                //System.out.println(cmnd[1] +  "xxx" + Objarr[d].getName());
                                                if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())){
                                                    int slavePortNo = Objarr[d].getX();
                                                    SocketConnection.connect(slavePortNo, trgtPNo, webName);
                                                }

                                            }
                                        }
                                        else{
                                            System.out.println("Slave name does not exist, Please chose a correct name from list");
                                        }

                                    }
                                }





                            }
                            else{
                                if(cmnd[0].equalsIgnoreCase("disconnect")){
                                    //

                                    String Ip_addr1 = Inet4Address.getLocalHost().getHostAddress().toString();
                                    if(cmnd[1].equalsIgnoreCase("all")){
                                        //System.out.println("disConnect all");
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        System.out.println(webName);
                                        System.out.println(trgtPNo);


                                        for(int y = 0; y< count; y++){
                                            SocketConnection.disconnect(Objarr[y].getX(), trgtPNo, webName);

                                        }

                                    }
                                    else{


                                        Ip_addr = Ip_addr1;

                                        if(cmnd[1].equalsIgnoreCase(Ip_addr)){
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            int trgtPNo = Integer.parseInt(portNoTrgt);

                                            //System.out.println(Objarr[cnt].getX());
                                            SocketConnection.disconnect(Objarr[cnt].getX(), trgtPNo, webName);
                                            cnt++;
                                            //System.out.println(cnt);
                                            //System.out.println(Objarr[cnt].getX());
                                        }
                                        else {
                                            int sNamelen = cmnd[1].length();
                                            System.out.println(sNamelen);
                                            String slName = cmnd[1].substring(0,5);
                                            System.out.println(slName);
                                            String numb = cmnd[1].substring(5,sNamelen);

                                            int num = Integer.parseInt(numb);
                                            //System.out.println(num);
                                            // System.out.println(slName +  "xxx" + Objarr[0].getName() +" " +  count);
                                            if(slName.equalsIgnoreCase("slave") && (num < count)){
                                                String webName = cmnd[2];
                                                String portNoTrgt = cmnd[3];
                                                int trgtPNo = Integer.parseInt(portNoTrgt);

                                                for(int d = 0; d <count; d++){
                                                    //System.out.println(cmnd[1] +  "xxx" + Objarr[d].getName());
                                                    if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())){
                                                        int slavePortNo = Objarr[d].getX();
                                                        SocketConnection.disconnect(slavePortNo, trgtPNo, webName);
                                                    }

                                                }
                                            }
                                            else{
                                                System.out.println("Slave name does not exist. Please chose a correct name from list");
                                            }

                                        }
                                    }




                                    //
                                }
                            }
                        }

                    }
                    }

                else{
                    System.out.println("The port number less than 1024 is not permitted by default, Please enter value greater than 1024");
                }
            }
            else{
                System.out.println("The arguments are not correct -p followed by port number");
            }
        }
        else{
            System.out.println("The MasterBot takes two arguments -p followed by port number ");
        }

        }catch (IOException s){
            s.printStackTrace();
        }

    }



}

class arr {
    private String name;
    private int x;
    private String Date;

    arr(String name, int x, String Date) {
        this.name = name;
        this.x = x;
        this.Date = Date;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public String getDate() {
        return Date;
    }
}