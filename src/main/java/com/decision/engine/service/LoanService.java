package com.decision.engine.service;


import com.decision.engine.Exceptions.LoanException;
import com.decision.engine.Model.Loan;
import com.decision.engine.Model.User;
import com.decision.engine.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loan save(Loan loan){


        Integer creditScore = loan.getLoanTaker().getCreditModifier() / loan.getLoanAmount() * loan.getMonths();
        if(creditScore >= 1){
            loanRepository.save(loan);
            return loan;


        }
        throw new LoanException("UserData");



    }
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }
}
