package com.university.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.university.dao.AccountingRepo;
import com.university.dao.BookRepo;
import com.university.dao.PaperRepository;
import com.university.entities.AccountingRecord;
import com.university.entities.Book;
import com.university.entities.ResultList;

@RestController
public class SearchController {
	 	
		@Autowired
		private PaperRepository paperRepository;
		@Autowired
		private BookRepo bookRepo;
		@Autowired
		private AccountingRepo accountingRepo ;
	
//		Search Handler
	@GetMapping("/search/{query}/{semester}/{batch}")
	public ResponseEntity<?> search(@PathVariable("query")String query,@PathVariable("semester")String semester,@PathVariable("batch")String batch){
		
		List<ResultList> resultLists=this.paperRepository.findByStudentNameContainingAndSemesterAndBatch(query,semester,batch);
		
		return ResponseEntity.ok(resultLists);
	}
	
	@GetMapping("/search-book/{query}")
	public ResponseEntity<?> searchBook(@PathVariable("query")String query){
		
		List<Book> books=this.bookRepo.findByTitleContaining(query);
		
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/search-fee/{query}/{semester}/{batch}")
	public ResponseEntity<?> searchFee(@PathVariable("query")String query,@PathVariable("semester")String semester,@PathVariable("batch")String batch){
		
		List<AccountingRecord> fee=this.accountingRepo.findByNameContaining(query,semester,batch);
		
		return ResponseEntity.ok(fee);
	}
}
