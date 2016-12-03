package pl.ias.provider.second.model;

public class Adres {
	private String ulica;
	private Long numerDomu;
	private String miasto;

	public Long getNumerDomu() {
		return numerDomu;
	}

	public void setNumerDomu(Long numerDomu) {
		this.numerDomu = numerDomu;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

}
