package com.cool.thing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThingApplicationTests {

	@Mock
	private ThingRepository thingRepository;

	@InjectMocks
	private ThingServiceImpl thingService;

	@Test
	void contextLoads() {
	}

	@Test
	void getAndSetThingId() {
		Thing cut = new Thing();
		Long myId = 1L;
		cut.setId(myId);
		assertEquals(myId, cut.getId());
	}

	@Test
	void getAndSetThingName() {
		Thing cut = new Thing();
		String name = "table";
		cut.setName(name);
		assertEquals(name, cut.getName());
	}

	@Test
	void getAndSetThingDescription() {
		Thing cut = new Thing();
		String myDescription = "table";
		cut.setDescription(myDescription);
		assertEquals(myDescription, cut.getDescription());
	}

	@Test
	void getAndSetThingQuantity() {
		Thing cut = new Thing();
		Long myQuantity = 10L;
		cut.setQuantity(myQuantity);
		assertEquals(myQuantity, cut.getQuantity());
	}

	@Test
	void allArgsConstructorThing() {
		Long id = 2L;
		String name = "Chair";
		String description = "Cool chair";
		Long quantity = 50L;
		Thing cut = new Thing(id, name, description, quantity);

		assertEquals(id, cut.getId());
		assertEquals(name, cut.getName());
		assertEquals(description, cut.getDescription());
		assertEquals(quantity, cut.getQuantity());

	}

	@Test
	void builderThing() {
		Long id = 2L;
		String name = "Chair";
		String description = "Cool chair";
		Long quantity = 50L;
		Thing cut = Thing.builder()
							.id(id)
							.name(name)
							.description(description)
							.quantity(quantity)
							.build();
		assertEquals(id, cut.getId());
		assertEquals(name, cut.getName());
		assertEquals(description, cut.getDescription());
		assertEquals(quantity, cut.getQuantity());
	}

	@Test
	void canSaveAThing() {
		Long id = 2L;
		String name = "Chair";
		String description = "Cool chair";
		Long quantity = 50L;
		Thing cut = Thing.builder()
							.id(id)
							.name(name)
							.description(description)
							.quantity(quantity)
							.build();
		given(thingRepository.findByName(name)).willReturn(Optional.empty());
        given(thingRepository.save(cut)).willReturn(cut);
        Thing savedThing = thingService.saveThing(cut);
        assertNotNull(savedThing);
	}
}
