package com.artur.lego;

import com.artur.lego.set.LegoSet;
import com.artur.lego.set.LegoSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final LegoSetService legoSetService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData() {

        initializeLegoSets();

    }


    private void initializeLegoSets() {

        LegoSet set1 = new LegoSet(
                75372,
                "Clone Trooper & Battle Droid Battle Pack",
                215
        );
        LegoSet set2 = new LegoSet(
                10328,
                "Bouqet of Roses",
                822
        );
        LegoSet testSet1 = new LegoSet(
                1,
                "Test Set 1",
                1
        );

        legoSetService.addLegoSet(set1);
        legoSetService.addLegoSet(set2);
        legoSetService.addLegoSet(testSet1);

    }

}
