package br.org.cremesp.datas.feriados.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.datas.feriados.constantes.DataFeriadoEnum;
import br.org.cremesp.datas.feriados.entity.Feriado;
import br.org.cremesp.datas.feriados.exception.BadRequestException;
import br.org.cremesp.datas.feriados.repository.FeriadoRepository;

@Service
public class FeriadoService {

	@Autowired
	private FeriadoRepository feriadoRepository;

	public List<Feriado> getAll() {
		return feriadoRepository.findAllByOrderByDataAsc();
	}

	public Feriado get(Date data) throws BadRequestException {
		return feriadoRepository.findById(data)
				.orElseThrow(() -> new BadRequestException(DataFeriadoEnum.MSG_FERIADO_FIND_ERRO.getTexto()));
	}

}
