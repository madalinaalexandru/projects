package presentation;

import java.lang.reflect.Field;
import java.util.Vector;

public class GUI {

	ClientWindow cw;
	ProductWindow pw;
	OrderWindow ow;
	
	public GUI() throws IllegalArgumentException, IllegalAccessException{
		
		cw = new ClientWindow();
		pw = new ProductWindow();
		ow = new OrderWindow();
	}
	
	public static Vector<String> getFields(Object object) throws IllegalArgumentException, IllegalAccessException{
		
		Field[] fields = object.getClass().getDeclaredFields();
		
		Vector<String> allFields = new Vector<String>();
		
		for(Field f : fields)
		{	
			f.setAccessible(true);
			allFields.add(f.getName());
		}
		
		return allFields;
	}
}
