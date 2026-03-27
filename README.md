# 방명록 (Guestbook)

Spring Boot + Mustache 기반 간단한 방명록 웹 애플리케이션

---

## 기술 스택

| 구분 | 기술 |
|---|---|
| Backend | Spring Boot 3.2, Spring Data JPA |
| Template | Mustache |
| Database | H2 (인메모리) |
| Build | Gradle |
| Java | 17 |

---

## 프로젝트 구조

```
src/main/
├── java/com/example/guestbook/
│   ├── GuestbookApplication.java       # 메인 클래스
│   ├── entity/
│   │   └── Guestbook.java              # Entity (id, author, content, createdAt)
│   ├── repository/
│   │   └── GuestbookRepository.java    # JpaRepository (최신순 정렬)
│   └── controller/
│       └── GuestbookController.java    # 4개 엔드포인트
└── resources/
    ├── application.properties
    └── templates/
        ├── list.mustache               # 목록 페이지
        └── write.mustache             # 작성 폼 페이지
```

---

## API 엔드포인트

| Method | URL | 설명 |
|---|---|---|
| GET | `/guestbook` | 목록 페이지 (최신순 정렬) |
| GET | `/guestbook/write` | 작성 폼 페이지 |
| POST | `/guestbook/write` | 글 저장 → 목록으로 redirect |
| POST | `/guestbook/{id}/delete` | 글 삭제 → 목록으로 redirect |

---

## 실행 방법

```bash
./gradlew bootRun
```

브라우저에서 접속: [http://localhost:8080/guestbook](http://localhost:8080/guestbook)

> H2 콘솔: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
> JDBC URL: `jdbc:h2:mem:guestbookdb`

---

## Entity

```java
@Entity
public class Guestbook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String content;
    private LocalDateTime createdAt;
}
```

---

## 주요 구현 사항

- **계층 구조**: Controller → Repository (단순 CRUD이므로 Service 생략)
- **최신순 정렬**: `findAllByOrderByCreatedAtDesc()` 쿼리 메서드 사용
- **더티 체킹 불필요**: 저장/삭제만 있어 `@Transactional` 직접 관리 없이 `save()`, `deleteById()` 사용
- **Mustache 템플릿**: `{{#list}}` 반복, `{{^list}}` 빈 상태 처리, 삭제는 POST 폼으로 구현
