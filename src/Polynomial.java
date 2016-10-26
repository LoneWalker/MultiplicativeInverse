/**
 * Created by azhar on 10/18/16.
 */

// we assume that coefficient a0 is stored in index 0 and highest coefficient is stored in index degree
public class Polynomial {


    public int degree;
    public PolynomialTerm[] polynomial;

    public Polynomial(){

    }

    public Polynomial(int degree){
        this.degree=degree;
        polynomial= new PolynomialTerm[degree+1];
        for (int i=0; i<=degree; i++){
            polynomial[i]= new PolynomialTerm(0,i);
        }
    }

    public Polynomial(Polynomial p){
        this.degree=p.degree;
        this.polynomial= new PolynomialTerm[degree+1];
        for (int i=0; i<=degree; i++){
            polynomial[i]= new PolynomialTerm(p.polynomial[i].ai,i);
        }
    }

    public void constructPolynomial(int degree, String[] coefficients){
        this.degree=degree;
        this.polynomial=new PolynomialTerm[degree+1];

        for (int i=0; i<=degree; i++){
            polynomial[i]= new PolynomialTerm(Integer.parseInt(coefficients[i]),i);

        }
    }



    /*
    returns
    0 if polynomial == p
    1 if polynomial > p
    -1 if polynomial < p
    */

    public int compare(Polynomial p){

        for (int i=degree; i>=0; i--){
            if (Math.abs(polynomial[i].ai) == Math.abs(p.polynomial[i].ai)) {
                continue;
            }else if (Math.abs(polynomial[i].ai) > Math.abs(p.polynomial[i].ai)){
                return 1;
            }else {
                return -1;
            }
        }
        return 0;

    }

    public void copy(Polynomial src){
        for (int i=degree; i>=0; i--){
            this.polynomial[i].ai=src.polynomial[i].ai;
        }
    }
    public int highestTerm(){

        for (int i=degree; i>=0; i--){
            if (polynomial[i].ai!=0)
                return i;
        }
        return 0;
    }

    private int numberOfTerms(){
        int terms=0;
        for (int i=degree; i>=0; i--){
            if (polynomial[i].ai!=0)
                terms++;
        }
        return terms;
    }

    public boolean isIdentity(){

        for (int i=degree; i>0; i--){
            if (polynomial[i].ai!=0)
                return false;
        }
        if (polynomial[0].ai ==0)
            return false;
        else
            return true;
    }

    public boolean isZero(){
        for (int i=degree; i>=0; i--){
            if (polynomial[i].ai!=0)
                return false;
        }
        return true;
    }


    public void updateCoefficient(int index, int newCoefficient){
        polynomial[index].ai=newCoefficient;
    }

    public int coefficientValue(int index){
        return polynomial[index].ai;
    }

    @Override
    public String toString(){

        StringBuffer stringBuffer = new StringBuffer();

        boolean isFirstTerm=true;
        for (int i=degree ; i>=0; i--){
            if (polynomial[i].ai!=0){
                if (i==0){
                    stringBuffer.append(" + 1");
                }else {
                    if (isFirstTerm){
                        stringBuffer.append("X^"+i);
                    }else {
                        stringBuffer.append(" + X^"+i);
                    }

                }
                isFirstTerm=false;


            }
        }


        return stringBuffer.toString();

    }

}