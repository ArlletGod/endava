package md.fin.homefinance.model;


import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name ="item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private int cost;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category owner;


    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;


    public Item() {
    }


    public Item(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Category getOwner() {
        return owner;
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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}



