package main.EDRM.hybridFramework.ExcelReader;

import java.io.File;

import org.apache.log4j.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import main.EDRM.hybridFramework.TestBase.Suite;
import main.EDRM.hybridFramework.helper.logger.LoggerHelper;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;

public class XLSReader {
 private final Fillo fillo;
 private final String filePath;
 private Connection connection;
 private Logger log = LoggerHelper.getLogger(XLSReader.class);
 
 public XLSReader(String filePath) {
  fillo = new Fillo();
  this.filePath = filePath;
 }

 public void getTests(String query) {
  try {
   connection = fillo.getConnection(this.filePath);
   Recordset recordset = connection.executeQuery(query);
   this.createSuite(recordset);
  } catch (FilloException e) {
   e.printStackTrace();
  } finally {
   connection.close();
  }
 }
 
 
 private void createSuite(Recordset recordset) {
  XmlMapper xmlMapper = new XmlMapper();
  Suite suite = new Suite("EDM Regression");
  String prevClassname="";
  try {
   while (recordset.next()) {
    String testName = recordset.getField("TestCaseName");
    String className = recordset.getField("ClassName");
    String moduleName = recordset.getField("Module");
    if(prevClassname==className) {
    	suite.addTestMethods(className,testName);
    }
    else {
    String param = "Data";
    String paramValue = recordset.getField("Data");
    
    suite.addTest(moduleName,testName, param, paramValue, className);
    prevClassname=className;
    }
   }
   xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
   String path = "src//testng-suite.xml";
   xmlMapper.writeValue(new File(ResourceHelper.getResourcePath(path)), suite);
   log.info("XML successefully created at "+path);
  } 
  catch (Exception e) {
   e.printStackTrace();
  } 
  finally {
   recordset.close();
  }
 }
}