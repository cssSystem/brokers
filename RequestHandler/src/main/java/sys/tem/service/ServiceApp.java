package sys.tem.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import sys.tem.model.enumeration.CreditStatus;
import sys.tem.model.event.DecisionLoanApplicationEvent;
import sys.tem.model.event.LoanApplicationEvent;

@Service
public class ServiceApp {
    private final RabbitTemplate rabbitTemplate;

    public ServiceApp(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void processApplication(LoanApplicationEvent loanApplication) {
        DecisionLoanApplicationEvent decisionLoanApplicationEvent = new DecisionLoanApplicationEvent();
        decisionLoanApplicationEvent.setId(loanApplication.getId());
        if (loanApproval(loanApplication)) {
            decisionLoanApplicationEvent.setCreditStatus(CreditStatus.APPROVED);
        } else {
            decisionLoanApplicationEvent.setCreditStatus(CreditStatus.REFUSED);
        }

        rabbitTemplate.convertAndSend("loan-applications", decisionLoanApplicationEvent);
    }

    private boolean loanApproval(LoanApplicationEvent loanApplication) {
        int monthlyPayment = loanApplication.getLoanAmount()
                / loanApplication.getLoanTerm();
        if (monthlyPayment < loanApplication.getUsersIncome() * 0.5) {
            return true;
        }
        return false;
    }
}
