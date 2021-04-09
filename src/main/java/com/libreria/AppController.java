package com.libreria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	private LibrosRepository librosRepo;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/registro_form")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Usuarios());
		
		return "process_register";
	}
	
	@PostMapping("/process_register")
	public String processRegister(Usuarios user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userRepo.save(user);
		
		return "registro_exitoso ";
	}
	
	@GetMapping("/libros")
	public String listUsers(Model model) {
		List<Libros> listLibros= librosRepo.findAll();
		model.addAttribute("listLibros", listLibros);
		
		return "libros";
	}
}
