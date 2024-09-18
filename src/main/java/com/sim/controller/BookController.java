package com.sim.controller;

import com.sim.model.Book;
import com.sim.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {
    @Autowired
    BookRepository bookRepository;
    private final String strDoesNotExist = "Book with id: {} does not exist!";

    @GetMapping
    public List<Book> getAllBookRecords() {
        return bookRepository.findAll();
    }

    @GetMapping(value = "{bookId}")
    public Book getBookById(@PathVariable(value="bookId") Long bookId) throws Exception {
        Optional<Book> bookOptional = getBookByIdFromBackEnd(bookId);

        return (Book) bookOptional.get();
    }

    private Optional<Book> getBookByIdFromBackEnd(Long bookId) throws ChangeSetPersister.NotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isEmpty()) {

            log.info(strDoesNotExist, bookId);
            throw new ChangeSetPersister.NotFoundException();
        }
        return bookOptional;
    }

    @PostMapping()
    public Book createBookRecord(@RequestBody Book bookRecord) {
        return (Book) bookRepository.save(bookRecord);
    }

    @PutMapping()
    public Book updateBookRecord(@RequestBody  Book bookRecord) throws Exception {
        if (bookRecord == null || bookRecord.getBookId() == null) {
            log.info("BookRecord or ID must not be null!");
            throw new ChangeSetPersister.NotFoundException();
        }

        Optional<Book> bookOptional = getBookByIdFromBackEnd(bookRecord.getBookId());

        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            existingBook.setName(bookRecord.getName());
            existingBook.setRating(bookRecord.getRating());
            existingBook.setSummary(bookRecord.getSummary());

            return (Book) bookRepository.save(existingBook);
        }

        return null;
    }

    @DeleteMapping(value = "{bookId}")
    public void deleteBookById(@PathVariable(value="bookId") Long bookId) throws Exception {
        Optional<Book> bookOptional = getBookByIdFromBackEnd(bookId);

        bookRepository.deleteById(bookId);
    }
}
