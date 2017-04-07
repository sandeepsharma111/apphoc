package com.ths.domain.datatable;

import java.util.ArrayList;
import java.util.List;


public class DataTablesOutput<T> {
	public int draw;
	public Long recordsTotal;
	public Long recordsFiltered;
	public List<T> data = new ArrayList<T>();
}
