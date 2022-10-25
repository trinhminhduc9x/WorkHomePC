package com.book.model.controller;

import com.book.model.Book;
import com.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("/book")
    public String goList(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "list";
    }

    @GetMapping("/detail/{id}")
    public String goDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "detail";
    }

    @PostMapping("/detail")
    public String borrow(Model model, @RequestParam Integer id) throws Exception {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new Exception();
        }
        if ((book.getCount()) == 0) {
            model.addAttribute("book", bookService.findById(id));
            model.addAttribute("msg", "Out Of Books");
            return "detail";
        }
        book.setCount(book.getCount() - 1);
        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/pay")
    public String goPay() {
        return "pay";
    }
    @PostMapping("/pay")
    public String pay(Model model, @RequestParam Integer id) throws Exception {
        Book book = bookService.findById(id);
        if (book == null) {
            throw new Exception();
        }
        if ((book.getCount()) == book.getTotal()) {
            model.addAttribute("msg1", "Enough books cannot be added");
            return "pay";
        }
        book.setCount(book.getCount() + 1);
        bookService.save(book);
        model.addAttribute("msg2", "Successful payment");
        return "redirect:/book";
    }

    @ExceptionHandler(value = Exception.class)
    public String error() {
        return "error";
    }

}
