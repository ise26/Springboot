package com.imageupload.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.imageupload.entity.UploadImge;
import com.imageupload.repository.ImageRepository;

import antlr.StringUtils;

@Controller
public class ImageUplaodController {

	@Autowired
	private ImageRepository imgrepo;
	
	public final String UPLOAD_DIR="C:\\Users\\Shubham.Ise\\Documents\\workspace-spring-tool-suite-4-4.14.1.RELEASE\\ImageUpload\\src\\main\\resources\\static\\images";

	@GetMapping("/home")
	public String indexPage() {
		return "index";
	}
	
	
	@GetMapping("/output")
	public String op() {
		return "output";
	}
	
	
	@PostMapping("/upload")
	public String UploadImage(@RequestParam("image") MultipartFile file,Model model,UploadImge img) {
		String filename=file.getOriginalFilename();
		
		Path fileNameAndPath= Paths.get(UPLOAD_DIR, file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuilder name=new StringBuilder();
		name.append(fileNameAndPath);
	    String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

		img.setImg("/images/" +fileName);
		imgrepo.save(img);
		
		model.addAttribute("msg", name);
		return "index";
	}
	
	@PostMapping("/show")
	public String ShowOutput(Model model,@RequestParam("id") Long img) {
		
		Optional<UploadImge> img1=imgrepo.findById(img);
		String imagename=img1.get().getImg();
		System.out.println(imagename);
		model.addAttribute("msg",imagename);
		return "output";
	}
	
	
}
