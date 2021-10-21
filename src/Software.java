public class Software {
    private String name;
    private int size;
    private int price;
    private String type;

    public Software(){
        name="";
        size=0;
        price=0;
        type = "";
    }
    public Software(String name, int size, int price, String type) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.type = type;
    }

    public String getName(){return name;}
    public int getSize() {return size;}
    public int getPrice() {return price;}
    public String getType(){return type;}

    public void setName(String name){this.name = name;}
    public void setSize(int size){this.size = size;}
    public void setPrice(int price){this.price = price;}
    public void setType(String type){this.type = type;}

    public void show(){
        System.out.println("name: " + this.name);
        System.out.println("size: " + this.size);
        System.out.println("price: " + this.price);
        System.out.println("type: " + this.type);
    }
}
