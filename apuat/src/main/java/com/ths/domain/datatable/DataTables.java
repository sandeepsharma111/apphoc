package com.ths.domain.datatable;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataTables<T> implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "iTotalRecords")
	public int totalRecords;

	@JsonProperty(value = "iTotalDisplayRecords")
	public int totalDisplayRecords;

	@JsonProperty(value = "sEcho")
	public String echo;

	@JsonProperty(value = "sColumns")
	public String columns;

	@JsonProperty(value = "aaData")
	public List<T> data = new ArrayList<T>();

}
