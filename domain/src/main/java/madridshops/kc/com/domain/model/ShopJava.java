package madridshops.kc.com.domain.model;


public final class ShopJava {
    private int id;
    private String name;
    private String address;

    public ShopJava(String name){
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Constructor anónimo = que el Init de Kotlin

    {
        ShopJava shop1 = new ShopJava("Tienda1");
        shop1.setAddress("adress");
        shop1.getAddress();
    }
}

/*
class HijaDeShop extends ShopJava {

    public HijaDeShop(String name){
        super(name);
    }

    @Override
    public void setAddress(String address) {
        //super.setAddress(address);
        // Podríamos hacer algo totalmente distinto
    }
}*/

// SOLID
