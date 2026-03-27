package com.example.guestbook.controller;

import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookRepository guestbookRepository;

    // 1) 목록 페이지 - 전체 조회 (최신순)
    @GetMapping("/guestbook")
    public String list(Model model) {
        List<Guestbook> list = guestbookRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("list", list);
        return "list"; // templates/list.mustache
    }

    // 2) 작성 폼 페이지
    @GetMapping("/guestbook/write")
    public String writeForm() {
        return "write"; // templates/write.mustache
    }

    // 3) 저장 후 목록으로 redirect
    @PostMapping("/guestbook/write")
    public String write(@RequestParam String author,
                        @RequestParam String content) {
        Guestbook guestbook = Guestbook.builder()
                .author(author)
                .content(content)
                .build();
        guestbookRepository.save(guestbook);
        return "redirect:/guestbook";
    }

    // 4) 삭제 후 목록으로 redirect
    @PostMapping("/guestbook/{id}/delete")
    public String delete(@PathVariable Long id) {
        guestbookRepository.deleteById(id);
        return "redirect:/guestbook";
    }
}
