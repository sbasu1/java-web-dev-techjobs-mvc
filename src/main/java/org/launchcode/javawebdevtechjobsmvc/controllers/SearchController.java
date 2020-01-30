package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.javawebdevtechjobsmvc.models.JobData.findByColumnAndValue;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @RequestMapping(value = "results", method = {RequestMethod.GET, RequestMethod.POST})
   // @ResponseBody
    public String displaySearchResults(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        System.out.println(searchTerm);
        System.out.println(searchType);
        System.out.println(JobData.findByColumnAndValue(searchType, searchTerm));
        model.addAttribute("columns", columnChoices);

        model.addAttribute("searchResult", JobData.findByColumnAndValue(searchType, searchTerm));

        return "search";
    };

}
