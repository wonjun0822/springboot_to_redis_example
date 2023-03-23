package org.example.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/session")
public class SesssionExampleController {
    private final HttpSession httpSession;

    @GetMapping
    public String getId() {
        return httpSession.getAttribute("id") == null ? "" : httpSession.getAttribute("id").toString();
    }

    @PostMapping
    public void Login(@RequestParam(required = true) String id) {
        httpSession.setAttribute("id", id);
    }

    @DeleteMapping
    public void Logout() {
        httpSession.removeAttribute("id");
    }
}
