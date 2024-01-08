package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtils {
	
	
	
	static FileInputStream ExcelFile;
	static DataFormatter df = new DataFormatter();
	static Workbook ExcelWBook;
	static Cell cell,cellKey,cellValue;
	static Row row;
	
	

	/**
	 * This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	 * @param filePath 	: Absolute folder path where excel file is placed
	 * @param fileName 	: Name of excel file with extension e.g. ExcelFile.xls or ExcelFile.xlsx
	 * @param SheetName : SheetName from where data is to be read or in which data to be writen
	 * @return 			: Sheet object gets return
	 * @throws Exception
	 */
		public  Sheet setExcelFile(String filePath,String fileName,String SheetName) throws Exception {
			Sheet ExcelWSheet;
			try {
				ExcelFile = new FileInputStream(filePath+fileName); 			// Open the Excel file 
				/*Check condition if the file is xlsx or xls file*/
			    String fileExtensionName = fileName.substring(fileName.indexOf("."));
			    if(fileExtensionName.equals(".xlsx")){
			    	ExcelWBook = new XSSFWorkbook(ExcelFile); //If xlsx file then create object of XSSFWorkbook class
			    }
			    else if(fileExtensionName.equals(".xls")){
			    	ExcelWBook = new HSSFWorkbook(ExcelFile); //If xls file then create object of XSSFWorkbook class
			    }
				// Access the required test data sheet
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
			}
			catch (Exception e){
				System.out.println("setExcelFile : " + e);
				throw (e);
			}
			return ExcelWSheet;
		}
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as row num and Col num
	 * @param filePath  : Absolute folder path where excel file is placed
	 * @param fileName  : Name of excel file with extension e.g. ExcelFile.xls or ExcelFile.xlsx
	 * @param SheetName : SheetName from where data is to be read 
	 * @return 			: Cell Value gets return
	 * @throws Exception
	 */
	public String[][] readExcel(String filePath,String fileName,String SheetName) throws Exception{
		Sheet ExcelWSheet;
		String CellData[][];
		try {
			ExcelWSheet = setExcelFile(filePath, fileName, SheetName);
			int rowNum,colNum;
			rowNum = ExcelWSheet.getLastRowNum(); // returns number of rows - 1 i.e 3 if number of rows are 4 in cell
			colNum= ExcelWSheet.getRow(0).getLastCellNum(); // getting number of columns count
			CellData = new String[rowNum][colNum]; // creating 2D array to store excel data
			//ignore's 1st row assuming its column name & start fetching data from 2nd row ,1st column onwards
			for(int i = 1;i <=rowNum ;i++){ // fetch entire row one by one
				for(int j=0; j < colNum; j++) { // fetch each column of row fetched above one by one 
						cell = ExcelWSheet.getRow(i).getCell(j); // gets value of each cell of excel i.e A1,A2,A3 etc
						CellData[i-1][j] =  cell==null ? "" : df.formatCellValue(cell); //formating value of excel to string
				}
			}
		}
		catch (Exception e) {
			CellData=null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExcelWBook.close();
		ExcelFile.close();
		return CellData;
	}
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as row num and Col num
	 * @param filePath  : Absolute folder path where excel file is placed
	 * @param fileName  : Name of excel file with extension e.g. ExcelFile.xls or ExcelFile.xlsx
	 * @param SheetName : SheetName from where data is to be read 
	 * @return 			: Cell Value gets return
	 * @throws Exception
	 */
	public String[][] readExcelWithTestCaseId(String filePath,String fileName,String SheetName, String testCaseId) throws Exception{
		Sheet ExcelWSheet;
		String CellData[][];
		try {
			ExcelWSheet = setExcelFile(filePath, fileName, SheetName);
			int colNum;
				
			List<Integer> rowNum = new ArrayList<Integer>();
			rowNum = findRow(ExcelWSheet, testCaseId);
			colNum= ExcelWSheet.getRow(rowNum.get(0)).getLastCellNum(); // getting number of columns count
			CellData = new String[rowNum.size()][colNum-1]; // creating 2D array to store excel data
			//ignore's 1st row assuming its column name & start fetching data from 2nd row ,1st column onwards
			
			
			  for(int i=0; i<rowNum.size();i++){ // fetch entire row one by one 
				  
				  for(int j=1; j< colNum; j++) { // fetch each column of row fetched above one by one 
					  cell = ExcelWSheet.getRow(rowNum.get(i)).getCell(j); // gets value of each cell of excel i.e A1,A2,A3 etc 
			      CellData[i][j-1] = cell==null ? "" : df.formatCellValue(cell);
			  //formating value of excel to string 
				  } 
				  
		  }
		}
		catch (Exception e) {
			CellData=null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExcelWBook.close();
		ExcelFile.close();
		return CellData;
	}
	
	
	  public List findRow(Sheet excelWSheet, String cellContent) { 
		  List<Integer> rowNumbers = new ArrayList<Integer>();
		  int rowNum = excelWSheet.getLastRowNum();
		  for (int i=1;i<=rowNum;i++) { 
			  {
				 try {
				  cell = excelWSheet.getRow(i).getCell(0,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				  if (cell.getCellType() == Cell.CELL_TYPE_STRING) {  
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						 int rownumber=i;
						 rowNumbers.add(rownumber);
						 System.out.println("rowNumber of "+cellContent +" is "+rownumber);
				       }
				   }
				 }
				 catch(NullPointerException e) {
					 continue;
				 }
			  }
		  }
	   return rowNumbers;
	  
	  }
			  
				 

	 
	
	
	/**
	 * This method is to read the test data from the Excel cell, in this we are passing parameters as row num and Col num
	 * @param filePath  : Absolute folder path where excel file is placed
	 * @param fileName  : Name of excel file with extension e.g. ExcelFile.xls or ExcelFile.xlsx
	 * @param SheetName : SheetName from where data is to be read 
	 * @return 			: Hashmap containing the columnName as key and values associated with columns as value
	 * @throws Exception
	 */
	public Object[][] readExcelToMap(String filePath,String fileName,String SheetName) throws Exception{
		Sheet ExcelWSheet;
		Object[][] mapValues ;
		try {
			ExcelWSheet = setExcelFile(filePath, fileName, SheetName);
			int rowNum,colNum;
			rowNum = ExcelWSheet.getLastRowNum(); // returns number of rows - 1 i.e 3 if number of rows are 4 in cell
			colNum= ExcelWSheet.getRow(0).getLastCellNum(); // getting number of columns count

			mapValues = new Object[rowNum][1]; 

			
			for(int i = 0;i <rowNum ;i++){ // fetch entire row one by one
				Map<String, String> datamap = new HashMap<>();
				for(int j=0; j < colNum; j++) { // fetch each column of row fetched above one by one 
					
					cellKey = ExcelWSheet.getRow(0).getCell(j);
					String key =  cellKey==null ? "" : df.formatCellValue(cellKey);
					cellValue = ExcelWSheet.getRow(i+1).getCell(j);
					String value =  cellValue==null ? "" : df.formatCellValue(cellValue);
					datamap.put(key, value);
				}
			    mapValues[i][0] = datamap;
			
			}
		}
		catch (Exception e) {
			mapValues=null;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExcelWBook.close();
		ExcelFile.close();
		return mapValues;
	}
	
}
