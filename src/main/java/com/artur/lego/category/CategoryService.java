package com.artur.lego.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    Category getCategoryById(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new CategoryNotFoundException("There is no category with the provided ID");
        }

        return categoryRepository.findById(id).get();
    }

    public Category getCategoryByName(String name) {
        if (categoryRepository.findByName(name).isEmpty()) {
            throw new CategoryNotFoundException("There is no category with name " + name);
        }

        return categoryRepository.findByName(name).get();
    }

    List<Category> getAllCategories() {
        if (categoryRepository.findAll().isEmpty()) {
            throw new CategoryNotFoundException("No categories found");
        }

        return categoryRepository.findAll();
    }

    public void addCategory(CategoryDto categoryDto) {
        categoryRepository.save(
                new Category(
                        categoryDto.getName()
                )
        );
    }

}
