package cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AATest {

	
	public static void main(String[] args) {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		list.add("aa");
		map.put("a", list);
		map.get("a").add("bb");
		ArrayList<String> lista = map.get("a");
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i));
		}
	}
}
