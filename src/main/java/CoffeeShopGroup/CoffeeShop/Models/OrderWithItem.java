package CoffeeShopGroup.CoffeeShop.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderWithItem {
    private ArrayList<orderItem> items;
    private Long id;
    private Date date;

    public OrderWithItem(ArrayList items, Long orderNumber, Date date) {
        this.items = items;
        this.id = orderNumber;
        this.date = date;
    }

    public ArrayList<orderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<orderItem> items) {
        this.items = items;
    }
    public OrderWithItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public OrderWithItem(ArrayList<orderItem> array, Long id) {
        this.items = array;
        this.id = id;
    }

    public int getSum() {

        int result =  items.stream().reduce(0, (subtotal, el) -> subtotal + (el.getPrice() * el.getQuantity()), Integer::sum);

        return result;
    }

    public String dateToString() {
        if (date != null) {
            int month = date.getMonth() + 1;
            int year = date.getYear() + 1900;
            return date.getDate() + "-" + month + "-" + year;
        } else {
            return "";
        }
    }
}
