package com.artur.lego.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final ICategoryRepository iCategoryRepository;

    Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    List<Category> findAll() {
        return iCategoryRepository.findAll();
    }
}
