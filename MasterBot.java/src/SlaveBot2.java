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

    public static void main(String [] args) {
        if (args.length == 4) {
            String input1 = args[0];
            String input2 = args[1];
            String input3 = args[2];
            String input4 = args[3];
            //System.out.println(input1);
            if (input1.equalsIgnoreCase("-h")) {
                if ((input2.equalsIgnoreCase("localhost")) || (input2.equalsIgnoreCase("127.0.0.1"))) {
                    if (input3.equalsIgnoreCase("-p")){


                        Date date = new Date();
                    long time = date.getTime();
                    Timestamp ts = new Timestamp(time);
                    String RegistrationDate = ts.toString().substring(0, 10);

                    String MasterName = args[1];
                    int port = Integer.parseInt(args[3]);
                    try {
                        Socket slave = new Socket(MasterName, port);



                    } catch (IOException e) {


                        e.printStackTrace();
                        //System.out.println("-1");
                        System.exit(-1);
                    }
                }
                else {System.out.println("The SlaveBot takes third argument as -p ");
                        System.out.println("-1");
                    System.exit(-1);
                }
            }
            else {System.out.println("The SlaveBot takes second argument as HostName/IP Address (localhost/127.0.0.1)");
                    System.out.println("-1");
                    System.exit(-1);
            }
            }
            else {System.out.println("The SlaveBot takes first arguments -h followed by HostName/IP Address");
                System.out.println("-1");
                System.exit(-1);
            }
        } else {
            System.out.println("The SlaveBot takes 4 arguments -h HostName/IP Address followed by -p and port Number");
            System.out.println("-1");
            System.exit(-1);
        }
    }
    }

