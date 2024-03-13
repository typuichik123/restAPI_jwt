package com.dmitrij.restapi_jwt.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
@RequiredArgsConstructor
@RestController
public class MainController {
    @GetMapping("/api/posts/**")
    public ResponseEntity<Void> getPost() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PostMapping("/api/posts/**")
    public ResponseEntity<Void> postPost() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PutMapping("/api/posts/**")
    public ResponseEntity<Void> putPost() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @DeleteMapping("/api/posts/**")
    public ResponseEntity<Void> deletePosts() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @GetMapping("/api/users/**")
    public ResponseEntity<Void> getUser() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PostMapping("/api/users/**")
    public ResponseEntity<Void> postUser() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PutMapping("/api/users/**")
    public ResponseEntity<Void> putUser() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @DeleteMapping("/api/users/**")
    public ResponseEntity<Void> deleteUser() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @GetMapping("/api/albums/**")
    public ResponseEntity<Void> getAlbum() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PostMapping("/api/albums/**")
    public ResponseEntity<Void> postAlbum() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @PutMapping("/api/albums/**")
    public ResponseEntity<Void> putAlbum() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }

    @DeleteMapping("/api/albums/**")
    public ResponseEntity<Void> deleteAlbum() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://jsonplaceholder.typicode.com/")).build();
    }


}
