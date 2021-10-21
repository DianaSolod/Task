public class Application_Software extends Software{
    private String requires;

    public Application_Software(){
        super();
        requires = "";
    }

    public Application_Software(String name, int size, int price, String type, String requires){
        super(name, size, price, type);
        this.requires = requires;
    }

    public String getRequires(){return requires;}

    public void setRequires(String requires){this.requires = requires;}

    @Override
    public void show(){
        super.show();
        System.out.println("requires: " + this.requires);
    }
}
