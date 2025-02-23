package sys.tem.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sys.tem.model.event.DecisionLoanApplicationEvent;
import sys.tem.service.CreditProcessingService;

@Component
@RequiredArgsConstructor
public class RabbitMessageListener {
    private final CreditProcessingService service;

    @RabbitListener(queues = "loan-applications")
    public void handleCreditDecision(DecisionLoanApplicationEvent decisionLoanApplicationEvent) {
        service.decisionTaken(decisionLoanApplicationEvent);
    }

}
