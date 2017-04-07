package com.ths.domain.datatable;

import java.io.Serializable;
import java.util.List;

public class DataTablesRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	/*
	 * public List<Boolean> abRegex; public List<Boolean> abSearchable; public
	 * List<Boolean> abSortable; public List<Integer> aiSortCol; public
	 * List<String> amDataProp; public List<String> asSearch; public
	 * List<String> asSortDir;
	 * 
	 * public boolean bRegex; public int iColumns; public int iDisplayLength;
	 * public int iDisplayStart; public int iSortingCols; public String
	 * sColumns; public String sEcho; public String sSearch; public Long id;
	 * public Long catId; public Long subCatId; }
	 */
	public List<Column> columns;
	public List<Order> order;
	public Search search;
	public int draw;
	public int displayStart;
	public int iDisplayLength;
	public Long catId; 
	public Long subCatId; 
	public Long quizId;
	public String operationalUnit;
	public String status;
	public String uploadedBy;
	public String sDate;
	public String eDate;
	public String vendorName;
	public String invoiceNumber;
	public String doccumentname;
	public String poNumber;
 	
}

