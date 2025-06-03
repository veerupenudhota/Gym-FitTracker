package com.pvb.springboot.veeru.gym.controller;

import com.pvb.springboot.veeru.gym.model.AskPromptRequest;
import com.pvb.springboot.veeru.gym.model.ApiResponse;
import com.pvb.springboot.veeru.gym.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "http://localhost:3000")
public class FitnessAiController {

    private final OpenAIService openAIService;

    public FitnessAiController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }
    @GetMapping("/workout-details") 
    public ResponseEntity<ApiResponse> getAiWorkoutDetails(@RequestParam String workoutName) {
        String prompt = String.format(
            "Provide a comprehensive overview of the exercise '%s'. " +
            "Include its main description, key benefits, typical calories burned per 30 minutes (give a reasonable range), " +
            "and brief instructions on how to perform it correctly. " +
            "Format the output using basic HTML tags (like <p>, <strong>, <ul>, <li>, <br/>) for readability. " +
            "Highlight important terms and present the information clearly for a user interface. " +
            "For example: " +
            "<strong>Description:</strong> <p>...</p>" +
            "<strong>Benefits:</strong> <ul><li>...</li></ul>" +
            "<strong>Calories Burned:</strong> <p>...</p>" +
            "<strong>Instructions:</strong> <ol><li>...</li></ol>" +
            "Be concise and informative, suitable for a compact UI box.",
            workoutName
        );

        try {
            String aiResponseContent = openAIService.askGeneral(prompt, "user"); 
            return ResponseEntity.ok(new ApiResponse(aiResponseContent));
        } catch (Exception e) {
            System.err.println("Error fetching AI workout details for " + workoutName + ": " + e.getMessage());
            return ResponseEntity.status(500)
                                 .body(new ApiResponse("Failed to load workout details. Please try again."));
        }
    }
    
    @PostMapping("/ask-general") 
    public ResponseEntity<ApiResponse> askGeneralQuestion(@RequestBody AskPromptRequest request) {
        String userPrompt = request.getPrompt();
        if (userPrompt == null || userPrompt.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("Question cannot be empty."));
        }

        try {
            String aiAnswer = openAIService.askGeneral(userPrompt, "user");
            return ResponseEntity.ok(new ApiResponse(aiAnswer));
        } catch (Exception e) {
            System.err.println("Error with Ask Veeru AI chat: " + e.getMessage());
            return ResponseEntity.status(500)
                                 .body(new ApiResponse("Sorry, Veeru is having trouble answering right now. Please try asking again."));
        }
    }
}