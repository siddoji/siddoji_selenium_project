package commonFunctionPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utility_Common_Function {
	
	public static void evidencecreator(String filename,String requestBody,String responseBody) throws IOException {
		
		File newfile=new File("C:\\Users\\admin\\Desktop\\rest\\evidence\\"+filename+".txt");
		
		System.out.println("a new file is created to save request and response"+newfile.getName());
		
		FileWriter datawrite=new FileWriter(newfile);
		datawrite.write("resquest body"+requestBody+"\n\n");
		datawrite.write("response body"+responseBody);
		datawrite.close();
		
		System.out.println("request and responsebody saved into a file"+newfile.getName());
	}
	
	public static  ArrayList<String> readdataexel(String sheetname , String testcasename) throws IOException {
		
		ArrayList<String> arrayData=new ArrayList<String>();
		
		//step1 create object of fileinputstraem
		FileInputStream fis=new FileInputStream("C:\\Users\\admin\\Desktop\\rest\\evidence\\testdata.xlsx");
		
		//Step2 access the excel file
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		//step3 access the sheet 
		int countofsheet=workbook.getNumberOfSheets();
		
		for (int i=0;i<countofsheet; i++)
		{
			String filesheetname=workbook.getSheetName(i);
			if (filesheetname.equalsIgnoreCase(sheetname))
			{
				//step4 access the row from where data supposed to fetch
				XSSFSheet sheet=workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				while (rows.hasNext())
				{
					Row r1=rows.next();
					if (r1.getCell(0).getStringCellValue().equalsIgnoreCase(testcasename))
							{
						Iterator<Cell> cellvalues=r1.cellIterator();
						while(cellvalues.hasNext())
						{
							String testdata=cellvalues.next().getStringCellValue();
							arrayData.add(testdata);
						}
							}
				}
				
			}
			
		}
		workbook.close();
		return arrayData;
	}

}
