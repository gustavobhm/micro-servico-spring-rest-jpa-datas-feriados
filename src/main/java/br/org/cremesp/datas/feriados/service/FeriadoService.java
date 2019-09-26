package br.org.cremesp.datas.feriados.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.datas.feriados.entity.Feriado;
import br.org.cremesp.datas.feriados.repository.FeriadoRepository;

@Service
public class FeriadoService {

	@Autowired
	private FeriadoRepository feriadoRepository;

	public List<Feriado> getAll() {
		return feriadoRepository.findAllByOrderByDataAsc();
	}

	public Feriado get(Date data) {
		return feriadoRepository.findById(data).orElse(new Feriado());
	}

}
