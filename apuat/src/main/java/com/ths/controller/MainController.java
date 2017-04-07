package com.ths.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ths.domain.FileMetaData;
import com.ths.domain.datatable.DataTablesOutput;
import com.ths.domain.datatable.DataTablesRequest;
import com.ths.service.DbService;
import com.ths.util.AppS3Client;

@Controller
public class MainController {

	@Autowired
	private DbService sbService;
	
	@Autowired
	AppS3Client s3client;
	@GetMapping("/")
	public String root() 
	{
		return "home";
	}
	@GetMapping("/home")
	public String home() 
	{
		return "home";
	}
	@GetMapping("/process")
	public String process() 
	{
		return "process";
	}
	
	@GetMapping("/search")
	public String search() 
	{
		return "search";
	}
	@GetMapping("/viewfile")
	public String viewfile(@RequestParam Integer id,ModelMap map) 
	{
		map.addAttribute("id",id);
		return "viewfile";
	}
	@GetMapping("/file/{id}")
	public void file(HttpServletResponse response, @PathVariable Integer id,ModelMap map) throws IOException 
	{
		FileMetaData fileMetaData= sbService.getFileMetaDataById(id);
		if(fileMetaData!=null)
		{
			
		   InputStream is=	s3client.getS3File(fileMetaData.getPdfname());
		   System.out.println(fileMetaData.getPdfname());
		   org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
		   response.flushBuffer();
		}
	}
	@RequestMapping(value="/upload",consumes = {"multipart/form-data"},method=RequestMethod.POST)
	public String upload(@RequestParam("uploadFile[]") MultipartFile[] files,@RequestParam("opunit") String operationalUnit,ModelMap  map) throws IOException
	{
		
		for(int i=0;i<files.length;i++){
			MultipartFile file= files[i];
			if(file!=null && file.getSize()>0){
			System.out.println("*************************"+file.getSize());
				FileMetaData fileMetaData= new FileMetaData();
				fileMetaData.setCreatedby("Jhon");
				fileMetaData.setStatus("NEW");
				fileMetaData.setCreateddate(new Date());
				fileMetaData.setDocumentname(file.getOriginalFilename());
				fileMetaData.setUploadedby("Jhon");
				fileMetaData.setOperatingunit(operationalUnit);
				final String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				String extension=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."), file.getOriginalFilename().length());
				fileMetaData.setDocumentid(uuid);
				fileMetaData.setPdfname(uuid+extension);
				sbService.saveFileData(fileMetaData);
				s3client.saveS3File(uuid+extension, file.getInputStream());
				map.addAttribute("message", "File uploaded Successfully ");
			}else{
				map.addAttribute("message", "Please Upload at least One file ");
			}
		}
		
		return "home";
	}
	
	@RequestMapping(value="/getfilemetadata",method=RequestMethod.POST)
	@ResponseBody public Object getfilemetadat(@RequestBody DataTablesRequest dataTable){
		System.out.println(dataTable.iDisplayLength);
		DataTablesOutput<FileMetaData> output=    sbService.getAllFiles(dataTable);
		 return output;
		
	}
	
	@RequestMapping(value="/update_exception_rised",method=RequestMethod.POST)
	@ResponseBody public String UpdateExceptionRised(@RequestBody FileMetaData fileMetaData){
		
	Integer updateRow=	sbService.updateExceptionRised(fileMetaData);
	if(updateRow>0){
		return "Exception is Updated Successfully ";
	}	else{
		return "Exception is Not Updated ";	
	}
	
	
	}
	@RequestMapping(value="/update_status",method=RequestMethod.GET)
	@ResponseBody public String  StringupdateStatus(@RequestParam String status , @RequestParam Integer ID){
		System.out.println(status+"                 :"+ID);
		Integer updaterow = sbService.updateMatched(status, ID);
		if(updaterow>0){
			 return "Matched  Successfully ";	
		}else{
			 return "Staus is not Updated ";
		}
		
		
	}
	
	@RequestMapping(value="/get_search_file_data",method=RequestMethod.POST)
	@ResponseBody public Object getSearchFileMetaData(@RequestBody DataTablesRequest dataTable){
		System.out.println( "***************888"+dataTable.iDisplayLength);
		DataTablesOutput<FileMetaData> output=    sbService.getSearchFile(dataTable);
		 return output;
		
	}
}
