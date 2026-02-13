package com.isaacferrairo.tema4gradle;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
    public static void  main(String[] args){

        var iaPregunta = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("dummy")
                .modelName("gemma:2b")
                .build();

        var iaRespuesta = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("dummy")
                .modelName("llama3.1:8b")
                .build();

        // TEMAS QUE QUIERO QUE MI IA HABLE
        String[] temas = {
                "Rocas curiosas de españa",
                "Ordenadores y precios",

        };

        String tema = temas[new Random().nextInt(temas.length)];
        System.out.println("Tema del debate: " + tema);

        List<ChatMessage> historialPregunta = new ArrayList<>();
        historialPregunta.add(new SystemMessage(
                "Eres una IA curiosa que hace preguntas inteligentes sobre " + tema + "."
        ));
        historialPregunta.add(new UserMessage("Haz una pregunta sobre este tema."));

        AiMessage pregunta = iaPregunta.chat(historialPregunta).aiMessage();
        System.out.println("\nPregunta de la IA 1:");
        System.out.println(pregunta.text());

        List<ChatMessage> historialRespuesta = new ArrayList<>();
        historialRespuesta.add(new SystemMessage(
                "Eres una IA experta en " + tema + " y debes responder con claridad y argumentos."
        ));
        historialRespuesta.add(new UserMessage(pregunta.text()));

        AiMessage respuesta = iaRespuesta.chat(historialRespuesta).aiMessage();
        System.out.println("\nRespuesta de la IA 2:");
        System.out.println(respuesta.text());

    }
}






