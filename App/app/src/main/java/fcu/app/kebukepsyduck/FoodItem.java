package fcu.app.kebukepsyduck;

public class FoodItem {
    private int imgID;
    private String FoodName;
    private int FoodPrice;

    public FoodItem(int imgID, String FoodName, int FoodPrice) {
        this.imgID = imgID;
        this.FoodName = FoodName;
        this.FoodPrice = FoodPrice;
    }

    public int getImgID() {
        return imgID;
    }

    public String getFoodName() {
        return FoodName;
    }

    public int getFoodPrice() {
        return FoodPrice;
    }
}
