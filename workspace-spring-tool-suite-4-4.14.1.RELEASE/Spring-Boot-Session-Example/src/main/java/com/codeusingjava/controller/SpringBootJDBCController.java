package com.codeusingjava.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringBootJDBCController {

	@GetMapping("/")
	public String home(Model model, HttpSession httpSession) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) httpSession.getAttribute("SPRING_BOOT_SESSION_MESSAGES");

		if (messages == null) {
			messages = new ArrayList<>();
		}
		model.addAttribute("sessionMessages", messages);

		return "index";
	}

	@PostMapping("/saveMessage")
	public String saveMessage(@RequestParam("msg") String message, HttpServletRequest httpServletRequest) {
		@SuppressWarnings("unchecked")
		List<String> messages = (List<String>) httpServletRequest.getSession().getAttribute("SPRING_BOOT_SESSION_MESSAGES");
		if (messages == null) {
			messages = new ArrayList<>();
			httpServletRequest.getSession().setAttribute("SPRING_BOOT_SESSION_MESSAGES", messages);
		}
		messages.add(message);
		httpServletRequest.getSession().setAttribute("SPRING_BOOT_SESSION_MESSAGES", messages);
		return "redirect:/";
	}

	@PostMapping("/delete")
	public String deleteSession(HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().invalidate();
		return "redirect:/";
	}
}