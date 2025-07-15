#!/bin/bash

echo "=== Calculator Kafka Integration Test ==="
echo

# Base URL for the calculator API
BASE_URL="http://localhost:8080/api"

echo "1. Testing Calculator Operations (which will send to Kafka)..."
echo

# Test addition
echo "Testing Addition: 10 + 5"
curl -X POST "$BASE_URL/calculator/add" \
  -H "Content-Type: application/json" \
  -d '{"a": 10, "b": 5}' \
  -w "\nStatus: %{http_code}\n\n"

# Test subtraction
echo "Testing Subtraction: 15 - 3"
curl -X POST "$BASE_URL/calculator/subtract" \
  -H "Content-Type: application/json" \
  -d '{"a": 15, "b": 3}' \
  -w "\nStatus: %{http_code}\n\n"

# Test multiplication
echo "Testing Multiplication: 4 * 6"
curl -X POST "$BASE_URL/calculator/multiply" \
  -H "Content-Type: application/json" \
  -d '{"a": 4, "b": 6}' \
  -w "\nStatus: %{http_code}\n\n"

# Wait a moment for Kafka processing
echo "Waiting 3 seconds for Kafka message processing..."
sleep 3

echo "2. Checking Aggregation Results..."
echo

# Get aggregation totals
echo "Current Aggregation Totals:"
curl -X GET "$BASE_URL/aggregation/total" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "3. Performing more operations..."
echo

# More operations
echo "Testing Addition: 20 + 30"
curl -X POST "$BASE_URL/calculator/add" \
  -H "Content-Type: application/json" \
  -d '{"a": 20, "b": 30}' \
  -w "\nStatus: %{http_code}\n\n"

echo "Testing Multiplication: 7 * 8"
curl -X POST "$BASE_URL/calculator/multiply" \
  -H "Content-Type: application/json" \
  -d '{"a": 7, "b": 8}' \
  -w "\nStatus: %{http_code}\n\n"

# Wait for processing
echo "Waiting 3 seconds for Kafka message processing..."
sleep 3

echo "4. Final Aggregation Results:"
curl -X GET "$BASE_URL/aggregation/total" \
  -H "Content-Type: application/json" \
  -w "\nStatus: %{http_code}\n\n"

echo "=== Test Complete ==="
echo
echo "Expected aggregation calculation:"
echo "Operation 1: ADD(10,5)=15 -> Sum: 10+5+15 = 30"
echo "Operation 2: SUBTRACT(15,3)=12 -> Sum: 15+3+12 = 30"
echo "Operation 3: MULTIPLY(4,6)=24 -> Sum: 4+6+24 = 34"
echo "Operation 4: ADD(20,30)=50 -> Sum: 20+30+50 = 100"
echo "Operation 5: MULTIPLY(7,8)=56 -> Sum: 7+8+56 = 71"
echo "Total expected sum: 30+30+34+100+71 = 265"