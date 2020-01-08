package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {
	HashMap<String, String> jobCOls = new HashMap<>();
	  public SearchController () {
			jobCOls.put("name", "Name");
	    	jobCOls.put("employer", "Employer");
	    	jobCOls.put("location", "Location");
	    	jobCOls.put("positionType", "Position Type");
	    	jobCOls.put("coreCompetency", "Skill");
	  }

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobCols", jobCOls);
    
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @RequestMapping(value = "results")
    public String listJobsByColumnAndValue(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
    	model.addAttribute("columns", columnChoices); 
    	model.addAttribute("jobCols", jobCOls);
        ArrayList<Job> jobs;
     
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        
        model.addAttribute("jobs", jobs);

        return "search";
    }

}
