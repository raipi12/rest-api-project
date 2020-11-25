package com.hello.service;

import com.hello.exception.NegativeParamException;
import com.hello.exception.NotFoundException;
import com.hello.model.Post;
import com.hello.model.dto.PostDto;
import com.hello.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // GetALL
    public Page<PostDto> getAll(int page, int size, Sort.Direction desc, String id){

        if (page < 0)
            throw new NegativeParamException("Page Number should be positive");
        else if (size < 1)
            throw new NegativeParamException("Page Size should be bigger than 0");

        Page<Post> posts = postRepository.findAll(PageRequest.of(page, size, desc, id));

        if (posts.isEmpty())
            throw new NotFoundException("Information about posts is empty");

        List<PostDto> postDto = posts.stream().map(PostDto::fromPost).collect(Collectors.toList());

        return new PageImpl<>(postDto);
    }
    //  GetByID
    public PostDto getById(Long id){
        if (id <= 0)
            throw new NegativeParamException("ID should be bigger or equal than 1");

        return PostDto.fromPost(postRepository.findById(id).orElseThrow(() -> new NotFoundException("We have not Post with this ID")));
    }
    //  SAVE
    public PostDto save(Post post) {
        return PostDto.fromPost(postRepository.save(post));
    }
    //  UPDATE
    public PostDto update(long id, Post post){
        Post newPost = PostDto.toPost(getById(id));
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        return PostDto.fromPost(postRepository.save(newPost));
    }
    //  DELETE
    public void delete(Long id){
        getById(id);
        postRepository.deleteById(id);
    }

}