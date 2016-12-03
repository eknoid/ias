package pl.ias.hub.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.ias.hub.model.Agency;
import pl.ias.hub.model.Announcement;
import pl.ias.hub.model.BasicInformation;

@RestController
public class AnnoucementController {

	@RequestMapping("annoucements")
	public List<Announcement> getAnnoucements() {
		try {
			List<Announcement> result = new ArrayList<>();
			getAnnoucementFormProvider(result, getMapperForProvider1(), "http://localhost:8080/Provider1/annoucements");
			getAnnoucementFormProvider(result, getMapperForProvider2(), "http://localhost:8080/Provider2/ogloszenia");
			return result;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}

	private void getAnnoucementFormProvider(List<Announcement> result, Mapper mapper, String url) {
		RestTemplate restTemplate = new RestTemplate();
		
		String json = restTemplate.getForObject(url, String.class);
		
		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++) {
			JSONObject objectToConvert = (JSONObject) array.get(i);
			result.add(mapper.createAnnoucement(objectToConvert));
		}
	}
	
	private Mapper getMapperForProvider1(){
		return new Mapper() {
			@Override
			public Announcement createAnnoucement(JSONObject objectToConvert) {
				Announcement annoucement = new Announcement();
				annoucement.setSubject(objectToConvert.getString("topic"));
				annoucement.setBodyContent(objectToConvert.getString("content"));
				
				BasicInformation basicInformation = new BasicInformation();
				basicInformation.setAddress(objectToConvert.getString("address"));
				basicInformation.setPrice(objectToConvert.getLong("price"));
				basicInformation.setRoomNumber(objectToConvert.getLong("roomQuantity"));
				basicInformation.setSurface(objectToConvert.getLong("surface"));
				
				annoucement.setBasicInformation(basicInformation);
				
				Agency agency = new Agency();
				agency.setAddress(objectToConvert.getJSONObject("agency").getString("address"));
				agency.setName(objectToConvert.getJSONObject("agency").getString("name"));
				agency.setPhoneNumber(objectToConvert.getJSONObject("agency").getString("phoneNumber").replaceAll("-", ""));
				annoucement.setAgency(agency);
				return annoucement;
			}
		};
	}
	
	private Mapper getMapperForProvider2(){
		return new Mapper() {
			@Override
			public Announcement createAnnoucement(JSONObject objectToConvert) {
				Announcement annoucement = new Announcement();
				annoucement.setSubject(objectToConvert.getString("tytul"));
				annoucement.setBodyContent(objectToConvert.getString("tresc"));
				BasicInformation basicInformation = new BasicInformation();
				basicInformation.setAddress(objectToConvert.getJSONObject("adres").getString("miasto")+", "+objectToConvert.getJSONObject("adres").getString("ulica")+" " +objectToConvert.getJSONObject("adres").getLong("numerDomu"));
				basicInformation.setPrice(null);
				basicInformation.setRoomNumber(null);
				basicInformation.setSurface(null);
				annoucement.setBasicInformation(basicInformation);
				Agency agency = new Agency();
				agency.setAddress(null);
				agency.setName(objectToConvert.getString("autor"));
				agency.setPhoneNumber(objectToConvert.getString("numerTelefonu").replace(" ", ""));
				annoucement.setAgency(agency);
				return annoucement;
			}
		};
	}

	
	static interface Mapper{
		Announcement createAnnoucement(JSONObject objectToConvert);
	}
}
