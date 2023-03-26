package com.decision.engine.Controller;


import com.decision.engine.Exceptions.LoanException;
import com.decision.engine.Model.Loan;
import com.decision.engine.Model.User;
import com.decision.engine.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/create", produces = {"application/json"}, consumes = { "application/json"})
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        if(loan.getLoanTaker() == null ){
            throw new LoanException("User Missing");
        }
        else if (loan.getLoanAmount() <2000 || loan.getLoanAmount() >10000){
            throw new LoanException("Loan amount must be between 2000 - 10000€");
        } else if (loan.getMonths()< 12 || loan.getMonths()> 60) {
            throw new LoanException("Month amount must be between 12 - 60");
        }

        return ResponseEntity
                .ok()
                .body(loanService.save(loan));
    }
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<Loan>> getAllLoans() {
        ;
        return ResponseEntity
                .ok()
                .body(loanService.findAllLoans());
    }

}
