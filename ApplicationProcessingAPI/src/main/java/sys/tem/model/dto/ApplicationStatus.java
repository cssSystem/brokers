package sys.tem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sys.tem.model.enumeration.CreditStatus;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationStatus {
    private CreditStatus status;
}
