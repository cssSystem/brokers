package sys.tem.model.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Embeddable
public class LoanApplication {
    private int loanAmount;
    private int loanTerm;
    private int usersIncome;
    private int creditLoad;
    private int creditRating;
}
