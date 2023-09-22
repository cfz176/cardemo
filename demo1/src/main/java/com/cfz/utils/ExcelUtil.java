package com.cfz.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {

	/**
	 * @auth: xing
	 * @return：void
	 * @description:response 响应,sheetName sheet页名字,titleName 第二行的字段名字,list 填充数据,key 列名
	 */
	public static void exportExcel(HttpServletResponse response, String sheetName, String[] titleName, List<Map> list,
			String[] key) throws IOException {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wkb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wkb.createSheet(sheetName);
		// 在sheet里创建第一行
		HSSFRow row1 = sheet.createRow(0);
		// 填充首行
		for (int i = 0; i < titleName.length; i++) {
			row1.createCell(i).setCellValue(titleName[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			// 在sheet里创建第二行
			HSSFRow row = sheet.createRow(i + 1);
			for (int j = 0; j < titleName.length; j++) {
				row.createCell(j)
						.setCellValue(list.get(i).get(key[j]) == null ? "" : list.get(i).get(key[j]).toString());
			}
		}
		// .....省略部分代码 //输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=file.xls");
		response.setContentType("application/msexcel");
		wkb.write(output);
		output.close();
	}

	
	
	/**
	 * @auth: xing
	 * @return：void
	 * @description:response 响应,sheetName sheet页名字,titleName 第二行的字段名字,list 填充数据,key 列名
	 */
	public static void exportExcels(HttpServletResponse response, Iterator<String> iterator, String[] titleName, Map<String,List<Map>> map,
			String[] key) throws IOException {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wkb = new HSSFWorkbook();
		while(iterator.hasNext()){
			String sheetName = iterator.next();
			List<Map> list = map.get(sheetName);
			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wkb.createSheet(sheetName);
			// 在sheet里创建第一行
			HSSFRow row1 = sheet.createRow(0);
			// 填充首行
			for (int i = 0; i < titleName.length; i++) {
				row1.createCell(i).setCellValue(titleName[i]);
			}
			for (int i = 0; i < list.size(); i++) {
				// 在sheet里创建第二行
				HSSFRow row = sheet.createRow(i + 1);
				for (int j = 0; j < titleName.length; j++) {
					row.createCell(j)
							.setCellValue(list.get(i).get(key[j]) == null ? "" : list.get(i).get(key[j]).toString());
				}
			}
		}
		// .....省略部分代码 //输出Excel文件
		OutputStream output = response.getOutputStream();
		response.reset();
		response.setHeader("Content-disposition", "attachment; filename=file.xls");
		response.setContentType("application/msexcel");
		wkb.write(output);
		output.close();
	}
}