package com.homework.allrest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class AllrestApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void loadMain() {
		AllrestApplication.main(new String[] {});
	}

	@Test
	@Transactional
	public void getToDoOneTest() throws Exception {
		mockMvc.perform(get("/api/todos/1"))
				.andExpect(status().isOk())
				.andExpect(content()
						.json("""
								{userId: 1,
								id: 1,
								title: 'delectus aut autem',
								completed: false}"""));
	}

	@Test
	public void getThingTest() throws Exception {
		mockMvc.perform(get("/api/things/1"))
				.andExpect(status().isOk())
				.andExpect(content()
						.json("{id: 1,title: 'My title',year: 2022}"));
	}

	@Test
	public void getNonExistingThingTest() throws Exception {
		mockMvc.perform(get("/api/things/0"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("Thing by id 0 not found."));
	}

	@Test
	public void getThingsTest() throws Exception {
		mockMvc.perform(get("/api/things"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].title").exists());
	}

	@Test
	public void getFront() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("look"))
				.andExpect(model().attributeExists("things", "todos"));
	}

	@Test
	public void getNonexistingToDo() throws Exception {
		mockMvc.perform(get("/api/todos/201"))
				.andExpect(status().isNotFound())
				.andExpect(content().string("ToDo by id 201 not found."));
	}

	@Test
	public void getCachedToDos() throws Exception {
		mockMvc.perform(get("/api/todos/cached"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}

	@Test
	@Transactional
	void testCreateThing() throws Exception {
		mockMvc.perform(post("/api/things")
				.content("{\"title\":\"Test\",\"year\":1000}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNumber());
	}

	@Test
	@Transactional
	void testDeleteThing() throws Exception {
		mockMvc.perform(delete("/api/things/1"))
				.andExpect(status().isNoContent());
	}

	@Test
	@Transactional
	void testUpdateThingTitle() throws Exception {
		mockMvc.perform(put("/api/things/1")
				.content("{\"title\":\"Test Update\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testUpdateNonExistingThingTitle() throws Exception {
		mockMvc.perform(put("/api/things/0")
				.content("{\"title\":\"Test Update\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	void testUpdateThingYear() throws Exception {
		mockMvc.perform(put("/api/things/1").content("{\"year\": 1999}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
