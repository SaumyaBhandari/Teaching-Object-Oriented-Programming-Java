class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound.");
    }
    void eat() {
        System.out.println("Animal eats.");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks.");
    }
    @Override
    void eat() {
        System.out.println("Dog eats.");
    }
    void play() {
        System.out.println("Dog plays.");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows.");
    }
}

public class OverRiding {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound(); 
    }
}