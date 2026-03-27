package com.example.guestbook.repository;

import com.example.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

    // 최신순 정렬 (createdAt 내림차순)
    List<Guestbook> findAllByOrderByCreatedAtDesc();
}
