package br.com.mobibike.transparencia.domain.relatorio.beans;

import io.swagger.annotations.ApiModelProperty;

public class RegiaoRelatorio {

	String regiao;
	@ApiModelProperty(notes = "Quantidade total de KM percorridos por ciclistas")
	String totalKmRodados;
	@ApiModelProperty(notes = "Valor em Reais, economizado no sistema público de transporte, repassado para os ciclistas")
	String totalTarifasEonomizado;


	private RegiaoRelatorio(String regiao, String totalKmRodados, String totalTarifasEonomizado) {
		this.regiao = regiao;
		this.totalKmRodados = totalKmRodados;
		this.totalTarifasEonomizado = totalTarifasEonomizado;
	}

	public static RegiaoRelatorio of(String regiao, String totalKmRodados, String totalTarifasEonomizado) {
		return new RegiaoRelatorio(regiao, totalKmRodados, totalTarifasEonomizado);
	}

	public String getRegiao() {
		return regiao;
	}

	public String getTotalKmRodados() {
		return totalKmRodados;
	}

	public String getTotalTarifasEonomizado() {
		return totalTarifasEonomizado;
	}
}
