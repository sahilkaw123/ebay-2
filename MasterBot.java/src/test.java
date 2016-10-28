/**
 * Created by sahilkaw on 10/21/16.
 */
/**
 * Created by sahilkaw on 10/21/16.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class test {

    public static void main(String[] args) {

        test obj = new test();

        String domainName = " google.com";
        String x = args[0];
        //in mac oxs
        String command = x + domainName;
        System.out.println(command);

        //in windows
        //String command = "ping -n 3 " + domainName;

        String output = obj.executeCommand(command);

        System.out.println(output);

    }

    private String executeCommand(String command) {
        System.out.println("XX");
        StringBuffer output = new StringBuffer();

        Process p;
        try {

            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = " ";
            System.out.println("XXX");
            while ((line = reader.readLine())!= null) {

                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}
