package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFIleUtil {
XSSFWorkbook wb;
//constructor for reading excel path
public ExcelFIleUtil(String excelpath) throws Throwable
{
	FileInputStream fi = new FileInputStream(excelpath);
	wb = new XSSFWorkbook(fi);
}
//count no of rows in a sheet
public int rowCount(String SheetName)
{
	return wb.getSheet(SheetName).getLastRowNum();
}
//counting no of cells in a row
public int cellcount(String SheetName)
{
	return wb.getSheet(SheetName).getRow(0).getLastCellNum();			
}
//get cell data
public String getCellData(String SheetName, int row, int column)throws Throwable
{
	String data = "";
	if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int cellData = (int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(cellData);
	}else
	{
		data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//method for set cell data
@SuppressWarnings("deprecation")
public void SetCellData(String SheetName, int row, int column, String Status, String WriteExcel)throws Throwable
{
	//get sheet from wb
	XSSFSheet ws = wb.getSheet(SheetName);
	//get row from sheet
	XSSFRow rownum = ws.getRow(row);
	//create cell in a row
	XSSFCell cell = rownum.createCell(column);
	//write cell
	cell.setCellValue(Status);
	if(Status.equalsIgnoreCase("Pass"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		//color the txt into green
		font.setColor(IndexedColors.OLIVE_GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).getCellStyle();
		
	}
	else if(Status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		//color the txt into Red
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).getCellStyle();
	}
	else if(Status.equalsIgnoreCase("Blocked"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		//color the txt into Blue 
		font.setColor(IndexedColors.DARK_BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(column).getCellStyle();
	}
	FileOutputStream fo = new FileOutputStream(WriteExcel);
	wb.write(fo);
	
			
	
}
public static void main(String[] args) throws Throwable {
	ExcelFIleUtil xl = new ExcelFIleUtil("F://Exceldata.xlsx");
	int rc = xl.rowCount("Login");
	int cc = xl.cellcount("Login");
	System.out.println(rc+"   "+cc);
	for(int i=1;i<=rc;i++)
	{
		String user = xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		System.out.println(user+"   "+pass);
		xl.SetCellData("Login", i, 2, pass, "F://results.xlsx");
		xl.SetCellData("Login", i, 2, fail, "F://results.xlsx");
		xl.SetCellData("Login", i, 2, Blocked, "F://results.xlsx");
		
	}
}
}



















