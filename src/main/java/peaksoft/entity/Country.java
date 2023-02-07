package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.EnumCountry;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="country_id_generator")
    @SequenceGenerator(name = "country_id_generator",sequenceName = "county_seq",
    allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumCountry country;
    private String description;
    @OneToMany(mappedBy = "country",cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
    private List<Address>addresses;

    public Country(EnumCountry country, String description) {
        this.country = country;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country=" + country +
                ", description='" + description + '\'' +
                '}';
    }
}
