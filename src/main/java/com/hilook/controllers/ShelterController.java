package com.hilook.controllers;

import com.hilook.beans.vo.ShelterVO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shelter/*")
public class ShelterController {
		
    	@GetMapping("/list")
    	public ModelAndView list(Model model,
			@RequestParam(value = "care_nm", defaultValue = "") String care_nm,
			@RequestParam(defaultValue = "1") int pageNo) throws IOException {

        int numOfRows = 10;
        int offset = (pageNo - 1) * numOfRows;

    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=TqJb2EMvQ4ga3%2Flmz63uvvv3TTJmkEO9qKkfhmngKmCwQcuWYSb%2FkfdLmZObp6hdIJ0WmapQa9PU8Z7OGIv24w%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호센터등록번호*/
        urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode(care_nm, "UTF-8")); /*동물보호센터명*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + (numOfRows)); /*한 페이지 결과 수 (1,000 이하)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + (pageNo)); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml(기본값) 또는 json*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        
//      JSON 데이터 파싱 및 추출
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.optJSONObject("items");
        JSONArray itemList = null;
        if (!items.isEmpty()) {
            itemList = items.getJSONArray("item");
        }    
    
//      itemList 처리 로직
        List<ShelterVO> itemNames = new ArrayList<>();
        if (itemList != null) {
            for (int i = 0; i < itemList.length(); i++) {
            	JSONObject item = itemList.getJSONObject(i);
            	ShelterVO shelterVO = new ShelterVO();
            	
            	shelterVO.setCareNm(item.getString("careNm"));
            	shelterVO.setCareAddr(item.getString("careAddr"));
            	shelterVO.setCareTel(item.getString("careTel"));

            	
            	if (item.has("saveTrgtAnimal")) {
            	    shelterVO.setSaveTrgtAnimal(item.getString("saveTrgtAnimal"));
            	} else {
            	    shelterVO.setSaveTrgtAnimal(null);
            	}
            	
                itemNames.add(shelterVO);
                
            }
        }
        
//      ModelAndView 객체를 생성하고 뷰 이름과 모델을 설정
        ModelAndView modelAndView = new ModelAndView("shelter/list");
        modelAndView.addObject("items", itemNames);
        modelAndView.addObject("pageNo", pageNo);
        
//      ModelAndView 객체를 반환
        return modelAndView;

    }
}