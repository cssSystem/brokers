package sys.tem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sys.tem.model.entity.LoanApplicationEntity;

@Repository
public interface Repo extends JpaRepository<LoanApplicationEntity, Long> {
}
