package main.EDRM.hybridFramework.helper.resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceHelper {

	public static String getResourcePath(String path) {
		//String basePath = System.getProperty("user.dir");
		
		Path basePath = Paths.get(".").normalize().toAbsolutePath();
		return basePath+"\\"+path;
	}
	
	
	
//	  public static void main(String [] args) { 
//		  System.out.println(getResourcePath("src\\testdata")); }
	 
	 
	
}
