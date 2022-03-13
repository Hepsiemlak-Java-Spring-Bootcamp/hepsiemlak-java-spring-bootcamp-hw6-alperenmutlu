package emlakburada.model;


import emlakburada.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column (name = "advertNo")
    private Integer advertNo;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="realEstateType")
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;

   // @ManyToOne
    //@JoinColumn(name="user_id")
    @OneToOne
    private User creatorUser;

    @Column(name="createdDate")
    private LocalDate createdDate = LocalDate.now();

    @Column(name="isActive")
    private boolean isActive;


}
