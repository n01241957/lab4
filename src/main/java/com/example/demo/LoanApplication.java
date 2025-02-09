package com.example.demo;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Applicant name is required")
    private String applicantName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "Loan amount must be greater than 0")
    private Double loanAmount;

    @Min(value = 300, message = "Credit score must be at least 300")
    @Max(value = 850, message = "Credit score cannot exceed 850")
    private Integer creditScore;

    private String status = "Pending";  // Default status

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(Double loanAmount) { this.loanAmount = loanAmount; }

    public Integer getCreditScore() { return creditScore; }
    public void setCreditScore(Integer creditScore) { this.creditScore = creditScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

