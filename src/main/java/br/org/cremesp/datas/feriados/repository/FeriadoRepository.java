package br.org.cremesp.datas.feriados.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.datas.feriados.entity.Feriado;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, Date> {

	public List<Feriado> findAllByOrderByDataAsc();
}
