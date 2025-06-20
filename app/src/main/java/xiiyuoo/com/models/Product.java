package xiiyuoo.com.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int cate_id;
    private String description;
    private int image_id;
    private String imageLink;

    public Product() {
    }

    public Product(int id, String name, int quantity, double price, int cate_id, String description, String imageLink) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cate_id = cate_id;
        this.description = description;
        this.imageLink = imageLink; // Khởi tạo imageLink
    }

    // Getter cho imageLink
    public String getImageLink() {
        return imageLink;
    }

    // Setter cho imageLink
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    public Product(int id, String name, int quantity, double price, String description, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image_id = image_id;
    }

    @NonNull
    @Override
    public String toString() {
        return id+"\t"+name+"\t"+price;
    }

    public Product(int id, String name, int quantity, double price, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_id = image_id;
    }

    // ✅ Thêm các phương thức getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getCate_id() {
        return cate_id;
    }

    public String getDescription() {
        return description;
    }

    public int getImage_id() {
        return image_id;
    }


}

