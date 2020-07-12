package cn.slkj.taxi.ireport;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings("unchecked")
public class JasperUtils {

	private static Logger logger = Logger.getLogger(JasperUtils.class);
	public static final String PRINT_TYPE = "print";
	public static final String PDF_TYPE = "pdf";
	public static final String EXCEL_TYPE = "excel";
	public static final String HTML_TYPE = "html";
	public static final String WORD_TYPE = "word";

	/**
	 * 如果导出的是excel，则需要去掉周围的margin
	 * 
	 * @param jasperReport
	 * @param type
	 */
	public static void prepareReport(JasperReport jasperReport, String type) {
		logger.debug("The method======= prepareReport() start.......................");
		if ("excel".equals(type))
			try {
				Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("topMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				margin = JRBaseReport.class.getDeclaredField("bottomMargin");
				margin.setAccessible(true);
				margin.setInt(jasperReport, 0);
				Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
				pageHeight.setAccessible(true);
				pageHeight.setInt(jasperReport, 2147483647);
			} catch (Exception exception) {

			}

	}

	/**
	 * 导出Excel
	 * 
	 * @param jasperPrint
	 * @param defaultFilename
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws JRException
	 */
	public static void exportExcel(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
		logger.debug("执行导出excel   The method======= exportExcel() start.......................");
		response.setContentType("application/vnd.ms-excel"); // 设置头文件信息
		String defaultname = null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname = defaultFilename + ".xls";
		} else {
			defaultname = "export.xls";
		}
		String fileName = new String(defaultname.getBytes("gbk"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		ServletOutputStream ouputStream = response.getOutputStream();
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.exportReport();
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 导出pdf，注意此处中文问题， 这里应该详细说：主要在ireport里变下就行了。看图
	 * 1）在ireport的classpath中加入iTextAsian.jar 2）在ireport画jrxml时，看ireport最左边有个属性栏。
	 * 下边的设置就在点字段的属性后出现。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
	 */
	private static void exportPdf(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
		response.setContentType("application/pdf");
		String defaultname = null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname = defaultFilename + ".pdf";
		} else {
			defaultname = "export.pdf";
		}
		String fileName = new String(defaultname.getBytes("gbk"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		ServletOutputStream ouputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * description:通过传进来的pageIndexStr 得到当前页码 Date 2013-1-18 上午10:49:38
	 * 
	 * @param @param jasperPrint
	 * @param @param request
	 * @param @param pageIndexStr
	 * @param @return
	 * @return Integer
	 */
	private static Integer getPageIndex(JasperPrint jasperPrint, HttpServletRequest request, String pageIndexStr) {
		if (pageIndexStr == null || StringUtils.isBlank(pageIndexStr)) { // 如果pageIndexStr为空或空字符串则返回null
			return null;
		}
		Integer pageIndex = 0;
		int lastPageIndex = 0;
		if (jasperPrint.getPages() != null) { // 得到最后一页的 页码
			lastPageIndex = jasperPrint.getPages().size() - 1;
		}
		if ("lastPage".equals(pageIndexStr)) { // 如果字符串＝＝lastPage
			// 则反lastPageIndex的值赋给pageIndex
			// 并返回pageIndex
			pageIndex = lastPageIndex;
			return pageIndex;
		}
		try {
			pageIndex = Integer.parseInt(pageIndexStr);
			if (pageIndex > 0) { // 从ireport
				// 传来的PageIndex是从1开始，而JRExporterParameter.PAGE_INDEX是从0开始的
				pageIndex = pageIndex - 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (pageIndex < 0) {
			pageIndex = 0;
		}
		if (pageIndex > lastPageIndex) {
			pageIndex = lastPageIndex;
		}
		return pageIndex;
	}

	/**
	 * 导出word
	 */

	private static void exportWord(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("application/msword;charset=utf-8");
		String defaultname = null;
		if (defaultFilename.trim() != null && defaultFilename != null) {
			defaultname = defaultFilename + ".doc";
		} else {
			defaultname = "export.doc";
		}
		String fileName = new String(defaultname.getBytes("gbk"), "utf-8");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		JRExporter exporter = new JRRtfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		exporter.exportReport();
	}

	/**
	 * 按照类型导出不同格式文件
	 * 
	 * @param datas                     数据
	 * @param type                      文件类型
	 * @param is                        jasper文件的来源
	 * @param request
	 * @param response
	 * @param defaultFilename默认的导出文件的名称
	 */

	private static void export(Collection datas, String type, String defaultFilename, InputStream is, HttpServletRequest request, HttpServletResponse response, String pageIndexStr) {
		logger.debug("导出判断     The method======= export() start.......................");
		try {
			logger.info("is==" + is);
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
			prepareReport(jasperReport, type);
			JRDataSource ds = new JRBeanCollectionDataSource(datas, false);
			Map parameters = new HashMap();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
			if (EXCEL_TYPE.equals(type)) {
				exportExcel(jasperPrint, defaultFilename, request, response);
			} else if (PDF_TYPE.equals(type)) {
				exportPdf(jasperPrint, defaultFilename, request, response);
			} else if (WORD_TYPE.equals(type)) {
				exportWord(jasperPrint, defaultFilename, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}