package com.artur.lego.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void shouldReturnListOfCategories() {
//        given
        Category category1 = new Category(
                1L,
                "City"
        );
        Category category2 = new Category(
                2L,
                "Star Wars"
        );
//        when
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));
//        then
        assertEquals(List.of(category1, category2), categoryService.getAllCategories());
    }

    @Test
    public void shouldThrowExceptionIfNoCategoriesExist() {
//        when
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of());
//        then
        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getAllCategories());
    }

    @Test
    public void shouldReturnCategoryWithGivenId() {
//        given
        Category category1 = new Category(
                1L,
                "City"
        );
//        when
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category1));
//        then
        assertEquals(category1, categoryService.getCategoryById(1L));
    }

    @Test
    public void shouldThrowExceptionIfNoCategoryWithGivenId() {
//        when
        Mockito.when(categoryRepository.findById(any())).thenReturn(Optional.empty());
//        then
        assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getCategoryById(any()));
    }

}