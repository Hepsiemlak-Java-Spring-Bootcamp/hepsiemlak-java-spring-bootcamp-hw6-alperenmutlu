package emlakburada.model.dto.reponse;

import emlakburada.enums.RealEstateType;
import emlakburada.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class AdvertResponse {

    private Integer advertNo;
    private String title;
    private String description;
    private User user;
    private RealEstateType realEstateType;
    private BigDecimal price;
    private LocalDate createdDate;
    private boolean isActive;

}
