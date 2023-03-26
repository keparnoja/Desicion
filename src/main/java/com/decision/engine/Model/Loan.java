package com.decision.engine.Model;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "loan")
public class Loan {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @Id
    private Long LoanId;
    @OneToOne
    private User loanTaker;
    private Integer loanAmount;
    private Integer Months;
}
