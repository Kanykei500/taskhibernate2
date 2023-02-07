package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "addresses")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="address_id_generator")
    @SequenceGenerator(name = "address_id_generator",sequenceName = "address_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "region_name")
    private String regionName;
    private String street;
    @Column(name = "home_number")
    private int homeNumber;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
    CascadeType.REFRESH},fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Country country;
    @OneToOne(mappedBy = "location",cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,},fetch = FetchType.EAGER)

    private Programmer programmer;

    public Address(String regionName, String street, int homeNumber) {
        this.regionName = regionName;
        this.street = street;
        this.homeNumber = homeNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber=" + homeNumber +
                '}';
    }
}
