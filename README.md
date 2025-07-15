# Calculator Kafka Integration

This project demonstrates a Spring Boot Calculator REST API with Kafka integration for streaming calculation results and aggregating numbers.

## Features

- **REST API**: Calculator endpoints for add, subtract, and multiply operations
- **Kafka Producer**: Streams calculation results to `calculator-results-0` topic
- **Kafka Consumer**: Aggregates all numbers (operands + results) from the topic
- **Aggregation API**: Endpoints to view and reset aggregation totals
- **Docker Integration**: Complete Kafka setup with Docker Compose

## Architecture

1. **Calculator Service**: Performs calculations and sends results to Kafka
2. **Kafka Producer**: Publishes calculation results to `calculator-results-0` topic
3. **Kafka Consumer**: Consumes messages and aggregates all numbers
4. **Aggregation Service**: Maintains running totals and operation counts

## Prerequisites

- **Java 17** or higher
- **Docker** and **Docker Compose** (for Kafka)
- **Gradle** (wrapper included)

## Quick Start

### 1. Start Kafka with Docker

**Option A: Using Docker Compose (Recommended)**
```bash
# Start Kafka infrastructure
docker compose up -d

# Verify containers are running
docker compose ps
```

**Option B: Manual Docker Setup**
```bash
# Start Zookeeper
docker run -d --name zookeeper -p 2181:2181 confluentinc/cp-zookeeper:7.4.0 \
  -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000

# Start Kafka
docker run -d --name kafka -p 9092:9092 --link zookeeper confluentinc/cp-kafka:7.4.0 \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1
```

This starts:
- **Zookeeper** (port 2181)
- **Kafka** (port 9092)
- **Kafka UI** (port 8090) - Access at http://localhost:8090

### 2. Build and Run the Application

```bash
# Build the project
./gradlew.bat build

# Run the application
./gradlew.bat bootRun
```

The application will start on **http://localhost:8080**

### 3. Test the Integration

**Option A: PowerShell Script (Windows)**
```powershell
./test-kafka-integration.ps1
```

**Option B: Bash Script (Linux/Mac)**
```bash
./test-kafka-integration.sh
```

**Option C: Manual Testing**
```bash
# Test calculator operations
curl -X POST http://localhost:8080/api/calculator/add \
  -H "Content-Type: application/json" \
  -d '{"a": 10, "b": 5}'

# Check aggregation results
curl -X GET http://localhost:8080/api/aggregation/total
```

## API Endpoints

### Calculator Operations
- `POST /api/calculator/add` - Add two numbers
- `POST /api/calculator/subtract` - Subtract two numbers  
- `POST /api/calculator/multiply` - Multiply two numbers

**Request Format:**
```json
{
  "a": 10,
  "b": 5
}
```

**Response Format:**
```json
{
  "result": 15
}
```

### Aggregation
- `GET /api/aggregation/total` - Get current aggregation totals
- `POST /api/aggregation/reset` - Reset aggregation counters

**Aggregation Response:**
```json
{
  "totalSum": 265,
  "operationCount": 5
}
```

## How Aggregation Works

For each calculation operation, the consumer aggregates:
**Operand A + Operand B + Result**

### Example Calculation:
1. **ADD(10, 5) = 15** → Aggregates: 10 + 5 + 15 = **30**
2. **SUBTRACT(15, 3) = 12** → Aggregates: 15 + 3 + 12 = **30**
3. **MULTIPLY(4, 6) = 24** → Aggregates: 4 + 6 + 24 = **34**
4. **ADD(20, 30) = 50** → Aggregates: 20 + 30 + 50 = **100**
5. **MULTIPLY(7, 8) = 56** → Aggregates: 7 + 8 + 56 = **71**

**Total Aggregated Sum:** 30 + 30 + 34 + 100 + 71 = **265**

## Kafka Message Format

Messages sent to `calculator-results-0` topic:
```json
{
  "operation": "ADD",
  "operandA": 10,
  "operandB": 5,
  "result": 15,
  "timestamp": "2025-07-14T23:30:00"
}
```

## Monitoring & Debugging

### Application Logs
Monitor the console output for:
- Kafka message sending confirmations
- Consumer message processing
- Aggregation updates

### Kafka UI
Access **http://localhost:8090** to:
- View topics and messages
- Monitor consumer groups
- Check message throughput

### Docker Logs
```bash
# View Kafka logs
docker logs kafka

# View Zookeeper logs
docker logs zookeeper
```

## Troubleshooting

### Common Issues

1. **Kafka Connection Failed**
   - Ensure Docker containers are running: `docker compose ps`
   - Check port 9092 is not blocked by firewall

2. **Application Won't Start**
   - Verify Java 17 is installed: `java -version`
   - Check if port 8080 is available

3. **No Messages in Kafka**
   - Check application logs for producer errors
   - Verify Kafka topic exists in Kafka UI

4. **Consumer Not Processing**
   - Check consumer group status in Kafka UI
   - Verify JSON deserialization is working

### Reset Everything
```bash
# Stop and remove containers
docker compose down

# Remove volumes (optional - clears all data)
docker compose down -v

# Restart
docker compose up -d
```

## Technology Stack

- **Kotlin** with Spring Boot 3.2.0
- **Spring Kafka** for messaging
- **Apache Kafka** with Zookeeper
- **Docker Compose** for infrastructure
- **Gradle** for build management
- **Jackson** for JSON serialization

## Development

### Running Tests
```bash
./gradlew.bat test
```

### Building JAR
```bash
./gradlew.bat bootJar
```

The JAR will be created in `build/libs/calculator-0.0.1-SNAPSHOT.jar`