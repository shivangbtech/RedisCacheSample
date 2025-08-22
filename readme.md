# RedisCacheSample

A Spring Boot project demonstrating how to integrate **Redis** as a caching layer using the Spring Cache abstraction.

---

##  Features

- Spring Boot REST API with CRUD operations (e.g., `Product`)
- Cache integration using `@Cacheable`, `@CachePut`, `@CacheEvict`
- JSON serialization for cache values via Jackson
- Docker Compose for spinning up Redis quickly
- H2 in-memory database for simplified development

---

##  Prerequisites

- Java 8 or higher
- Maven
- Docker & Docker Compose (optional but recommended)

---

##  Setup & Usage

```bash
git clone https://github.com/shivangbtech/RedisCacheSample.git
cd RedisCacheSample

# Start Redis via Docker
mvn clean install
docker-compose up -d
```


- App will run on http://localhost:8080
- Redis caching enabled at localhost:6379

## Configuration Details

- Cache TTL configurable via application.yml (e.g., cache.ttl-seconds: 300)
- JSON-based cache serialization using GenericJackson2JsonRedisSerializer in RedisConfig
- JPA's ddl-auto: update setting ensures schema creation (and optionally runs data.sql)

## Troubleshooting

- Redis Connection Errors: Ensure Redis is running. Use docker ps to verify.
- Table Not Found on Startup: Enable spring.jpa.hibernate.ddl-auto=update or add schema.sql.
- Cache Not Working: Ensure methods are public and not self-invoked within the service (required for Spring AOP).

## Future Improvements

- Add validation using @Valid and spring-boot-starter-validation
- Implement per-cache TTLs or eviction strategies
- Dockerize the Spring Boot app itself for a full docker-compose stack
-Add monitoring tools like RedisInsight or actuator metrics