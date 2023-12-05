package com.portfolio.portfolio;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Controller
public class WelcomeController {

    @GetMapping("/form")
    public String home(Model model) {
        // Load HTML content from the file
        String htmlContent = loadFile("templates/index.html");

        // Load CSS content from the file
        String cssContent = loadFile("static/style.css");

        // Load Bootstrap content from the file
        String bootstrapContent = loadFile("static/bootstrap.css");

        // Add the HTML, CSS, and Bootstrap content to the model
        model.addAttribute("htmlContent", htmlContent);
        model.addAttribute("cssContent", cssContent);
        model.addAttribute("bootstrapContent", bootstrapContent);

        // Return the view name
        return "index";
    }

    private String loadFile(String filePath) {
        try {
            // Load the file as a resource
            Resource resource = new ClassPathResource(filePath);

            // Read the content of the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading file: " + filePath;
        }
    }
}
