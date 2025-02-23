package sys.tem.model.event;

import lombok.*;
import sys.tem.model.enumeration.CreditStatus;

@Setter
@Getter
public class DecisionLoanApplicationEvent {
    private long id;
    private CreditStatus creditStatus;
}
