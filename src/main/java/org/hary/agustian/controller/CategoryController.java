package org.hary.agustian.controller;

import org.hary.agustian.exception.ResourceNotFoundException;
import org.hary.agustian.model.Category;
import org.hary.agustian.repository.CategoryRepotisory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin("http://localhost:8080")
public class CategoryController {

    @Autowired
    private CategoryRepotisory categoryRepotisory;

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
