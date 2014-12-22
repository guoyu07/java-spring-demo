package jot.projects.spring.springweb.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import jot.projects.spring.springapp.data.reference.Country;
import jot.projects.spring.springapp.data.reference.State;
import jot.projects.spring.springapp.data.reference.Title;
import jot.projects.spring.springapp.services.ReferenceService;
import jot.projects.spring.springweb.mvc.data.DropDownData;

//import jot.projects.spring.springapp.data.reference.State;
//import jot.projects.spring.springapp.services.ReferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class JsonController {

	@Autowired
	protected ReferenceService refService;

	protected final static Logger log = LoggerFactory
			.getLogger(JsonController.class);

	@RequestMapping(value = "/json/getStates", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getStatesAsJSON() {
		log.debug("Calling JSON service getStatesAsJSON()");
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		Country c = refService.getCountryByCode("CA");
		ArrayList<State> stateList = null;
		if (c != null) {
			stateList = (ArrayList<State>) refService.getStatesByCountry(c);
		} else {	
			log.warn("Country was null. Getting all states / provinces");
			stateList = (ArrayList<State>) refService.getAllStates();
		}
		if (stateList != null && stateList.size() > 0) {
			for (State s: stateList) {
				dropDownList.add(new DropDownData(s.getId(), s.getName() + " (" + s.getCode() + ")"));
			}
		}
		return dropDownList;
	}

	@RequestMapping(value = "/json/getTitles", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getTitlesAsJSON() {
		log.debug("Calling JSON service getTitlesAsJSON()");
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		ArrayList<Title> titleList = (ArrayList<Title>) refService
				.getAllTitles();
		if (titleList != null && titleList.size() > 0) {
			for (Title t : titleList) {
				dropDownList.add(new DropDownData(t.id(), t.name()));
			}
		}
		return dropDownList;
	}

	@RequestMapping(value = "/json/getCountries", method = RequestMethod.GET)
	public @ResponseBody
	List<DropDownData> getCountriesAsJSON() {
		log.debug("Calling JSON service getCountriesAsJSON()");
		ArrayList<DropDownData> dropDownList = new ArrayList<DropDownData>();
		ArrayList<Country> countryList = (ArrayList<Country>) refService
				.getAllCountries();
		if (countryList != null && countryList.size() > 0) {
			for (Country c : countryList) {
				dropDownList.add(new DropDownData(c.getId(), c.getName()));
			}
		}
		return dropDownList;
	}

}