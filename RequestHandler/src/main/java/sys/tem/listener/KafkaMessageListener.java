package sys.tem.listener;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sys.tem.model.event.LoanApplicationEvent;
import sys.tem.service.ServiceApp;

@Component
@AllArgsConstructor
public class KafkaMessageListener {
    private final ServiceApp serviceApp;

    @KafkaListener(topics = "loan-applications", groupId = "loan-applications-group")
    public void process(LoanApplicationEvent loanApplication) {
        if (loanApplication == null) {
            return;
        }
        serviceApp.processApplication(loanApplication);
    }

}
