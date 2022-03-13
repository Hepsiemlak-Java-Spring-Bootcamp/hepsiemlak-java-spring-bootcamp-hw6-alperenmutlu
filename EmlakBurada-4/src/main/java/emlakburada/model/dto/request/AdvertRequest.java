package emlakburada.model.dto.request;

import emlakburada.enums.RealEstateType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdvertRequest {

    private String title;
    private BigDecimal price;
    private String description;
    private boolean isActive;
    private RealEstateType realEstateType;
    private Integer userId;

}
