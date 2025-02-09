package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoanApplicationController {

    private Map<Long, LoanApplication> loanApplications = new HashMap<>();
    private Long applicationIdCounter = 1L;

    // Show Loan Application Form
    @GetMapping("/apply")
    public String showApplicationForm(Model model) {
        model.addAttribute("loanApplication", new LoanApplication());
        return "apply";
    }

    // Process Loan Application Form
    @PostMapping("/submit")
    public String processApplication(@Valid @ModelAttribute LoanApplication loanApplication, 
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "apply";
        }

        // Loan approval logic
        if (loanApplication.getCreditScore() >= 700) {
            loanApplication.setStatus("Approved");
        } else if (loanApplication.getCreditScore() < 500) {
            loanApplication.setStatus("Rejected");
        } else {
            loanApplication.setStatus("Pending");
        }

        loanApplication.setId(applicationIdCounter++);
        loanApplications.put(loanApplication.getId(), loanApplication);

        return "redirect:/status/" + loanApplication.getId();
    }

    // View Loan Application Status
    @GetMapping("/status/{id}")
    public String viewStatus(@PathVariable Long id, Model model) {
        LoanApplication loanApplication = loanApplications.get(id);
        if (loanApplication == null) {
            return "error";
        }
        model.addAttribute("loanApplication", loanApplication);
        return "status";
    }
}

