package com.vz.tg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vz.tg.model.HomeBean;
import com.vz.tg.services.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	HomeService homeservice ;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		
		//model = new object.. set values and set to MAV..
		
		SolrQuery query = new SolrQuery("id:*");
		query.set("group", true);
		query.set("group.field", "sentimentScore");
		query.addSort("sentimentScore",SolrQuery.ORDER.asc);
		HomeBean bean = new HomeBean();
		try{
			Collection<JSONObject> dataList = new ArrayList<JSONObject>();
			TreeMap<String,Long> dataListObj = new TreeMap<String,Long>();
			
			JSONObject timeObject = new JSONObject();
			timeObject.put("date", "10-30-2015");
			timeObject.put("bytes", 1023456);
			dataList.add(timeObject);
			dataListObj.put("10-30-2015", (long)1023456);
			
			
			JSONObject timeObject1 = new JSONObject();
			timeObject1.put("date", "10-29-2015");
			timeObject1.put("bytes", 102345126);
			dataList.add(timeObject1);
			dataListObj.put("10-29-2015", (long)102345126);
			
			JSONObject timeObject2 = new JSONObject();
			timeObject2.put("date", "10-28-2015");
			timeObject2.put("bytes", 10213456);
			dataList.add(timeObject2);
			dataListObj.put("10-28-2015", (long)10213456);
			
			JSONObject timeObject3 = new JSONObject();
			timeObject3.put("date", "10-27-2015");
			timeObject3.put("bytes", 102341);
			dataList.add(timeObject3);
			dataListObj.put("10-27-2015", (long)102341);
			
			JSONObject timeObject4 = new JSONObject();
			timeObject4.put("date", "10-26-2015");
			timeObject4.put("bytes", 10123456);
			dataList.add(timeObject4);
			dataListObj.put("10-26-2015", (long)10123456);
			
			JSONObject timeObject5 = new JSONObject();
			timeObject5.put("date", "10-25-2015");
			timeObject5.put("bytes", 1104);
			dataList.add(timeObject5);
			dataListObj.put("10-25-2015", (long)1104);
			
			JSONObject timeObject6 = new JSONObject();
			timeObject6.put("date", "10-24-2015");
			timeObject6.put("bytes", 10234516);
			dataList.add(timeObject6);
			dataListObj.put("10-24-2015", (long)10234516);
			
			JSONObject finalObj = new JSONObject();
			finalObj.put("dataListByDate", dataList);
			bean.setDataListRecords(dataListObj);
			bean.setDataUsageList(finalObj);
			
			/*QueryResponse response = homeservice.getServiceResponse(query);
			if(response!=null){
				 SolrDocumentList responseList = response.getResults();
				 long totalCount = responseList.getNumFound();
				 if(totalCount>0){
					 bean.setResultCount(totalCount);
				 }
				 List<GroupCommand> list = response.getGroupResponse().getValues();
				 GroupCommand groupList = list.get(0);
				 long totalCount = groupList.getMatches();
				 bean.setResultCount(totalCount);
				 List<Group> groups = groupList.getValues();
				 
				 long positivaTotal =0;
				 long neutralTotal =0;
				 if(groups!=null && groups.size()>0){
					 for(Group grp:groups){
						 String grpValue = grp.getGroupValue();
						 SolrDocumentList docList = grp.getResult();
						 long numFound = docList.getNumFound();
						 if(grpValue!=null){
							 int sentiment_val =  Integer.parseInt(grpValue);
							 switch(sentiment_val){
							 	case 0://Negative Needs immediate Attention....
							 		bean.setNegativeCount(numFound);
							 		break;
							 	case 1:
							 		neutralTotal = neutralTotal+numFound;
							 		break;
							 	case 2:
							 		//bean.setNeutralCount(numFound);
							 		neutralTotal = neutralTotal+numFound;
							 		break;
							 	case 3:
							 		positivaTotal = positivaTotal+numFound;
							 		break;
							 	case 4:
							 		positivaTotal = positivaTotal+numFound;
							 		break;
							 }
						 }
					 }
				 }
				 bean.setPositiveCount(positivaTotal);
				 bean.setNeutralCount(neutralTotal);
			}
			
			query.set("group", true);
			query.set("group.field", "agegroup");
			query.addSort("agegroup",SolrQuery.ORDER.asc);
			response = homeservice.getServiceResponse(query);
			if(response!=null){
				List<GroupCommand> list = response.getGroupResponse().getValues();
				 GroupCommand groupList = list.get(0);
				 //long totalCount = groupList.getMatches();
				 //bean.setResultCount(totalCount);
				 List<Group> groups = groupList.getValues();
				 Collection<JSONObject> jsonList = new ArrayList<JSONObject>();
				 if(groups!=null && groups.size()>0){
					 for(Group grp:groups){
						 String grpValue = grp.getGroupValue();
						 SolrDocumentList docList = grp.getResult();
						 long numFound = docList.getNumFound();
						 if(grpValue!=null && !"".equalsIgnoreCase(grpValue.trim())){
							 JSONObject json = new JSONObject();
							 json.put("label", grpValue);
							 json.put("value", numFound);
							 jsonList.add(json);
						 }
					 }
				 }
				 JSONObject finalObj = new JSONObject();
				 finalObj.put("agegroup", jsonList);
				 bean.setAgeGroup(finalObj);
			}
			
			DateFormat dateFormat = new SimpleDateFormat ("E MMM d hh:mm:ss zzz yyyy");
			SimpleDateFormat newFormat = new SimpleDateFormat("MM-dd-yyyy");
			query.addFacetPivotField("tweetPostedTime,tweet_category");
			query.addSort("tweetPostedTime",SolrQuery.ORDER.asc);
			query.setRows(0);
			JSONObject finalObj = new JSONObject();
			response = homeservice.getServiceResponse(query);
			List<Date> myList = new ArrayList<Date>();
			JSONObject tempObj = new JSONObject();
			Collection<JSONObject> categoryLst = new ArrayList<JSONObject>();
			if(response!=null){
				System.out.println(response);
				NamedList<List<PivotField>> pivotNamedList = response.getFacetPivot();
				System.out.println(pivotNamedList.size());
				for( int i=0;i<pivotNamedList.size();i++){
					List<PivotField> pvotList = pivotNamedList.getVal(i);
					for(PivotField pf:pvotList){
						if(pf!=null){
							System.out.println(pf.getValue());
							Date beforeParse = dateFormat.parse(pf.getValue().toString());
							myList.add(beforeParse);
							JSONObject categoryObj = new JSONObject();
							categoryObj.put("period", newFormat.format(beforeParse));
							//System.out.println(pf.getField());
							List<PivotField> categoryList = pf.getPivot();
							int smartPhoneTotal =0;
							int tabletsTotal =0;
							int productsTotal =0;
							for(PivotField categorypivot:categoryList){
								if(categorypivot!=null){
									if(categorypivot.getValue()!=null){
										String fullCatName = categorypivot.getValue().toString();
										if(fullCatName.contains("Smartphones")){
											smartPhoneTotal = smartPhoneTotal +categorypivot.getCount();
										}else if(fullCatName.contains("Tablets")){
											tabletsTotal = tabletsTotal +categorypivot.getCount();
										}else if(fullCatName.contains("Products")){
											productsTotal = productsTotal +categorypivot.getCount();
										}
									}
									
								}
							}
							categoryObj.put("Smartphones", smartPhoneTotal);
							categoryObj.put("Tablets", tabletsTotal);
							categoryObj.put("Products", productsTotal);
							tempObj.put(newFormat.format(beforeParse), categoryObj);
							//categoryLst.add(categoryObj);
						}
						
					}
					
				}
				//finalObj.put("categoryData", categoryLst);
			}
			
			Collections.sort(myList);
			Iterator<Date> itr = myList.iterator();
			while(itr.hasNext()){
				
				Date inputDate = dateFormat.parse(itr.next().toString());
				
				String tempKey = newFormat.format(inputDate);
				System.out.println(tempKey);
				JSONObject newObj = (JSONObject)tempObj.get(tempKey);
				categoryLst.add(newObj);
				
			}
			finalObj.put("categoryData", categoryLst);
			bean.setCategoryData(finalObj);
			
			String tweetType="*";
			Collection<JSONObject> tweetCollection = getTweetResponse(tweetType,0,10);
			bean.setEndRecord(10);
			if(tweetCollection!=null){
				LinkedHashMap<String, String> tweetList = new LinkedHashMap<String, String>();
				for(JSONObject tweet:tweetCollection){
					if(tweet!=null){
						tweetList.put((String)tweet.get("id"), (String)tweet.get("tweet"));
					}
				}
				bean.setTweetList(tweetList);
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("home", "model", bean);
		logger.info("bye now");
		return mav;
	}
	@RequestMapping(value = "/tweetresponse", method = RequestMethod.GET)
	@ResponseBody
	
	public String getTweets(@RequestParam("type") String type,@RequestParam("start") int start,@RequestParam("end") int end) {
		
		logger.info("Welcome to Tweets");
		JSONObject finalObject = new JSONObject();
		
		int startRow =0;
		int endRow=10;
		String tweetType="*";
		if(type!=null && type.equalsIgnoreCase("positive")){
			tweetType ="[3 TO 4]";
		}else if(type!=null && type.equalsIgnoreCase("neutral")){
			tweetType = "[1 TO 2]";
		}else if(type!=null && type.equalsIgnoreCase("negative")){
			tweetType = "0";
		}
		if(start>startRow){
			startRow =start;
		}
		if(end>endRow){
			endRow=end;
		}
		
		Collection<JSONObject> objectList = getTweetResponse(tweetType,startRow,endRow);
		finalObject.put("tweetList", objectList);
		logger.info("bye Tweets");
		return finalObject.toString();
	}
	
	public Collection<JSONObject> getTweetResponse(String tweetType,int startRow,int endRow){
		SolrQuery query = new SolrQuery("sentimentScore:"+tweetType);
		query.setStart(startRow);
		query.setRows(endRow);
		HomeBean bean = new HomeBean();
		Collection<JSONObject> objectList = new ArrayList<JSONObject>();
		try{
		QueryResponse response = homeservice.getServiceResponse(query);
		if(response!=null){
			 SolrDocumentList responseList = response.getResults();
			 long totalCount = responseList.getNumFound();
			 if(totalCount>0){
				 bean.setResultCount(totalCount);
			 }
			  
			 for(SolrDocument document:responseList){
				 JSONObject json = new JSONObject();
				 json.put("id", document.getFieldValue("id"));
				 json.put("tweet", document.getFieldValue("tweet_content"));
				 objectList.add(json);
			 }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return objectList;
	}
}
