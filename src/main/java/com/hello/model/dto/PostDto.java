package com.hello.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

//    I can to use 'ModelMapper' dependence for that

    private Long id;

    private String title;

    private String content;

    private LocalDateTime dateTime;

    public static Post toPost(PostDto postDto){
        return new Post(postDto.getId(), postDto.getTitle(), postDto.getContent(), LocalDateTime.now());
    }
    public static PostDto fromPost(Post post){
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), LocalDateTime.now());
    }
}
