package org.hary.agustian.controller;

import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.entity.Book;
import org.hary.agustian.entity.Category;
import org.hary.agustian.repository.BookRepository;
import org.hary.agustian.repository.CategoryRepotisory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@RestController
//@RequestMapping("/api/v1/books")
//@CrossOrigin({"http://localhost:8080", "http://localhost:3000"})
public class BookController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class.getSimpleName());
//    @Autowired
//    private BookRepository bookRepotisory;
//
//    @Autowired
//    private CategoryRepotisory categoryRepotisory;
//
//
//    private Sort.Direction getSortDirection(String direction) {
//        if (direction.equals("asc")) {
//            return Sort.Direction.ASC;
//        } else if (direction.equals("desc")) {
//            return Sort.Direction.DESC;
//        }
//
//        return Sort.Direction.ASC;
//    }
//
//    @GetMapping("/paging")
//    public ResponseEntity<Map<String, Object>> getAllBookWithPage(
//            @RequestParam(required = false) String title,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size,
//            @RequestParam(defaultValue = "id, desc") String[] sort) {
//
//        LOGGER.info("===SORT INDEX 0 : {}, SORT INDEX 1 : {} ===", sort[0], sort[1]);
//
//        try {
//            List<Sort.Order> orders = new ArrayList<>();
//
//            if (sort[0].contains(",")) {
//                // will sort more than 2 fields
//                // sortOrder="field, direction"
//                for (String sortOrder : sort) {
//                    String[] _sort = sortOrder.split(",");
//                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
//                }
//            } else {
//                // sort=[field, direction]
//                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
//            }
//
//            List<Book> books = new ArrayList<>();
//            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
//
//            Page<Book> pageTuts;
//            if (title == null)
//                pageTuts = bookRepotisory.findAll(pagingSort);
//            else
//                pageTuts = bookRepotisory.findByTitleContainingIgnoreCase(title, pagingSort);
//
//            books = pageTuts.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("categories", books);
//            response.put("currentPage", pageTuts.getNumber());
//            response.put("totalItems", pageTuts.getTotalElements());
//            response.put("totalPages", pageTuts.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/sorted")
//    public ResponseEntity<List<Book>> getAllBookWithSorted(@RequestParam(defaultValue = "id, desc") String[] sort) {
//
//        LOGGER.info("===SORT INDEX 0 : {}, SORT INDEX 1 : {} ===", sort[0], sort[1]);
//
//        try {
//            List<Sort.Order> orders = new ArrayList<>();
//
//            if (sort[0].contains(",")) {
//                for (String sortOrder : sort) {
//                    String[] _sort = sortOrder.split(",");
//                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
//                }
//            } else {
//                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
//            }
//
//            List<Book> books = bookRepotisory.findAll(Sort.by(orders));
//
//            if (books.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(books, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/{id}/categories")
//    public ResponseEntity<Book> createBook(@PathVariable("id") Integer id, @RequestBody Book req){
//        try {
//            Book byTitle = bookRepotisory.findByTitle(req.getTitle());
//            if (byTitle != null)
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//            Optional<Category> optionalCategory = categoryRepotisory.findById(id);
//            if (optionalCategory.isEmpty())
//                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);
//
//            req.setCategory(optionalCategory.get());
//            return new ResponseEntity<>(bookRepotisory.save(req), HttpStatus.CREATED);
//        }catch (Exception event){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Book>> getAllBook(){
//        try {
//            List<Book> all = bookRepotisory.findAll();
//            if (all.isEmpty())
//                throw new ResourceNotFoundException("No Data");
//            return new ResponseEntity<>(all, HttpStatus.OK);
//        }catch (Exception event){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}/categories")
//    public ResponseEntity<Book> updateBookById(@PathVariable("id") Integer id, @RequestBody Book req){
//        try {
//            Optional<Book> optionalBook = bookRepotisory.findById(req.getId());
//            if (optionalBook.isEmpty())
//                throw new ResourceNotFoundException("Not Found Book with ID = : " + id);
//
//            Optional<Category> optionalCategory = categoryRepotisory.findById(id);
//            System.out.println(optionalCategory.get());
//            if (optionalCategory.isEmpty())
//                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);
//
//            if (req.getTitle() != null)
//                optionalBook.get().setTitle(req.getTitle());
//
//            if (req.getYear() != null)
//                optionalBook.get().setYear(req.getYear());
//
//            optionalBook.get().setCategory(optionalCategory.get());
//            System.out.println(optionalCategory.get());
//            return new ResponseEntity<>(bookRepotisory.save(optionalBook.get()), HttpStatus.OK);
//        }catch (Exception event){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable("id") Integer id){
//        try {
//            Optional<Book> byId = bookRepotisory.findById(id);
//            if (byId.isEmpty())
//                throw new ResourceNotFoundException("Not Found Book with ID = : " + id);
//
//            bookRepotisory.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception event){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
