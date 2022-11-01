package com.spring_boot.controller;


import com.spring_boot.model.Blog;
import com.spring_boot.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/blogRest")
public class BlogRestController {

    @Autowired
    private IBlogService blogService;

    @GetMapping(" ")
    public ResponseEntity<Page<Blog>> goPage(@PageableDefault(10) Pageable pageable,
                                             @RequestParam Optional<String> name,
                                             @RequestParam Optional<String> note) {

        String keyName = name.orElse("");
        String keyNote = note.orElse("");
        Page<Blog> blogPage = blogService.findAll(pageable, keyName, keyNote);
        if (!blogPage.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogPage, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Blog> goDetail(@PathVariable Integer id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Page<Void>> save(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}