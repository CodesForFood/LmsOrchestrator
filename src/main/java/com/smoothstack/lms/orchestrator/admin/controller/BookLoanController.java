package com.smoothstack.lms.orchestrator.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.lms.orchestrator.admin.service.BookLoanService;
import com.smoothstack.lms.orchestrator.entity.BookLoan;

@RestController
@RequestMapping("/admin")
public class BookLoanController {
	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private BookLoanService loanService;
	
	@PutMapping(value = "/extend-due-date", consumes = { XML, JSON }, produces = { XML, JSON })
	public ResponseEntity<BookLoan> extendsDueDate(@RequestBody BookLoan loan){
		if(loan.getBookLoanId() == null || loan.getBookLoanId().getBook() == null
				|| loan.getBookLoanId().getBorrower() == null || loan.getBookLoanId().getBranch() == null
				|| loan.getDueDate() == null) {
			return new ResponseEntity<BookLoan>(HttpStatus.BAD_REQUEST);
		}
		else {			
			return loanService.extendDueDate(loan);	
		}		
	}
	
}
