package md.fin.homefinance.model;


import jakarta.persistence.*;
import md.fin.homefinance.model.enums.Status;


import java.util.Date;


@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category owner;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client ownClient;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sum")
    private double sum;

    private Status status;

    public Item() {
    }


    public Item(int id, String name, double cost, int quantity, double sum) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.sum = sum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        double forPercentage = 100;
        double discountnumber = getOwnClient().getDiscount();
        return getCost()*getQuantity()-getCost()*getQuantity()*discountnumber/forPercentage;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Category getOwner() {
        return owner;
    }

    public Client getOwnClient() {
        return ownClient;
    }

    public void setOwnClient(Client ownClient) {
        this.ownClient = ownClient;
    }

    public void setOwner(Category owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}



