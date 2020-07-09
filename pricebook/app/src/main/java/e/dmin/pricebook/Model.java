package e.dmin.pricebook;

public class Model {
    private String modelNumber;
    private int price;
    public Model(){}
    public Model(String modelnumber,int rate)
    {
        this.modelNumber = modelnumber;
        this.price=rate;
    }
    public void setModelNumber(String modelnumber)
    {
        this.modelNumber=modelnumber;
    }
    public String getModelNumber()
    {
     return this.modelNumber;
    }
    public void setPrice(int rate)
    {
        this.price=rate;
    }
    public int getPrice()
    {
        return this.price;
    }
}
