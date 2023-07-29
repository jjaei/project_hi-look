package com.hilook.controllers;

import com.hilook.beans.vo.AnimalVO;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/animal/*")
public class AnimalController {
	
	List<AnimalVO> itemNames;
	private AnimalVO selectedAnimal;
	
	@GetMapping("/list")
	public ModelAndView list(Model model, 
		@RequestParam(value = "upr_cd", defaultValue = "") String upr_cd,
		@RequestParam(value = "upkind", defaultValue = "") String upkind,
		@RequestParam(defaultValue = "1") int pageNum) throws IOException {
	
        int itemsPerPage = 1000;
        int offset =(pageNum - 1) * itemsPerPage;
        
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic"); /*URL*/
		
		LocalDate currentDate = LocalDate.now();
		LocalDate startDate = currentDate.minusMonths(3);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

		String encodedStartDate = URLEncoder.encode(startDate.format(formatter), "UTF-8");
		String encodedEndDate = URLEncoder.encode(currentDate.format(formatter), "UTF-8");
		
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=TqJb2EMvQ4ga3%2Flmz63uvvv3TTJmkEO9qKkfhmngKmCwQcuWYSb%2FkfdLmZObp6hdIJ0WmapQa9PU8Z7OGIv24w%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("bgnde","UTF-8") + "=" + (encodedStartDate)); /*유기날짜(검색 시작일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("endde","UTF-8") + "=" + (encodedEndDate)); /*유기날짜(검색 종료일) (YYYYMMDD)*/
        urlBuilder.append("&" + URLEncoder.encode("upkind","UTF-8") + "=" + upkind); /*축종코드 (개 : 417000, 고양이 : 422400, 기타 : 429900)*/
        urlBuilder.append("&" + URLEncoder.encode("kind","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*품종코드 (품종 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("upr_cd","UTF-8") + "=" + upr_cd); /*시도코드 (시도 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*시군구코드 (시군구 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("care_reg_no","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*보호소번호 (보호소 조회 OPEN API 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("state","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*상태(전체 : null(빈값), 공고중 : notice, 보호중 : protect)*/
        urlBuilder.append("&" + URLEncoder.encode("neuter_yn","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*상태 (전체 : null(빈값), 예 : Y, 아니오 : N, 미상 : U)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + (pageNum)); /*페이지 번호 (기본값 : 1)*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + (itemsPerPage)); /*페이지당 보여줄 개수 (1,000 이하), 기본값 : 10*/
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
//      System.out.println(sb.toString());
     
        
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
        itemNames = new ArrayList<>();
        if (itemList != null) {
            for (int i = 0; i < itemList.length(); i++) {
            	JSONObject item = itemList.getJSONObject(i);
            	AnimalVO animalVO = new AnimalVO();
            	
            	animalVO.setDesertionNo(item.getString("desertionNo"));
            	animalVO.setFilename(item.getString("filename"));
            	animalVO.setHappenDt(item.getString("happenDt"));
            	animalVO.setHappenPlace(item.getString("happenPlace"));
            	animalVO.setKindCd(item.getString("kindCd"));
            	animalVO.setColorCd(item.getString("colorCd"));
            	animalVO.setAge(item.getString("age"));
            	animalVO.setWeight(item.getString("weight"));
            	animalVO.setNoticeNo(item.getString("noticeNo"));
            	animalVO.setNoticeSdt(item.getString("noticeSdt"));
            	animalVO.setNoticeEdt(item.getString("noticeEdt"));
            	animalVO.setPopfile(item.getString("popfile"));
            	animalVO.setProcessState(item.getString("processState"));
            	animalVO.setSexCd(item.getString("sexCd"));
            	animalVO.setNeuterYn(item.getString("neuterYn"));
            	animalVO.setSpecialMark(item.getString("specialMark"));
            	animalVO.setCareNm(item.getString("careNm"));
            	animalVO.setCareTel(item.getString("careTel"));
            	animalVO.setCareAddr(item.getString("careAddr"));
                String detailUrl = "/animal/detail" + item.getString("desertionNo");
                animalVO.setDetailUrl(detailUrl);
                itemNames.add(animalVO);
                
            }
        }
    	
        String totalCount = body.get("totalCount").toString();
        System.out.println( "전체 카운트 : " + totalCount);
        
//      ModelAndView 객체를 생성하고 뷰 이름과 모델을 설정
        ModelAndView modelAndView = new ModelAndView("animal/list");
        modelAndView.addObject("items", itemNames);
        modelAndView.addObject("pageNum", pageNum);
        model.addAttribute("startDate", encodedStartDate);
        model.addAttribute("endDate", encodedEndDate);

//      ModelAndView 객체를 반환
        return modelAndView;
    }    
	@GetMapping("/detail")
    public ModelAndView animalDetail(@RequestParam("desertionNo") String desertionNo) {
   
	 	for(AnimalVO animal : itemNames) {
	 		if (animal.getDesertionNo().equals(desertionNo)) {
	 			selectedAnimal = animal;
				break;
			}
		} 	
        ModelAndView modelAndView = new ModelAndView("animal/detail");
        modelAndView.addObject("selectedAnimal", selectedAnimal);
        return modelAndView;        
	}	
}     