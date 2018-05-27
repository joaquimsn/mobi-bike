package br.com.mobibike.transparencia.domain.relatorio;

import br.com.mobibike.transparencia.domain.relatorio.beans.RegiaoRelatorio;

public class RelatorioService {

	private RelatorioDAO relatorioDao;

	public String creditosPagosPorPeriodo() {
		return "{\"total\": \"77.074,54\"}";
	}

	public RegiaoRelatorio resumoRegiao(String regiao) {
		return RegiaoRelatorio.of("norte", "50.000", "R$ 18.547,23");
	}
}

