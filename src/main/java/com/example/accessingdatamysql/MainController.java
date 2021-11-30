package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ResourceLoader resourceLoader;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser () {
		String name = "";
		String email = "doesnotmatter@com.com";

		Resource resource= resourceLoader.getResource("classpath:/largeFile.txt");

		InputStream inputStream= null;
		try {
			inputStream = resource.getInputStream();
			Assert.notNull(inputStream,"Could not load template resource!");
			byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
			name = new String(bdata, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
