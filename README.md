# Interstellar Study 01

투자 상품 조회 및 투자 신청 기능을 제공하는 Spring Boot 기반 백엔드 서비스입니다.  
분산 락(Redisson)을 활용해 동시 투자 요청 시 데이터 정합성을 보장합니다.

---

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 21 (Virtual Threads 활성화) |
| Framework | Spring Boot 3.3.4 |
| ORM | Spring Data JPA (Hibernate) |
| Database | PostgreSQL 15 |
| Cache / 분산 락 | Redis + Redisson 3.37.0 |
| DTO Mapping | MapStruct 1.5.5 |
| Build Tool | Gradle |
| Test | JUnit 5, MockMvc |

---

## 프로젝트 구조

```
src/main/java/com/interstellar/interstellarstudy01/
├── common/
│   └── redis/
│       └── RedissonLockFacade.java       # 분산 락 유틸리티
├── product/
│   ├── constant/ProductStatus.java       # OPEN / SOLD_OUT
│   ├── controller/ProductController.java
│   ├── domain/ProductEntity.java
│   ├── mapper/ProductMapper.java
│   ├── repository/ProductRepository.java
│   └── service/ProductServiceImpl.java
├── investment/
│   ├── controller/InvestmentController.java
│   ├── domain/InvestmentEntity.java
│   ├── mapper/InvestmentMapper.java
│   ├── repository/InvestmentRepository.java
│   └── service/InvestmentServiceImpl.java
└── InterstellarStudy01Application.java
```

---

## API 명세

### 상품 목록 조회

```
GET /api/v1/products
```

**Query Parameters**

| 파라미터 | 타입 | 제약 | 설명 |
|---------|------|------|------|
| page | Integer | >= 0 | 페이지 번호 |
| size | Integer | >= 1 | 페이지 크기 |

**Response**

```json
{
  "products": [
    {
      "productId": 1,
      "title": "상품명",
      "totalInvestingAmount": 10000000,
      "currentInvestingAmount": 3000000,
      "investCount": 5,
      "status": "OPEN",
      "startedAt": "2024-01-01T00:00:00",
      "finishedAt": "2024-12-31T23:59:59"
    }
  ]
}
```

---

### 투자 신청

```
POST /api/v1/investments
X-USER-ID: {userId}
```

**Request Body**

```json
{
  "productId": 1,
  "investingAmount": 500000
}
```

**Response** `201 Created`

```json
{
  "investmentId": 10,
  "productId": 1,
  "investingAmount": 500000,
  "investedAt": "2024-06-01T10:00:00"
}
```

---

### 나의 투자 목록 조회

```
GET /api/v1/investments/my
X-USER-ID: {userId}
```

**Response**

```json
{
  "investments": [
    {
      "investmentId": 10,
      "productId": 1,
      "title": "상품명",
      "totalInvestingAmount": 10000000,
      "investingAmount": 500000,
      "investedAt": "2024-06-01T10:00:00"
    }
  ]
}
```

---

## 실행 방법

### 사전 요구 사항

- Java 21
- Docker & Docker Compose

### 인프라 실행

```bash
docker-compose up -d
```

PostgreSQL(5432)과 Redis(6379)가 컨테이너로 실행됩니다.

### 애플리케이션 실행

```bash
./gradlew bootRun
```

### 테스트 실행

```bash
./gradlew test
```

---

## 주요 설계 결정

### 분산 락 (Redisson)

동시에 여러 투자 요청이 들어올 경우 목표 금액 초과 및 투자자 수 오차 문제가 발생할 수 있습니다.  
이를 방지하기 위해 DB 비관적 락 대신 Redis 기반 Redisson 분산 락을 적용했습니다.

- 락 키: `PRODUCT_LOCK:{productId}`
- 대기 시간: 5초 / 점유 시간: 3초

### JPA Dirty Checking

투자 신청 시 `ProductEntity`의 `currentInvestingAmount`, `investCount` 업데이트에  
별도 `save()` 호출 없이 JPA 더티 체킹을 활용합니다.

### 판매 완료 처리

투자 후 `currentInvestingAmount >= totalInvestingAmount` 조건을 확인해  
자동으로 상품 상태를 `SOLD_OUT`으로 전환합니다.

---

## 환경 설정

`src/main/resources/application.properties`

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=admin
spring.datasource.password=1234

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# JPA
spring.jpa.hibernate.ddl-auto=update

# Virtual Threads
spring.threads.virtual.enabled=true
```
