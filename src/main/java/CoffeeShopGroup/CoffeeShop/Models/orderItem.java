package CoffeeShopGroup.CoffeeShop.Models;

public class orderItem {
    private String title, full_text;
    private int quantity;
    private int price;



    public orderItem() {}

    public orderItem(String title, String full_text, int quantity, int price) {
        this.title = title;
        this.full_text = full_text;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
