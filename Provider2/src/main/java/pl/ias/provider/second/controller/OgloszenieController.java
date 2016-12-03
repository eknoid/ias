package pl.ias.provider.second.controller;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OgloszenieController {

	static String xmlSource ;

	@RequestMapping("ogloszenia")
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
