package com.vinhonotas.pairing.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pairings")
@RequiredArgsConstructor
public class PairingController {

    private final OpenAiChatClient openAiChatClient;

    @GetMapping("/informations")
    public String getWineInformation(@RequestParam(value = "wine") String wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Quais as características do vinho {wine}.");
        promptTemplate.add("wine", wine);
        return openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    @GetMapping("/pairings")
    public String getWinePairing(@RequestParam(value = "wine") String wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Quais os pratos que combinam com o vinho {wine}?");
        promptTemplate.add("wine", wine);
        return openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    @GetMapping("/menu")
    public String getMenuPairing(@RequestParam(value = "wine") String wine) {
        PromptTemplate promptTemplate = new PromptTemplate("Crie um menu com entrada, prato principal e sobremesa que harmonize com o vinho {wine}?");
        promptTemplate.add("wine", wine);
        return openAiChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

}
