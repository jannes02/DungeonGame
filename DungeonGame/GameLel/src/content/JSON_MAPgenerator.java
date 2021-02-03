package content;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class JSON_MAPgenerator {

	@SuppressWarnings("unchecked")
	public static void non(String[] args) {
		
		JSONObject ja = new JSONObject();
		
		
	    //288 512
		int k = 0;
		for (int i = 0; i < 18; i++) {

			for (int j = 0; j < 32; j++) {
				
				JSONObject jb = new JSONObject();
				jb.put("x", j);
				jb.put("y", i);
				jb.put("w", 16);
				jb.put("h", 16);
				jb.put("type", "grass");
				
				ja.put("MapTile" + k, jb);
				System.out.println(jb);
				k++;
				
			}
		}
		
	    FileWriter file;
		try {
			file = new FileWriter("files/test.json");
			file.write(ja.toJSONString());
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	}

	

}
