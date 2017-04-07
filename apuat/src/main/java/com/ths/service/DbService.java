package com.ths.service;

import com.ths.domain.FileMetaData;
import com.ths.domain.datatable.DataTablesOutput;
import com.ths.domain.datatable.DataTablesRequest;

public interface DbService {
	 DataTablesOutput<FileMetaData> getAllFiles(DataTablesRequest dataTablesRequest);
	 FileMetaData saveFileData(FileMetaData fileMetaData);
	 Integer updateExceptionRised(FileMetaData fileMetaData);
	 Integer updateMatched(String status,Integer ID);
	 FileMetaData getFileMetaDataById(Integer id);
	 DataTablesOutput<FileMetaData> getSearchFile(DataTablesRequest dataTablesRequest);
}
