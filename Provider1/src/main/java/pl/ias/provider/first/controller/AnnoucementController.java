package pl.ias.provider.first.controller;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnoucementController {

	static String xmlSource ;
	
	@RequestMapping("annoucements")
	public Object getAnnoucements() throws FileNotFoundException{
		FileInputStream fis = new FileInputStream(xmlSource);
		XMLDecoder xde= new XMLDecoder(fis);
		return xde.readObject();
	}
	
	@RequestMapping("xmlSource")
	public String setXmlSource(@RequestParam("xmlSource") String xmlSource){
		this.xmlSource=xmlSource;
		return this.xmlSource;
	}
}
