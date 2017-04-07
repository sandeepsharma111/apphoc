package com.ths.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ths.dao.DbDao;
import com.ths.domain.FileMetaData;
import com.ths.domain.datatable.Column;
import com.ths.domain.datatable.DataTablesOutput;
import com.ths.domain.datatable.DataTablesRequest;
@Repository
public class DbDaoImpl implements DbDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public  DataTablesOutput<FileMetaData>getAllFiles(DataTablesRequest dataTablesRequest){
		DataTablesOutput<FileMetaData> dtl=new DataTablesOutput<FileMetaData>();
		String search = "";
		String opUnit=dataTablesRequest.operationalUnit;
		String uplodedBy= dataTablesRequest.uploadedBy;
		String status= dataTablesRequest.status;
		String date= dataTablesRequest.sDate;
		if(opUnit!=null&&opUnit.length()>0)
		{
			search+=" operatingunit='"+opUnit+"'  ";
		}
		if(uplodedBy!=null&&uplodedBy.length()>0)
		{
			search+=(search=="")?"uploadedby='"+uplodedBy+"' ":" and uploadedby='"+uplodedBy+"'  ";
		}
		if(status!=null&&status.length()>0)
		{
			search+=(search=="")?"status='"+status+"' ":" and status='"+status+"' ";
		}
		if(date!=null&&date.length()>0)
		{
			search+=(search=="")?"createddate='"+date+"' ":" and createddate='"+date+"' ";
		}
		if(search.length()>0)
			search=" where "+search;
		String query=" select count(*)  FROM FileMetaData  "+search+"  ";
		
		int currPosition = dataTablesRequest.displayStart;
		int pageSize = dataTablesRequest.iDisplayLength;
		Long totalrec =(long) ( (Long) this.factory  
				.getCurrentSession()
				.createQuery(query)
				.uniqueResult())
				.intValue();
		List<FileMetaData> lst = factory
				.getCurrentSession()
				.createQuery("  FROM FileMetaData   "+search+"  ")
				.setMaxResults(pageSize).setFirstResult(currPosition).list();
		
		
		dtl.data = lst;
		dtl.draw = dataTablesRequest.draw;
		dtl.recordsTotal = totalrec;
		dtl.recordsFiltered=totalrec;
		return dtl;
	}

	@Override
	public FileMetaData saveFileData(FileMetaData fileMetaData) {
		return (FileMetaData) this.factory.getCurrentSession().merge(fileMetaData);
	}

	@Override
	public Integer updateExceptionRised(FileMetaData fileMetaData) {
		Integer updatedrow=0;
		//System.out.println(fileMetaData+"    ::::"+fileMetaData.getID());
		String sql= " UPDATE file_meta_data  AS fmd SET fmd.vendorname=:vendorName ,"
					+ " fmd.currency=:currency, fmd.baseamount=:baseAmt,fmd.taxamount=:taxAmt ,"
					+ "fmd.totalamount=:totalAmt ,fmd.ponumber=:poNumber "
	                +   " ,fmd.exentereddate =:extenderDate ,fmd.invoicedate=:invoiceDate  ,"
	                + "fmd.exceptionreason=:exceptionReason ,fmd.status='EXCEPTION' WHERE fmd.ID=:id ";
		try{
			System.out.println(sql);
		 updatedrow=	this.factory.getCurrentSession().createSQLQuery(sql)
			.setParameter("vendorName", fileMetaData.getVendorname())
			.setParameter("currency", fileMetaData.getCurrency())
			.setParameter("baseAmt", fileMetaData.getBaseamount())
			.setParameter("taxAmt", fileMetaData.getTaxamount())
			.setParameter("totalAmt", fileMetaData.getTotalamount())
			.setParameter("poNumber", fileMetaData.getPonumber())
			.setParameter("extenderDate", fileMetaData.getExentereddate())
			.setParameter("invoiceDate", fileMetaData.getInvoicedate())
			.setParameter("exceptionReason", fileMetaData.getExceptionreason())
			.setParameter("id", fileMetaData.getPrimaryId()).executeUpdate();
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
		return updatedrow;
		
	}

	@Override
	public Integer updateMatched(String status,Integer ID) {
	
		
		return this.factory.getCurrentSession().createSQLQuery("UPDATE file_meta_data  fmd SET fmd.status=:status where fmd.ID=:id ")
				.setParameter("status", status).setParameter("id", ID).executeUpdate();
	}

	@Override
	public FileMetaData getFileMetaDataById(Integer id) {
		
		FileMetaData data=  (FileMetaData)this.factory.getCurrentSession().createQuery("from FileMetaData fmd where fmd.ID=:id ")
				.setParameter("id", id)
				.uniqueResult();
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataTablesOutput<FileMetaData> getSearchFile(DataTablesRequest dataTablesRequest) {
	
		DataTablesOutput<FileMetaData> dtl=new DataTablesOutput<FileMetaData>();
		String search = "";
		String opUnit = dataTablesRequest.operationalUnit;
		String status = dataTablesRequest.status;
		String sdate = dataTablesRequest.sDate;
		String vendorName = dataTablesRequest.vendorName;
		String doccumentname = dataTablesRequest.doccumentname;
		String poNumber = dataTablesRequest.poNumber;
		String invoiceNumber= dataTablesRequest.invoiceNumber;
		
		
		if(opUnit!=null&&opUnit.length()>0)
		{
			search+=" operatingunit='"+opUnit+"'  ";
		}
		if(sdate!=null&&sdate.length()>0)
		{
			search+=(search=="")?"invoicedate='"+sdate+"' ":" and invoicedate='"+sdate+"'  ";
		}
		if(status!=null&&status.length()>0)
		{
			search+=(search=="")?"status='"+status+"' ":" and status='"+status+"' ";
		}
		if(vendorName!=null&&vendorName.length()>0)
		{
			search+=(search=="")?"vendorName='"+vendorName+"' ":" and vendorName='"+vendorName+"' ";
		}
		
		if(doccumentname!=null&&doccumentname.length()>0)
		{
			search+=(search=="")?"doccumentname='"+doccumentname+"' ":" and doccumentname='"+doccumentname+"'  ";
		}
		if(poNumber!=null&&poNumber.length()>0)
		{
			search+=(search=="")?"poNumber='"+poNumber+"' ":" and poNumber='"+poNumber+"' ";
		}
		if(invoiceNumber!=null&&invoiceNumber.length()>0)
		{
			search+=(search=="")?"invoiceNumber='"+invoiceNumber+"' ":" and invoiceNumber='"+invoiceNumber+"' ";
		}
		if(search.length()>0)
			search=" where "+search;
		
		
		String query=" select count(*)  FROM FileMetaData  "+search+"  ";
		
		int currPosition = dataTablesRequest.displayStart;
		int pageSize = dataTablesRequest.iDisplayLength;
		Long totalrec =(long) ( (Long) this.factory  
				.getCurrentSession()
				.createQuery(query)
				.setMaxResults(pageSize).setFirstResult(currPosition)
				.uniqueResult())
				.intValue();
		System.out.println(totalrec);
		System.out.println(currPosition+"      ***************************************      "+pageSize);
		List<FileMetaData> lst = factory
				.getCurrentSession()
				.createQuery("  FROM FileMetaData   "+search+"  ")
				.setMaxResults(pageSize).setFirstResult(currPosition).list();
		
		
		dtl.data = lst;
		dtl.draw = dataTablesRequest.draw;
		dtl.recordsTotal = totalrec;
		dtl.recordsFiltered=totalrec;
		return dtl;
	}
}
