class Calculator {
    
    int add(int a, int b){
        return a+b;
    }
    int subtract(int a, int b){
        return a - b;
    }
    int multiply(int a, int b){
        return a*b;
    }
    float divide(int a, int b){
        try{
            return a/b;
        } catch(ArithmeticException e){
            return Float.NaN;
        }
    }
}


public class Hello{
    public static void main(String args[]){
        Calculator calc = new Calculator();
        int a = 5;
        int b = 10;
        int c = calc.add(a, b);
        System.out.println(c);
    }
}
