package emlakburada.model;


import emlakburada.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="userType")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="photo")
    private String photo;

    @Column(name="bio")
    private String bio;


    //@OneToMany
   // private List<Message> messages;

    @OneToMany(mappedBy = "user")
    private List<FavoriteAdvert> favoriteAdverts;

   // @OneToMany(mappedBy = "user")
	//private List<Advert> publishedAdverts;
//	private List<Message> mesajKutusu;

    public User(UserType userType, String name, String email){
        super();
        this.userType = userType;
        this.name = name;
        this.email = email;
    }

}
