package com.cool.thing;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThingServiceImpl implements ThingService {
    private ThingRepository thingRepository;

    public ThingServiceImpl(ThingRepository thingRepository){
        this.thingRepository = thingRepository;
    }

    public Thing saveThing(Thing thing) {
        Optional<Thing> theThing = thingRepository.findByName(thing.getName());
        if(theThing.isPresent()) {
            throw new InvalidConfigurationPropertyValueException("name", thing.getName(), "A thing named "+thing.getName()+" already exists");
        }
        return thingRepository.save(thing);
    }
}
