package com.artur.lego;

import com.artur.lego.category.Category;
import com.artur.lego.category.CategoryDto;
import com.artur.lego.category.CategoryService;
import com.artur.lego.person.Person;
import com.artur.lego.person.PersonRepository;
import com.artur.lego.set.LegoSet;
import com.artur.lego.set.LegoSetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final LegoSetService legoSetService;
    private final PersonRepository personRepository;
    private final CategoryService categoryService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {

        try {
            initializeCategories();
        } catch (DataIntegrityViolationException exception) {
            log.info("Data already present. Data initialization aborted. Error message below:");
            log.info(exception.getMessage());
        }

        try {
            initializeLegoSets();
        } catch (DataIntegrityViolationException exception) {
            log.info("Data already present. Data initialization aborted. Error message below:");
            log.info(exception.getMessage());
        }

        try {
            initializePeople();
        } catch (DataIntegrityViolationException exception) {
            log.info("Data already present. Data initialization aborted. Error message below:");
            log.info(exception.getMessage());
        }


    }


    private void initializeLegoSets() {

        LegoSet set1 = new LegoSet(
                75372,
                "Clone Trooper & Battle Droid Battle Pack",
                215,
                categoryService.getCategoryByName("Star Wars")

        );
        LegoSet set2 = new LegoSet(
                10328,
                "Bouqet of Roses",
                822
        );
        LegoSet set3 = new LegoSet(
                75354,
                "Coruscant Guard Gunship",
                1083,
                categoryService.getCategoryByName("Star Wars")
        );
        LegoSet testSet1 = new LegoSet(
                1,
                "Test Set 1",
                1,
                categoryService.getCategoryByName("Test")
        );

        legoSetService.addLegoSet(set1);
        legoSetService.addLegoSet(set2);
        legoSetService.addLegoSet(set3);
        legoSetService.addLegoSet(testSet1);

    }

    private void initializeCategories() {
        CategoryDto city = new CategoryDto("City");
        CategoryDto starWars = new CategoryDto("Star Wars");
        CategoryDto test = new CategoryDto("Test");

        categoryService.addCategory(city);
        categoryService.addCategory(starWars);
        categoryService.addCategory(test);

    }

    private void initializePeople() {
        Person person = new Person(
                1L,
                "Test123",
                "test@test.pl",
                null,
                null,
                null
        );
        Person person2 = new Person(
                2L,
                "Test234",
                "test2@test.pl",
                null,
                null,
                null
        );

        personRepository.save(person);
        personRepository.save(person2);
    }

}
