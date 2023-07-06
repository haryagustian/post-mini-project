package org.hary.agustian.implement;

import org.hary.agustian.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface BookImplement {

    ResponseEntity<Map<String, Object>> inquiry(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Integer borrowerPrice,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "3") Integer size,
            @RequestParam(required = false, defaultValue = "id, desc") String[] sort
            );

    ResponseEntity<Book> insertBook();
    ResponseEntity<Book> updateBook();
    ResponseEntity<Book> deleteBook();

}
