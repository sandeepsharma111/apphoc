package com.ths.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ths.dao.DbDao;
import com.ths.domain.FileMetaData;
import com.ths.domain.datatable.DataTablesOutput;
import com.ths.domain.datatable.DataTablesRequest;
import com.ths.service.DbService;
@Service
@Transactional
public class DbServiceImpl implements DbService {
    @Autowired
	private DbDao dao;

	@Override
	public DataTablesOutput<FileMetaData> getAllFiles(DataTablesRequest dataTablesRequest){
		return dao.getAllFiles(dataTablesRequest);
	}

	@Override
	@Transactional(readOnly=false)
	public FileMetaData saveFileData(FileMetaData fileMetaData) {
		return dao.saveFileData(fileMetaData);
	}

	@Override
	@Transactional(readOnly=false)
	public Integer updateExceptionRised(FileMetaData fileMetaData) {
		return dao.updateExceptionRised(fileMetaData);
	}

	@Override
	@Transactional(readOnly=false)
	public Integer updateMatched(String status,Integer ID) {
		
		return dao.updateMatched(status,ID);
	}

	@Override
	@Transactional(readOnly=true)
	public FileMetaData getFileMetaDataById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getFileMetaDataById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public DataTablesOutput<FileMetaData> getSearchFile(DataTablesRequest dataTablesRequest) {
		// TODO Auto-generated method stub
		 return dao.getSearchFile(dataTablesRequest);
	}
	
}
