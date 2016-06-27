package gUtil10.parser.text.dataManagement;

import java.util.HashMap;
import java.util.Map;

public class TPArray implements TPData {

	private Map<String, TPValue> data = new HashMap<>();

	public TPArray(String[] data) {

		for (String d : data) {

			if (d.contains(">")) {
				String[] spl = d.split(">");
				this.data.put(spl[0], new TPValue(spl[1]));
			} else {
				this.data.put(this.data.size() + "", new TPValue(d));
			}
		}
	}

	public TPValue get(String key) {
		return this.data.get(key);
	}

	public Map<String, TPValue> get() {
		return data;
	}

	public String toString() {
		return data.toString();
	}

}
