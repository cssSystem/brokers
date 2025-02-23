package sys.tem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sys.tem.model.dto.ApplicationID;
import sys.tem.model.dto.ApplicationStatus;
import sys.tem.model.dto.LoanApplication;
import sys.tem.model.entity.LoanApplicationEntity;
import sys.tem.model.event.DecisionLoanApplicationEvent;
import sys.tem.model.event.LoanApplicationEvent;
import sys.tem.repository.Repo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditProcessingService {
    private final Repo repository;

    private final KafkaTemplate<String, LoanApplicationEvent> kafkaTemplate;

    public ApplicationID submitApplication(LoanApplication req) {
        LoanApplicationEntity loanApplicationEntity = new LoanApplicationEntity();
        loanApplicationEntity.setLoanApplication(req);

        LoanApplicationEntity application = repository.save(loanApplicationEntity);
        LoanApplicationEvent loanApplicationEvent = new LoanApplicationEvent(
                application.getId(),
                application.getLoanApplication().getLoanAmount(),
                application.getLoanApplication().getLoanTerm(),
                application.getLoanApplication().getUsersIncome()
        );

        kafkaTemplate.send("loan-applications", loanApplicationEvent);

        return new ApplicationID(application.getId());
    }

    public Optional<ApplicationStatus> getApplication(Long id) {
        Optional<LoanApplicationEntity> loanApplication = repository.findById(id);

        return loanApplication.map(loanApplicationEntity -> new ApplicationStatus(
                loanApplicationEntity.getStatus()
        ));
    }

    public void decisionTaken(DecisionLoanApplicationEvent decisionLoanApplicationEvent) {
        LoanApplicationEntity loanApplicationEntity = repository.findById(decisionLoanApplicationEvent.getId())
                .get();
        loanApplicationEntity.setStatus(decisionLoanApplicationEvent.getCreditStatus());
        repository.save(loanApplicationEntity);
    }
}
