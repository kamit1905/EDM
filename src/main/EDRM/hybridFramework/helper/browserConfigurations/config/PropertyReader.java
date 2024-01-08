package main.EDRM.hybridFramework.helper.browserConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import main.EDRM.hybridFramework.helper.browserConfigurations.BrowserType;
import main.EDRM.hybridFramework.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	
	private static FileInputStream file,file1;
	public static Properties OR;
	public static Properties ER;

	public PropertyReader() {
		try {
			//String filePath = ResourceHelper.getResourcePath("src/main/EDRM/resources/config.properties");
			String filePath = ResourceHelper.getResourcePath("src\\main\\EDRM\\resources\\config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
			
			//String filePath1 = ResourceHelper.getResourcePath("src/main/EDRM/resources/EnvConfig.properties");
			String filePath1 = ResourceHelper.getResourcePath("src\\main\\EDRM\\resources\\EnvConfig.properties");
			file1 = new FileInputStream(new File(filePath1));
			ER = new Properties();
			ER.load(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		if(System.getProperty("applicationUrl")!=null){
			return System.getProperty("applicationUrl");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}
	
	public String getSuperUserName() {
		if(System.getProperty("superUserName")!=null){
			return System.getProperty("superUserName");
		}
		return OR.getProperty("superUserName");
	}	
	
	public String getLoginId() {
		if(System.getProperty("loginId")!=null){
			return System.getProperty("loginId");
		}
		return OR.getProperty("loginId");
	}

	public String getSuperLoginId() {
		if(System.getProperty("superLoginId")!=null){
			return System.getProperty("superLoginId");
		}
		return OR.getProperty("superLoginId");
	}
	
	public String getNonAdminLoginId() {
		if(System.getProperty("nonAdminId")!=null){
			return System.getProperty("nonAdminId");
		}
		return OR.getProperty("nonAdminId");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}
	
	public String getSuperUserPassword() {
		if(System.getProperty("superUserPassword")!=null){
			return System.getProperty("superUserPassword");
		}
		return OR.getProperty("superUserPassword");
	}
	
	public String getNonAdminUserPassword() {
		if(System.getProperty("nonAdminPassword")!=null){
			return System.getProperty("nonAdminPassword");
		}
		return OR.getProperty("nonAdminPassword");
	}
	
	public String getLoggedInUsersTeam() {
		if(System.getProperty("loggedInUserTeam")!=null){
			return System.getProperty("loggedInUserTeam");
		}
		return OR.getProperty("loggedInUserTeam");
	}


	public String getChromeDriverPath() {
		if(System.getProperty("chromeDriverPath")!=null){
			return ResourceHelper.getResourcePath(System.getProperty("chromeDriverPath"));
		}
		return ResourceHelper.getResourcePath(OR.getProperty("chromeDriverPath"));
	}

	public String getAutoITExePath() {
		if(System.getProperty("autoITExePath")!=null){
			return ResourceHelper.getResourcePath(System.getProperty("autoITExePath"));
		}
		return ResourceHelper.getResourcePath(OR.getProperty("autoITExePath"));
	}
	
	public String getAutoITExeFolderPath() {
		if(System.getProperty("autoITExePath")!=null){
			return ResourceHelper.getResourcePath(System.getProperty("autoITExeFolderPath"));
		}
		return ResourceHelper.getResourcePath(OR.getProperty("autoITExeFolderPath"));
	}
	
	public String getResourceFolderPath() {
		if(System.getProperty("resourceFolderPath")!=null){
			return ResourceHelper.getResourcePath(System.getProperty("resourceFolderPath"));
		}
		return ResourceHelper.getResourcePath(OR.getProperty("resourceFolderPath"));
	}
	
	public String getTestDocFolderPath() {
		if(System.getProperty("testDocFolderPath")!=null){
			return ResourceHelper.getResourcePath(System.getProperty("testDocFolderPath"));
		}
		return ResourceHelper.getResourcePath(OR.getProperty("testDocFolderPath"));
	}
	
	
	public String getFileSystemName() {
		if(System.getProperty("fileSystem")!=null){
			return System.getProperty("fileSystem");
		}
		return OR.getProperty("fileSystem");
	}
	
	public String getNoRecordMessage() {
		if(System.getProperty("noRecordMessage")!=null){
			return System.getProperty("noRecordMessage");
		}
		return ER.getProperty("noRecordMessage");
	}
	
	
	/*
	 * Added to get Account file name to import
	 */
	public String getAccountImportFileName() {
		if(System.getProperty("importAccountFileName")!=null) {
			return System.getProperty("importAccountFileName");
		}
		return OR.getProperty("importAccountFileName");
	}
	
	/*
	 * Added to get Misc file name to import
	 */
	public String getMiscImportFileName() {
		if(System.getProperty("importMiscFileName")!=null) {
			return System.getProperty("importMiscFileName");
		}
		return OR.getProperty("importMiscFileName");
	}
	
	
	/**
     * Get folder1RefName e.g Acc, folder1
     */
    public String getFolder1RefName() {
        if(System.getProperty("folder1RefName")!=null){
            return System.getProperty("folder1RefName");
        }
        return ER.getProperty("folder1RefName");
    }
    
    /**
     * Get folder3RefName e.g Misc, folder2
     */
    public String getFolder2RefName() {
        if(System.getProperty("folder2RefName")!=null){
            return System.getProperty("folder2RefName");
        }
        return ER.getProperty("folder2RefName");
    }
    
    /**
     * Get folder3RefName e.g Prop, folder3
     */
    public String getFolder3RefName() {
        if(System.getProperty("folder3RefName")!=null){
            return System.getProperty("folder3RefName");
        }
        return ER.getProperty("folder3RefName");
    }
    
    
    /**
     * Get teamId e.g testing
     */
    public String getLoggedInUsersTeamId() {
        if(System.getProperty("loggedInUserTeamId")!=null){
            return System.getProperty("loggedInUserTeamId");
        }
        return OR.getProperty("loggedInUserTeamId");
    }
    
    public String getUrl1() {
		if(System.getProperty("applicationUrl1")!=null){
			return System.getProperty("applicationUrl1");
		}
		return OR.getProperty("applicationUrl1");
	}
    
    
    /*
     * Get public accessURL
     */
    public String getPublicAccessUrl() {
    	if(System.getProperty("publicAccessUrl")!=null){
			return System.getProperty("publicAccessUrl");
		}
		return OR.getProperty("publicAccessUrl");
    }
    
    /*
     * Get email Id for document connect
     *
     */
	public String getEmailId() {
		if(System.getProperty("emailId")!=null){
			return System.getProperty("emailID");
		}
		return OR.getProperty("emailId");
	}
	
	
	/*
	 * Used to get capture user
	 * 
	 */
	public String getCaptureUser() {
		if(System.getProperty("captureUser")!=null){
			return System.getProperty("captureUser");
		}
		return OR.getProperty("captureUser");
	}

	/*
	 * It is use to set password for newely created user
	 */
	public String setUserPassword() {
		if(System.getProperty("setPassword")!=null){
			return System.getProperty("setPassword");
		}
		return OR.getProperty("setPassword");
	}
	
	public String getOtherUserTeamName() {
		if(System.getProperty("otherUserTeam")!=null) {
			return System.getProperty("otherUserTeam");
		}
		return OR.getProperty("otherUserTeam");
	}
	
	public String getOtherUserTeamId() {
		if(System.getProperty("otherUserTeamId")!=null) {
			return System.getProperty("otherUserTeamId");
		}
		return OR.getProperty("otherUserTeamId");
	}

	@Override
	public String getFolder1Reference() {
		if(System.getProperty("folder1Reference")!=null) {
			return System.getProperty("folder1Reference");
		}
		return OR.getProperty("folder1Reference");
	}

	@Override
	public String getRoleName() {
		if(System.getProperty("roleName")!=null) {
			return System.getProperty("roleName");
		}
		return OR.getProperty("roleName");
	}

	@Override
	public String getSAUDistributionUser() {
		if(System.getProperty("SAUDistrUser")!=null) {
			return System.getProperty("SAUDistrUser");
		}
		return OR.getProperty("SAUDistrUser");
	}

	@Override
	public String getFSADistributionUser() {
		if(System.getProperty("FSADistrUser")!=null) {
			return System.getProperty("FSADistrUser");
		}
		return OR.getProperty("FSADistrUser");
	}

	@Override
	public String getDocumentConnectUrl() {
		if(System.getProperty("documentConnectUrl")!=null) {
			return System.getProperty("documentConnectUrl");
		}
		return OR.getProperty("documentConnectUrl");
	}


}
