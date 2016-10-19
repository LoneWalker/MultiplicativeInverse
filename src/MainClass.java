import com.sun.deploy.panel.ITreeNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by azhar on 10/18/16.
 */
public class MainClass {
    public static  void  main(String arg[]){
        //System.out.println("Hello world!!");

        int n=0;
        String file_name="input.txt";
        Polynomial mx= new Polynomial();
        Polynomial fx=new Polynomial();


        BufferedReader br=null;
        String line;

        try {
            /*
            File format:
            --------------------
            line1: n
            line2: coefficients of m(x) in one line starting from a0 to an separated by space e.g for a 3 degree polynomial m(x)= x^3+x+1 input would be 1 1 0 1
            line3: coefficients of f(x) in one line starting from a0 to an separated by space
             */

            br = new BufferedReader(new FileReader(file_name));

            line=br.readLine();
            n=Integer.parseInt(line);

            line=br.readLine();
            String coefficients[]=line.split(" ");
            mx.constructPolynomial(n,coefficients);
            System.out.println("bit stream of mx: "+ line);
            System.out.println("m(x) = "+mx);


            line=br.readLine();
            coefficients=line.split(" ");
            fx.constructPolynomial(n,coefficients);
            System.out.println("bit stream of fx: "+ line);
            System.out.println("f(x) = "+fx);



            MulInverse obj= new MulInverse();

            Polynomial f_inv_x=obj.inverse(mx,fx);

            try {
                br.close();
            }catch (Exception ex){
                System.out.println("Exception in buffer close:"+ex.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}