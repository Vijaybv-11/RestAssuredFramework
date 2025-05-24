package api.Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	@DataProvider(name = "UserData")
	public String[][] getData() throws IOException {
		String path = "./testData/PestSwaggerTestData.xlsx";
		XLUtility xlutil = new XLUtility(path);

		int totalrows = xlutil.getRowCount("UserData");
		int totalcols = xlutil.getCellCount("UserData", 1);

		String apiData[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) // Row always starts from one 
		{
			for (int j = 0; j < totalcols; j++) // 0 Cloumn always start from 0
			{
				apiData[i - 1][j] = xlutil.getCellData("UserData", i, j);
			}

		}

		return apiData;
	}
	
	@DataProvider(name = "userNames")
	public String [] getUserNames() throws IOException {
		
		String filePath = System.getProperty("user.dir")+"//testData//PestSwaggerTestData.xlsx";
		XLUtility xlutil = new XLUtility(filePath);
		String sheetName="UserData";
		int rownum =xlutil.getRowCount(sheetName);
		
		String apidata[] =new String[rownum];
		for(int i=1;i<=rownum;i++) {
			
			apidata[i-1]=xlutil.getCellData(sheetName, i, 1);
		}
		
		return apidata;
		
	}

}
