package com.hello.controller;

import com.hello.model.Post;
import com.hello.model.dto.PostDto;
import com.hello.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/posts", params = {"page", "size"})
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        return ResponseEntity.ok(postService.getAll(page,size, Sort.Direction.DESC, "id").getContent());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
            return ResponseEntity.ok(postService.getById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody Post post) {

        return ResponseEntity.ok(postService.save(post));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,
                                                     @Valid @RequestBody Post post) {
        return ResponseEntity.ok(postService.update(id, post));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

