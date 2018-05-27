package br.com.mobibike.autenticacao.domain.main.enums;

public enum TypeStatus {
	A("Ativo"),
	I("Inativo");

	private String label;

	TypeStatus(final String label) {
		this.label = label;
	}

	public static TypeStatus inverterStatus(final TypeStatus status) {
		if (status == TypeStatus.A) {
			return TypeStatus.I;
		}

		return TypeStatus.A;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}
