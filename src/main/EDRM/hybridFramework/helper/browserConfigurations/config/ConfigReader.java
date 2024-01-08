package main.EDRM.hybridFramework.helper.browserConfigurations.config;

import main.EDRM.hybridFramework.helper.browserConfigurations.BrowserType;

public interface ConfigReader {

	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUrl1();
	public String getLoginId();
	public String getSuperLoginId();
	public String getUserName();
	public String getSuperUserName();
	public String getPassword();
	public String getSuperUserPassword();
	public String getChromeDriverPath();
	public String getAutoITExePath();
	public String getAutoITExeFolderPath();
	public String getResourceFolderPath();
	public String getTestDocFolderPath();
	public String getFileSystemName();
	public String getNonAdminLoginId();
	public String getNonAdminUserPassword();
	public String getLoggedInUsersTeam();
	public String getNoRecordMessage();
	public String getAccountImportFileName();
	public String getMiscImportFileName();
	public String getFolder1RefName();
	public String getFolder2RefName();
	public String getFolder3RefName();
	public String getLoggedInUsersTeamId();
	public String getPublicAccessUrl();
	public String getEmailId();
	public String getCaptureUser();
	public String setUserPassword();
	public String getOtherUserTeamName();
	public String getOtherUserTeamId();
	public String getFolder1Reference();
	public String getRoleName();
	public String getSAUDistributionUser();
	public String getFSADistributionUser();
	public String getDocumentConnectUrl();
}
