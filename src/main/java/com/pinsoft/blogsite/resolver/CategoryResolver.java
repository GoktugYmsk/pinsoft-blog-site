package com.pinsoft.blogsite.resolver;

import com.pinsoft.blogsite.entity.Category;
import com.pinsoft.blogsite.entity.Post;
import com.pinsoft.blogsite.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Set;

@Component
@Controller
@RequiredArgsConstructor
public class CategoryResolver {
  @Autowired
  private CategoryRepository categoryRepository;

  @MutationMapping
  public Category createCategory(@Argument String name) {
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }

  @MutationMapping
  public String deleteCategory(@Argument Long id) {
    if (categoryRepository.findById(id).isPresent()) {
      categoryRepository.deleteById(id);
      return "Category successfully deleted!";
    } else {
      return "Given id does not exist!";
    }
  }

  @QueryMapping
  public Optional<Post> findByCategory(@Argument String name) {
    Optional<Category> categoryOptional = categoryRepository.findByName(name);
    if (categoryOptional.isPresent()) {
      Category category = categoryOptional.get();
      Set<Post> posts = category.getPostSet();
      if (!posts.isEmpty()) {
        return posts.stream().findFirst();
      }
    }
    return Optional.empty();
  }

}
