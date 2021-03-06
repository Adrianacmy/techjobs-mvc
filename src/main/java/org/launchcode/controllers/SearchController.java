package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController  extends TechJobsController{

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String search(@RequestParam String searchType, @RequestParam String searchTerm , Model model ){
        if (searchType.toLowerCase().equals("all")){
            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);
        }else{
            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "Jobs with " + ListController.columnChoices.get(searchType) + ": " + searchTerm);
            model.addAttribute("jobs", jobs);
            model.addAttribute("searchType", searchType);
            model.addAttribute("searchTerm", searchTerm);

        }
        return "search";
    }
}
