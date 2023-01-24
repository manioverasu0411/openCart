package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException
    {

        String path = ".\\testData\\OpenCart_LoginData.xlsx";

        ExcelUtility excelUtility = new ExcelUtility(path);
        int totalRows = excelUtility.getRowCount("sheet1");
        int totalColm = excelUtility.getCellCount("sheet1",1);

        String logindata[][] = new String[totalRows][totalColm];

        for(int i=1;i<=totalRows;i++){

            for(int j=0;j<totalColm;j++){

                logindata[i-1][j]=excelUtility.getCellData("Sheet1",i,j);
            }
        }


        return logindata;

        }

    }

