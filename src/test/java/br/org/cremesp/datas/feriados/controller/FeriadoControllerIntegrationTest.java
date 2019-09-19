package br.org.cremesp.datas.feriados.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.org.cremesp.datas.feriados.Application;
import br.org.cremesp.datas.feriados.entity.Feriado;
import br.org.cremesp.datas.feriados.repository.FeriadoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class FeriadoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private FeriadoRepository feriadoRepository;

	@Before
	public void init() throws ParseException {

		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Date data1 = formato.parse("2019-09-19");
		Feriado feriado1 = new Feriado(data1, "Feriado 1");
		feriadoRepository.saveAndFlush(feriado1);

		Date data2 = formato.parse("2019-09-20");
		Feriado feriado2 = new Feriado(data2, "Feriado 2");
		feriadoRepository.saveAndFlush(feriado2);

	}

	@Test
	public void getFeriados_ValidTest() throws Exception {
		mvc.perform(get("/feriados") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].data", is("2019-09-20"))).andExpect(jsonPath("$[1].nome", is("Feriado 2")));
	}

	@Test
	public void getByDataFeriado_ValidTest() throws Exception {
		mvc.perform(get("/feriados/2019-09-19") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.data", is("2019-09-19"))) //
				.andExpect(jsonPath("$.nome", is("Feriado 1")));
	}

	@Test
	public void getByDataFeriado_InvalidTest() throws Exception {
		mvc.perform(get("/feriados/2019-09-21") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}
