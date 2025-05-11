// Create a class MobilePhone with brand, model, and price. Keep price as a private variable and provide getPrice() and setPrice() methods to access and update it. Display phone details using a method.

class MobilePhone {
    String brand = "Apple";
    private String model;
    private Double price;

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Brand: " + this.brand);
        System.out.println("Model: " + this.model);
        System.out.println("Price: " + this.price);
    }
    
}

public class Main{
    public static void main(String[] args){
        
        MobilePhone apple = new MobilePhone();
        apple.setModel("iPhone 14");
        apple.setPrice(999.99);
        apple.displayDetails();
        
    }
}