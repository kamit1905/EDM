package main.EDRM.hybridFramework.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "suite") 
public class Suite {
  @JacksonXmlProperty(isAttribute = true)  private String name;
  @JacksonXmlProperty(localName = "test") 
  @JacksonXmlElementWrapper(useWrapping = false) 
  private List < Test > tests;
  
  public Suite(String name) {
   this.name = name;
   this.tests = new ArrayList < Suite.Test > ();
  }
  
  public void addTest(String moduleName,String testname, String paramName, String paramValue, String className) {
   Test test = new Test(moduleName);
   test.addParam(paramName, paramValue);
  // Pattern.compile(",").splitAsStream(className).forEach(test::addClass);
   test.addClass(className, testname);
   this.tests.add(test);
  }
  
  public void addTestMethods(String className,String testName) {
	 this.tests.get(tests.size()-1).addClass(className, testName);
  }
  
  class Test {
   @JacksonXmlProperty(isAttribute = true) private String name;
   @JacksonXmlProperty(localName = "parameter") private Parameter param;
   @JacksonXmlProperty(localName = "classes") private Classes klasses;
   public Test(String name) {
    this.name = name;
    klasses = new Classes();
   }
   
   public void addParam(String name, String value) {
    param = new Parameter(name, value);
   }
   
   public void addClass(String name,String testName) {
    klasses.addClasses(name,testName);
   }
   
   
  }
  
  
  
  class Parameter {
   @JacksonXmlProperty(isAttribute = true) private String name;
   @JacksonXmlProperty(isAttribute = true) private String value;
   public Parameter(String name, String value) {
    this.name = name;
    this.value = value;
   }
  }
 
  
  
  class Classes {
   @JacksonXmlProperty(localName = "class") @JacksonXmlElementWrapper(useWrapping = false) private List < Class > classes;
   public Classes() {
    this.classes = new ArrayList < Suite.Class > ();
   }
   public void addClasses(String name,String testName) {
    this.classes.add(new Class(name,testName));
   }
  }
  
  class Class {
   @JacksonXmlProperty(isAttribute = true) @JacksonXmlElementWrapper(useWrapping = false) private String name;
   @JacksonXmlProperty(localName = "methods") private methods methods;
   Class(String name,String testname) {
    this.name = name;
    methods=new methods();
    methods.addMethods(testname);
   }
   
   public void addIncludeMethod(String name) {
	methods.addMethods(name);
   }
  }

   class methods {
   @JacksonXmlProperty(localName = "include") @JacksonXmlElementWrapper(useWrapping = false) private List < includes > methods;
   public methods() {
   this.methods = new ArrayList < Suite.includes > ();
   }
   
   public void addMethods(String name) {
    this.methods.add(new includes(name));
   }
  }
  
  class includes {
   @JacksonXmlProperty(isAttribute = true) private String name;
   public includes(String name) {
    this.name = name;
   }
  }
}
  