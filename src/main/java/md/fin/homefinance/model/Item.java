package md.fin.homefinance.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

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
    @JoinColumn(name="category_id",referencedColumnName = "id")
    private Category owner;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;





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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
