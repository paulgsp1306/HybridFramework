package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunction.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFIleUtil;

public class DriverScript extends AppUtil{
	String inputpath = "F:\\Automation\\Hybrid_Framework\\Input\\DataEngine.xlsx";
	String outputpath= "F:\\Automation\\Hybrid_Framework\\Output\\HybridResults.xlsx";
	String TCSheet = "MasterTCS";
	String TSSheet = "TestSteps";
	@Test
	public void startTest() throws Throwable
	{
		boolean res = false;
		String tcres = "";
		//access excel methods
		ExcelFIleUtil xl = new ExcelFIleUtil(inputpath);
		//count no of rows sheet
		int TCCount = xl.rowCount(TCSheet);
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log("No of rows in tcsheet::"+TCCount+"   "+"No of columns in TSSheet"+TSCount,true);
		for(int i = 1;i<=TCCount;i++)
		{
			//read modulestatus cell
			String Modulestatus = xl.getCellData(TCSheet, i,2);
			if(Modulestatus.equalsIgnoreCase("Y"))
			{
				//read tcid cell
				String tcid = xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++) 
				{
					String tsid = xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						String keyword = xl.getCellData(TSSheet, j, 4);
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							res = FunctionLibrary.verifyLogin(para1, para2);
						}
						else if (keyword.equalsIgnoreCase("BranchCreation"))
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para3 = xl.getCellData(TSSheet, j, 7);
							String para4 = xl.getCellData(TSSheet, j, 8);
							String para5 = xl.getCellData(TSSheet, j, 9);
							String para6 = xl.getCellData(TSSheet, j, 10);
							String para7 = xl.getCellData(TSSheet, j, 11);
							String para8 = xl.getCellData(TSSheet, j, 12);
							String para9 = xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBranches();
							res = FunctionLibrary.verifyBranchCreation(para1, para2, para3, para4, para5, para6, para7, para8, para9);
							
						}
						else if (keyword.equalsIgnoreCase("BranchUpdate")) 
						{
							String para1 = xl.getCellData(TSSheet, j, 5);
							String para2 = xl.getCellData(TSSheet, j, 6);
							String para5 = xl.getCellData(TSSheet, j, 9);
							String para6 = xl.getCellData(TSSheet, j, 10);
							FunctionLibrary.clickBranches();
							res = FunctionLibrary.verifyBranchUpdation(para1, para2, para5, para6);
							
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res = FunctionLibrary.verifyLogout();
							
						}
						String tsres="";
						if(res)
						{
							//write as pass into status cell
							tsres = "Pass";
							xl.SetCellData(TSSheet, j, 3, tsres, outputpath);
						}
						else
						{
							tsres="Fail";
							xl.SetCellData(TSSheet, j, 3, tsres, outputpath);
						}
						tcres = tsres;
						
					}
				}
				xl.SetCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else

			{
				//write as blocked which are flag to N
				xl.SetCellData(TCSheet, i, 3, "Blocked", outputpath);

			}

		}
	}
}
