package com.rossi21.familyPlanner.Controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.rossi21.familyPlanner.Models.ForecastItem;
import com.rossi21.familyPlanner.Models.LoginUser;
import com.rossi21.familyPlanner.Models.User;
import com.rossi21.familyPlanner.Services.UserService;

@Controller
public class UserController {
	
	@Autowired
    private UserService userServ;
	
	
	
	// Login or Register Page
	@GetMapping("/")
    public String index(Model model) {
		// Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
	}
	
	// Registration Method
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        // call a register method in the service 
    	userServ.register(newUser, result);
        // to do some extra validations and create a new user!
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            
            return "index.jsp";
        }
        
        // No errors! 
        // Store their ID from the DB in session, 
        // in other words, log them in.
        User createdUser = userServ.create(newUser);
        session.setAttribute("userId", createdUser.getId());
        
        return "redirect:/thefamilyplanner";
    }
	
    //Login Method
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
		BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if (user == null) {
        	model.addAttribute("newUser", new User());
        	return "index.jsp";
        }
        
        session.setAttribute("userId", user.getId());
        
        return "redirect:/thefamilyplanner";
    }
    
    // Home Page
    @GetMapping("/thefamilyplanner")
    public String home(Model model, HttpSession session) throws UnirestException {
    	if (session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	
    	Long userId = (Long)session.getAttribute("userId");
    	model.addAttribute("user", userServ.getOneById(userId));
    	
    	String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=bbb860ed73ae412e828142615231005&q=seattle&aqi=no&days=3";
    	HttpResponse <JsonNode> jsonResponse = Unirest.get(apiUrl).asJson();
    	JsonNode jsonNodeBody = jsonResponse.getBody();
    	JSONObject jsonObj = jsonNodeBody.getObject();    	           
    	
    	
    	JSONArray forecastArray = jsonObj.getJSONObject("forecast").getJSONArray("forecastday");

        List<ForecastItem> forecastList = new ArrayList<>();
        for (int i = 0; i < forecastArray.length(); i++) {
            JSONObject forecastObj = forecastArray.getJSONObject(i);
            ForecastItem forecastItem = new ForecastItem();
            forecastItem.setDate(forecastObj.getString("date"));

            ForecastItem.Day day = new ForecastItem.Day();
            day.setMaxtemp_f(forecastObj.getJSONObject("day").getDouble("maxtemp_f"));
            day.setMintemp_f(forecastObj.getJSONObject("day").getDouble("mintemp_f"));

            
            
            ForecastItem.Condition condition = new ForecastItem.Condition();
            condition.setText(forecastObj.getJSONObject("day").getJSONObject("condition").getString("text"));
            condition.setIcon(forecastObj.getJSONObject("day").getJSONObject("condition").getString("icon"));
            
            day.setCondition(condition);
            forecastItem.setDay(day);

            forecastList.add(forecastItem);
        }

        model.addAttribute("forecast", forecastList);
    	
    	return "dashboard.jsp";
    }
    
    @GetMapping("/weather")
	public String weather(Model model, HttpSession session) throws UnirestException{
    	
    	String apiUrl = "https://api.weatherapi.com/v1/forecast.json?key=bbb860ed73ae412e828142615231005&q=seattle&aqi=no&days=3";
    	HttpResponse <JsonNode> jsonResponse = Unirest.get(apiUrl).asJson();
    	JsonNode jsonNodeBody = jsonResponse.getBody();
    	JSONObject jsonObj = jsonNodeBody.getObject();    	           
    	
    	
    	JSONArray forecastArray = jsonObj.getJSONObject("forecast").getJSONArray("forecastday");

        List<ForecastItem> forecastList = new ArrayList<>();
        for (int i = 0; i < forecastArray.length(); i++) {
            JSONObject forecastObj = forecastArray.getJSONObject(i);
            ForecastItem forecastItem = new ForecastItem();
            forecastItem.setDate(forecastObj.getString("date"));

            ForecastItem.Day day = new ForecastItem.Day();
            day.setMaxtemp_f(forecastObj.getJSONObject("day").getDouble("maxtemp_f"));
            day.setMintemp_f(forecastObj.getJSONObject("day").getDouble("mintemp_f"));

            
            
            ForecastItem.Condition condition = new ForecastItem.Condition();
            condition.setText(forecastObj.getJSONObject("day").getJSONObject("condition").getString("text"));
            condition.setIcon(forecastObj.getJSONObject("day").getJSONObject("condition").getString("icon"));
            
            day.setCondition(condition);
            forecastItem.setDay(day);

            forecastList.add(forecastItem);
        }

        model.addAttribute("forecast", forecastList);
    	return "weather.jsp";
    }


    	
    // Logout Method
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("userId");
    	
    	return "redirect:/";
    }
    
}
