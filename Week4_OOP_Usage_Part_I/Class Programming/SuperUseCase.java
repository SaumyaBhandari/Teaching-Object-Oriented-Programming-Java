class Animal {
    int legs;
    public Animal(int legs) {
        this.legs = legs;
    }
}

class Cat extends Animal{
    int tail;
    public Cat(int legs, int tail) {
        super(legs);
        this.tail = tail;
    }
}

public class SuperUseCase {
    public static void main(String[] args) {
        Animal a = new Animal(4);
        System.out.println("Animal has " + a.legs + " legs.");
    }    
}
