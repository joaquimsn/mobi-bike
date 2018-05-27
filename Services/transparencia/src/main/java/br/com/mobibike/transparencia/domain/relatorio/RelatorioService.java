package br.com.mobibike.transparencia.domain.relatorio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.mobibike.transparencia.domain.relatorio.beans.RegiaoRelatorio;

public class RelatorioService {

	private RelatorioDAO relatorioDao;

	public ObjectNode creditosPagosPorPeriodo() {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode data = objectMapper.createObjectNode();

		return null;
	}

	public RegiaoRelatorio resumoRegiao(String regiao) {
		return RegiaoRelatorio.of("norte", "50.000", "R$ 18.547,23");
	}
}

