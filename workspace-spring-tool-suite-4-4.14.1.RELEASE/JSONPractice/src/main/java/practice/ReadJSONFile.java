package practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJSONFile {
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("D:JSONEXAMPLE.json"));

		String getFirstNamer = (String) jo.get("firstName");
		System.out.println(getFirstNamer);

		String pincode = (String) jo.get("state");
		System.out.println(pincode);

		Map m = ((Map) jo.get("address"));

		Iterator<Map.Entry> itr = m.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry pair = itr.next();
			//System.out.println(pair.getKey() + " " + pair.getValue());
			if(pair.getKey().equals("State"))
			{
				System.out.println("state is : "+pair.getValue());
			}
			
		}
		
	}
}
