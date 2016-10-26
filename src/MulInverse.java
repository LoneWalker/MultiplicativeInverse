/**
 * Created by azhar on 10/18/16.
 */
public class MulInverse {


    public Polynomial inverse(Polynomial mx, Polynomial fx ){

        Polynomial inv_mx= new Polynomial(mx.degree);

        Polynomial divisor= fx;
        Polynomial dividend=new Polynomial(mx);
        Polynomial a_i= new Polynomial(mx.degree);
        Polynomial b_i= new Polynomial(mx.degree);
        Polynomial reminder= extnd_gcd(dividend, divisor, a_i, b_i);

        if (reminder==null){
            System.out.println("No valid inverse was found");
        }else {
            System.out.println("Inverse polynomial is: "+b_i);
        }

        return inv_mx;

    }

    // basically this function implements GCD algorithm
    private Polynomial extnd_gcd(Polynomial dividend, Polynomial divisor, Polynomial a_i, Polynomial b_i){
        Polynomial quotient= new Polynomial(dividend.degree);

        int comp_val=dividend.compare(divisor);

        if (comp_val== 0){
            return null;
        }else if (comp_val == -1){
            Polynomial swap= dividend;
            dividend=divisor;
            divisor=swap;
        }

        Polynomial reminder = mod(dividend,divisor,quotient);
        if (reminder.isZero()){
            return null;
        }else {
            if (reminder.isIdentity()){
                a_i.updateCoefficient(0,1);
                b_i.copy(quotient);
                return reminder;
            }else{
                Polynomial gcd= extnd_gcd(divisor, reminder, a_i, b_i);
                Polynomial temp_a_i=new Polynomial(b_i);
                b_i.copy(add(a_i, mul(b_i, quotient)));
                a_i.copy(temp_a_i);

                //System.out.println("printing b_i="+b_i);

                return gcd;
            }
        }

    }


    // implements long division. returns quotient in the 3rd parameter and reminder as a function return value
    private Polynomial mod(Polynomial dividend, Polynomial divisor, Polynomial quotient){
        Polynomial reminder = new Polynomial(dividend.degree);

        Polynomial temp_dividend=new Polynomial(dividend);


        int divisor_degree=divisor.highestTerm();
        int comp=temp_dividend.compare(divisor);

        while (comp==1){

            int temp_dividend_degree=temp_dividend.highestTerm();
            int diff=temp_dividend_degree-divisor_degree;
            quotient.updateCoefficient(diff,1);
            Polynomial divisor_times_quotient= mul_single(divisor, diff);
            reminder=diff(temp_dividend,divisor_times_quotient);
            temp_dividend=reminder;

            comp=temp_dividend.compare(divisor);


        }

        return reminder;
    }

    private Polynomial diff(Polynomial p1, Polynomial p2){
        Polynomial result = new Polynomial(p1.degree);
        for (int i=0; i<=p1.degree; i++){
            result.updateCoefficient(i,xor(p1.coefficientValue(i),p2.coefficientValue(i)));
        }

        return result;
    }
    private Polynomial mul_single(Polynomial p, int factor){

        Polynomial result= new Polynomial(p.degree);

        for (int i=p.degree; i>=0; i--){
            if (p.coefficientValue(i)!=0){
                result.updateCoefficient(i + factor, 1);
            }
        }
        return result;
    }

    Polynomial mul(Polynomial a, Polynomial b){
        Polynomial result= new Polynomial(a.degree);
        for (int i= a.degree; i>=0; i--){
            if (b.polynomial[i].ai!=0){
                result=add(result,mul_single(a,i));
            }
        }

        return result;

    }

    Polynomial add(Polynomial a, Polynomial b){
        Polynomial result= new Polynomial(a.degree);
        for (int i=0; i<= a.degree; i++){
            result.polynomial[i].ai=xor(a.polynomial[i].ai,b.polynomial[i].ai);
        }

        return result;
    }

    private int xor(int a, int b){
        if (Math.abs(a) == Math.abs(b)){
            return 0;
        }else
            return 1;
    }

}