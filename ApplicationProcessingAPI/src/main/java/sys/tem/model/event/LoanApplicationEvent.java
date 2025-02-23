package sys.tem.model.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationEvent {
    private long id;
    private int loanAmount;
    private int loanTerm;
    private int usersIncome;
}
