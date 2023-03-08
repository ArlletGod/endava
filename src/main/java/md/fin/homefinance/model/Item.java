package md.fin.homefinance.model;


import jakarta.persistence.*;
import md.fin.homefinance.model.enums.Typestatus;
import org.hibernate.annotations.DynamicUpdate;


import java.util.Date;


@Entity
@Table(name = "item")
@DynamicUpdate
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

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;



    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "sum_field")
    private double sum;



@Enumerated(EnumType.ORDINAL)
private Typestatus typestatus;


    public Item() {
    }

    public Item( String name, double cost, int quantity, double sum) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.sum = sum;


    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
       return (getProduct().getPrice()*getQuantity())-((getProduct().getPrice()*getQuantity())*getOwnClient().getDiscount()/100);
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

    public Typestatus getTypestatus() {
        return typestatus;
    }

    public void setTypestatus(Typestatus typestatus) {
        this.typestatus = typestatus;
    }


}



