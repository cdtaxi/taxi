package cn.slkj.taxi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
public class ObjectExcelRead {
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum)
	  {
	    List varList = new ArrayList();
	    try
	    {
	      File target = new File(filepath, filename);
	      FileInputStream fi = new FileInputStream(target);
	      HSSFWorkbook wb = new HSSFWorkbook(fi);
	      HSSFSheet sheet = wb.getSheetAt(sheetnum);
	      System.out.println(sheet.getFirstRowNum());
	      System.out.println(sheet.getLastRowNum());
	      int rowNum = sheet.getLastRowNum() + 1;
	      System.out.println("rowNum"+rowNum);
	      System.out.println("startrow"+startrow);
	      for (int i = startrow; i < rowNum; i++)
	      {
	        PageData varpd = new PageData();
	        HSSFRow row = sheet.getRow(i);
	        int cellNum = row.getLastCellNum();

	        for (int j = startcol; j < cellNum; j++)
	        {
	          HSSFCell cell = row.getCell(j);
	          String cellValue = null;
	          if (cell != null){
	            switch (cell.getCellType()) {
	            case 0:
	              if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                SimpleDateFormat sdf = null;
	                if (cell.getCellStyle().getDataFormat() == 
	                  HSSFDataFormat.getBuiltinFormat("h:mm"))
	                  sdf = new SimpleDateFormat("HH:mm");
	                else {
	                  sdf = new SimpleDateFormat("yyyy-MM-dd");
	                }
	                Date date = cell.getDateCellValue();
	                cellValue = sdf.format(date);
	                break; 
	                } 
	              if (cell.getCellStyle().getDataFormat() == 58)
	              {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                double value = cell.getNumericCellValue();
	                Date date = 
	                  DateUtil.getJavaDate(value);
	                cellValue = sdf.format(date);
	                break;
	              }
	              double value = cell.getNumericCellValue();
	              CellStyle style = cell.getCellStyle();
	              DecimalFormat format = new DecimalFormat();
	              String temp = style.getDataFormatString();

	              if (temp.equals("General")) {
	                format.applyPattern("#");
	              }
	              cellValue = format.format(value);

	              break;
	            case 1:
	              cellValue = cell.getStringCellValue();
	              break;
	            case 2:
	              cellValue = cell.getNumericCellValue()+"";

	              break;
	            case 3:
	              cellValue = "";
	              break;
	            case 4:
	              cellValue = String.valueOf(cell.getBooleanCellValue());
	              break;
	            case 5:
	              cellValue = String.valueOf(cell.getErrorCellValue());
	            default:
	              break;
	            }
	          }
	          else cellValue = "";

	          varpd.put("var" + j, cellValue);
	        }

	        varList.add(varpd);
	      }
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }

	    return varList;
	  }
}
