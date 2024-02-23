package com.pinsoft.blogsite.resolver;


import com.pinsoft.blogsite.entity.Category;
import com.pinsoft.blogsite.entity.Post;
import com.pinsoft.blogsite.repository.CategoryRepository;
import com.pinsoft.blogsite.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Component
@RequiredArgsConstructor
public class PostResolver {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @QueryMapping
    public Post findByHeader(@Argument String header){
        return postRepository.findByHeader(header);
    }

    @MutationMapping
    public Post createPost(@Argument String header,
                           @Argument String content,
                           @Argument String base64img,
                           @Argument String categoryName
    ){
        Post post = new Post();
        post.setHeader(header);
        post.setContent(content);
        post.setBase64img(base64img);
        Optional<Category> category = categoryRepository.findByName(categoryName);
        post.setCategory(category.get());
        return postRepository.save(post);
    }
    @MutationMapping
    public String deletePost(@Argument Long id) {
        if(postRepository.findById(id).isPresent()){
            postRepository.deleteById(id);
            return "Post successfully deleted!";
        }
        else {
            return "Given id is not exist!";
        }
    }
}
