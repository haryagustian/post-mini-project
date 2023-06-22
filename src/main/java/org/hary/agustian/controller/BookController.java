package org.hary.agustian.controller;

import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.model.Book;
import org.hary.agustian.model.Category;
import org.hary.agustian.repository.BookRepository;
import org.hary.agustian.repository.CategoryRepotisory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin("http://localhost:8080")
public class BookController {
    @Autowired
    private BookRepository bookRepotisory;

    @Autowired
    private CategoryRepotisory categoryRepotisory;


    @PostMapping("/{id}/categories")
    public ResponseEntity<Book> createBook(@PathVariable("id") Integer id, @RequestBody Book req){
        try {
            Book byTitle = bookRepotisory.findByTitle(req.getTitle());
            if (byTitle != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            Optional<Category> optionalCategory = categoryRepotisory.findById(id);
            if (optionalCategory.isEmpty())
                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);

            req.setCategory(optionalCategory.get());
            return new ResponseEntity<>(bookRepotisory.save(req), HttpStatus.CREATED);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        try {
            List<Book> all = bookRepotisory.findAll();
            if (all.isEmpty())
                throw new ResourceNotFoundException("No Data");
            return new ResponseEntity<>(all, HttpStatus.OK);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/categories")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") Integer id, @RequestBody Book req){
        try {
            Optional<Book> optionalBook = bookRepotisory.findById(req.getId());
            if (optionalBook.isEmpty())
                throw new ResourceNotFoundException("Not Found Book with ID = : " + id);

            Optional<Category> optionalCategory = categoryRepotisory.findById(id);
            System.out.println(optionalCategory.get());
            if (optionalCategory.isEmpty())
                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);

            if (req.getTitle() != null)
                optionalBook.get().setTitle(req.getTitle());

            if (req.getYear() != null)
                optionalBook.get().setYear(req.getYear());

            optionalBook.get().setCategory(optionalCategory.get());
            System.out.println(optionalCategory.get());
            return new ResponseEntity<>(bookRepotisory.save(optionalBook.get()), HttpStatus.OK);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Integer id){
        try {
            Optional<Book> byId = bookRepotisory.findById(id);
            if (byId.isEmpty())
                throw new ResourceNotFoundException("Not Found Book with ID = : " + id);

            bookRepotisory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
