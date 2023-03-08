package md.fin.homefinance.model;


import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "product")
@DynamicUpdate
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private int price;

    @OneToMany(mappedBy = "product")
    private List<Item> items;



    public Product() {
    }

    public Product(int id, String productName, int price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  productName ;
    }
}
