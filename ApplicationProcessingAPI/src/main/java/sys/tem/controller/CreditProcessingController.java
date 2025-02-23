package sys.tem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sys.tem.model.dto.ApplicationID;
import sys.tem.model.dto.ApplicationStatus;
import sys.tem.model.dto.LoanApplication;
import sys.tem.service.CreditProcessingService;

@RestController
@RequestMapping("/api/loan-app")
@RequiredArgsConstructor
public class CreditProcessingController {
    private final CreditProcessingService service;

    @PostMapping
    public ResponseEntity<ApplicationID> submit(@RequestBody LoanApplication req) {
        ApplicationID applicationID = service.submitApplication(req);
        return ResponseEntity.ok(applicationID);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationStatus> getStatus(@PathVariable Long id) {
        return service.getApplication(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
