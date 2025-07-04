package com.milanmiljkovic.bookservice.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class BookController {

    private final WebClient webClient;

    private BookController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://openlibrary.org/search.json").build();
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello book";
    }

    @GetMapping("all-books")
    public Mono<String> allBooks() {
        Mono<String> nesto = this.webClient.get().uri("?q=mistborn").retrieve().bodyToMono(String.class);
//        System.out.println(nesto);
        return nesto;
    }


}
