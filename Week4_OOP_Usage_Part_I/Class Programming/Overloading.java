class Printer {

    void print() {
        System.out.println("Printing nothing");
    }

    void print(String text) {
        System.out.println("Printing text: " + text);
    }

    void print(int number) {
        System.out.println("Printing number: " + number);
    }

    void print(String text, int copies) {
        System.out.println("Printing " + copies + " copies of: " + text);
    }
    void print(String text, int copies, boolean isColor) {
        System.out.println("Printing " + copies + " copies of: " + text + " in color: " + isColor);
    }

}

public class Overloading {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.print(34);
    }
}
