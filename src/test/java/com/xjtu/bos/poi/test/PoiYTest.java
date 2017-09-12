package com.xjtu.bos.poi.test;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;



public class PoiYTest {
	/**
	 * 解析excel
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
    public void excelParse() throws FileNotFoundException, IOException{
    	//获得hssfworkbook工作薄，针对xls格式
         HSSFWorkbook  hssfWorkbook= new HSSFWorkbook(new FileInputStream("info.xls"));
		//要解析的Sheet
        HSSFSheet  sheet =  hssfWorkbook.getSheet("sheet1");
        HSSFSheet  sheet2 = hssfWorkbook.getSheetAt(0);
         //解析每一行
        for(Row row:sheet2){
        	//解析每一个单元格
        	for(Cell cell:row){
        		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
        			System.out.println(cell.getStringCellValue());
        		}else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
        			System.out.println(cell.getNumericCellValue());
        			
        		}
        		
        	}
        	
        }
		
    }
	/**
	 * 生成excel
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void  excelCeate() throws FileNotFoundException, IOException{
        HSSFWorkbook  hssfWorkbook  = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("数据信息");
	    HSSFRow  row = 	sheet.createRow(0);
		row.createCell(0).setCellValue("产品");
		row.createCell(1).setCellValue("价格");
		hssfWorkbook.write(new FileOutputStream("d:/text.xls"));
		}
}
