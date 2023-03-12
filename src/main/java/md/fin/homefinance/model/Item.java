package md.fin.homefinance.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.fin.homefinance.model.enums.Typestatus;
import org.hibernate.annotations.DynamicUpdate;


import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {
    private static final String SEQ_NAME = "category_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

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



}



