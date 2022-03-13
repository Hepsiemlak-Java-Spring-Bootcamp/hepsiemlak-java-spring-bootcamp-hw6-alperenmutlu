package emlakburada.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="advertNo")
    private Integer advertNo;

    @Column(name="phone")
    private String phone;

    @Column(name="total")
    private int total;

    @OneToOne
    private Address address;
}
