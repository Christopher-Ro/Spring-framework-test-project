package com.homework.allrest.thing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThingServise {
    @Autowired
    private ThingRepository thingRepository;

    public Iterable<Thing> getThings() {
        return thingRepository.findAll();
    }

    public Thing getThing(Integer id) {
        return thingRepository
                .findById(id)
                .orElseThrow(() -> new ThingNotFoundException(id));
    }

    public void createThing(Thing thing) {
        thingRepository.save(thing);
    }

    public void updateThing(Thing newThing, Integer id) {
        Thing oldThing = thingRepository
                .findById(id)
                .orElseThrow(() -> new ThingNotFoundException(id));
        thingRepository.save(oldThing.update(newThing));
    }

    public void deleteThing(Integer id) {
        thingRepository.deleteById(id);
    }
}
