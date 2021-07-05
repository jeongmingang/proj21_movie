package proj21_movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SalesController {
	
	@GetMapping("/salesManager")
	public String salesManaging() {
		return "manager/salesManaging";
	}
	
}
