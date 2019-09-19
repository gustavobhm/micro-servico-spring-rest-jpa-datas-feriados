package br.org.cremesp.datas.feriados.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.datas.feriados.entity.Feriado;
import br.org.cremesp.datas.feriados.exception.BadRequestException;
import br.org.cremesp.datas.feriados.service.FeriadoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/feriados")
public class FeriadoController {

	@Autowired
	private FeriadoService feriadoService;

	@GetMapping
	public List<Feriado> getAll() {
		return feriadoService.getAll();
	}

	@GetMapping("/{data}")
	public Feriado get(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date data) throws BadRequestException {
		return feriadoService.get(data);
	}

}
