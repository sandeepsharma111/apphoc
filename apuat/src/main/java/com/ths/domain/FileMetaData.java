package com.ths.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="msi_gi_poc_ap_metadata_db_dev")
public class FileMetaData {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="poc_id")
	private Integer ID;
	@Column(name="documentname")
	private String documentname;
	@Column(name="documentid")
	private String documentid; 
	@Column(name="filename")
	private String pdfname ;
	@Column(name="status")
	private String status;
	@Column(name="operatingunit")
	private String operatingunit ;
	@Column(name="createddate")
	private Date createddate ;
	@Column(name="createdby")
	private String createdby ;
	@Column(name="modifieddate")
	private Date modifieddate ;
	@Column(name="modifiedby")
	private String modifedby ;
	@Column(name="uploadedby")
	private String uploadedby;
	@Column(name="vendorname")
	private String vendorname ;
	@Column(name="invoicenumber")
	private String invoicenumber ;
	@Column(name="currency")
	private String currency ;
	@Column(name="baseamount")
	private String baseamount ;
	@Column(name="taxamount")
	private String taxamount ;
	@Column(name="totalamount")
	private String totalamount ;
	@Column(name="ponumber")
	private String ponumber ;
	@Column(name="invoicedate")
	private Date invoicedate ;
	@Column(name="exceptionreason")
	private String exceptionreason ;
	@Column(name="exentereddate")
	private Date exentereddate ;
	@Column(name="reserved1")
	private String reserved1;
	@Column(name="reserved2")
	private String reserved2;
	@Column(name="reserved3")
	private String reserved3;
	@Column(name="reserved4")
	private String reserved4;
	
	@Transient
	private Integer primaryId;
	
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getDocumentname() {
		return documentname;
	}
	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}
	public String getDocumentid() {
		return documentid;
	}
	public void setDocumentid(String documentid) {
		this.documentid = documentid;
	}
	public String getPdfname() {
		return pdfname;
	}
	public void setPdfname(String pdfname) {
		this.pdfname = pdfname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperatingunit() {
		return operatingunit;
	}
	public void setOperatingunit(String operatingunit) {
		this.operatingunit = operatingunit;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getModifedby() {
		return modifedby;
	}
	public void setModifedby(String modifedby) {
		this.modifedby = modifedby;
	}
	public String getUploadedby() {
		return uploadedby;
	}
	public void setUploadedby(String uploadedby) {
		this.uploadedby = uploadedby;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public String getInvoicenumber() {
		return invoicenumber;
	}
	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBaseamount() {
		return baseamount;
	}
	public void setBaseamount(String baseamount) {
		this.baseamount = baseamount;
	}
	public String getTaxamount() {
		return taxamount;
	}
	public void setTaxamount(String taxamount) {
		this.taxamount = taxamount;
	}
	public String getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
	public String getPonumber() {
		return ponumber;
	}
	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}
	public Date getInvoicedate() {
		return invoicedate;
	}
	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}
	public String getExceptionreason() {
		return exceptionreason;
	}
	public void setExceptionreason(String exceptionreason) {
		this.exceptionreason = exceptionreason;
	}
	public Date getExentereddate() {
		return exentereddate;
	}
	public void setExentereddate(Date exentereddate) {
		this.exentereddate = exentereddate;
	}
	
	public Integer getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Integer primaryId) {
		this.primaryId = primaryId;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	
}
