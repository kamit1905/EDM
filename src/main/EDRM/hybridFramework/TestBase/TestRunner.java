package main.EDRM.hybridFramework.TestBase;

import main.EDRM.hybridFramework.ExcelReader.XLSReader;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;

public class TestRunner {

	public static void main(String[] args) {
		/*try {
			String xlFilePath;

			if (args.length > 0) {
				xlFilePath = args[0];
			} else {
				System.out
						.println("Usage: TestRunner [input file/dir] <suite thread count>");
				return;
				// xlFilePath = "test-input";
			}

			ExcelTestNGRunner runner = new ExcelTestNGRunner(xlFilePath);
			runner.setPreserveOrder(true);
			runner.addListener(new RetryListener());
			runner.setVerbose(2);

			if (args.length > 1) {
				runner.setSuiteThreadPoolSize(Integer.parseInt(args[1]));
			}

			runner.run();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	*/
		
		//XLSReader suite = new XLSReader(ResourceHelper.getResourcePath("src\\test-input\\EDMTestRunner.xlsx"));
		//suite.getTests("select * from TestCase where Module='Login' and Active='Y'");
		
	}

}
