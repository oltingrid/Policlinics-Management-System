
public class ItemCombo {
	private Long id;
	private String denumire;
	
	ItemCombo(Long id,String denumire){
		this.id=id;
		this.denumire=denumire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	
	public String toString() {
		return denumire;
	}
}
