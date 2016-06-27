package gUtil10.parser.text.dataManagement;

public class TPValue implements TPData{

	private String data;

	public TPValue(String value) {
		this.data = value;
	}

	public String asString() {
		return data;
	}

	public String toString() {
		return data;
	}

	public double asNumber() {
		return Double.parseDouble((String) data);
	}

}
