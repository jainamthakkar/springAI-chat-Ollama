package ai.springAI;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

	private static final String PROMPT ="Tell me ten java interview question with answer";
	
	private final OllamaChatModel chatModel;
	
	public AIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }
	
	@GetMapping("/generate")
	public Flux<ChatResponse> promptResponse(@RequestParam("prompt") String prompt) {
		
		System.out.println("inside prompt");
		
		Prompt promptOb = new Prompt(prompt);
		
		Flux<ChatResponse> response = chatModel.stream(promptOb);
		
//		String res = chatModel.call(prompt);
		
		return response;
	}

}

