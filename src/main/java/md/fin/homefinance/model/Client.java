package md.fin.homefinance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {
    private static final String SEQ_NAME = "category_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "company_name")
    private String companyName;
    @Column(name = "discount")
    private int discount;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "ownClient")
    private List<Item> items;

}