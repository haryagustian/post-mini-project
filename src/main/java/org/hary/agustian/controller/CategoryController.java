package org.hary.agustian.controller;

import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.model.Category;
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

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin({"http://localhost:8080", "http://localhost:3000"})
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class.getSimpleName());
    @Autowired
    private CategoryRepotisory categoryRepotisory;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/paging")
    public ResponseEntity<Map<String, Object>> getAllCategoryWithPage(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id, desc") String[] sort) {

        LOGGER.info("===SORT INDEX 0 : {}, SORT INDEX 1 : {} ===", sort[0], sort[1]);

        try {
            List<Sort.Order> orders = new ArrayList<>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Category> categories = new ArrayList<Category>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Category> pageTuts;
            if (name == null)
                pageTuts = categoryRepotisory.findAll(pagingSort);
            else
                pageTuts = categoryRepotisory.findByNameContainingIgnoreCase(name, pagingSort);

            categories = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("categories", categories);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Category>> getAllCategoryWithSorted(@RequestParam(defaultValue = "id, desc") String[] sort) {
        LOGGER.info("===SORT INDEX 0 : {}, SORT INDEX 1 : {} ===", sort[0], sort[1]);

        try {
            List<Sort.Order> orders = new ArrayList<>();

            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Category> categories = categoryRepotisory.findAll(Sort.by(orders));

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category req){
        try {
            Category byName = categoryRepotisory.findByName(req.getName());
            if (byName != null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(categoryRepotisory.save(req), HttpStatus.CREATED);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        try {
            List<Category> all = categoryRepotisory.findAll();
            if (all.isEmpty())
                throw new ResourceNotFoundException("No Data");
            return new ResponseEntity<>(all, HttpStatus.OK);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable("id") Integer id, @RequestBody Category req){
        try {
            Optional<Category> byId = categoryRepotisory.findById(id);
            if (byId.isEmpty())
                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);

            if (req.getName() != null)
                byId.get().setName(req.getName());

            categoryRepotisory.save(byId.get());
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") Integer id){
        try {
            Optional<Category> byId = categoryRepotisory.findById(id);
            if (byId.isEmpty())
                throw new ResourceNotFoundException("Not Found Category with ID = : " + id);

            categoryRepotisory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception event){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
