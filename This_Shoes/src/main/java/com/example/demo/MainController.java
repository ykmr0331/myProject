package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class MainController {

	@RequestMapping("/view.do")
	public ModelAndView dbPage(ModelAndView mav) {
		mav.setViewName("view");
		return mav;
	}
}