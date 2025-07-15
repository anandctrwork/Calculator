# PowerShell script to test Calculator Kafka Integration

Write-Host "=== Calculator Kafka Integration Test ===" -ForegroundColor Green
Write-Host ""

# Base URL for the calculator API
$BaseUrl = "http://localhost:8080/api"

Write-Host "1. Testing Calculator Operations (which will send to Kafka)..." -ForegroundColor Yellow
Write-Host ""

# Test addition
Write-Host "Testing Addition: 10 + 5" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/calculator/add" -Method POST -ContentType "application/json" -Body '{"a": 10, "b": 5}'
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test subtraction
Write-Host "Testing Subtraction: 15 - 3" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/calculator/subtract" -Method POST -ContentType "application/json" -Body '{"a": 15, "b": 3}'
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test multiplication
Write-Host "Testing Multiplication: 4 * 6" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/calculator/multiply" -Method POST -ContentType "application/json" -Body '{"a": 4, "b": 6}'
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Wait for Kafka processing
Write-Host "Waiting 3 seconds for Kafka message processing..." -ForegroundColor Yellow
Start-Sleep -Seconds 3

Write-Host "2. Checking Aggregation Results..." -ForegroundColor Yellow
Write-Host ""

# Get aggregation totals
Write-Host "Current Aggregation Totals:" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/aggregation/total" -Method GET
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "3. Performing more operations..." -ForegroundColor Yellow
Write-Host ""

# More operations
Write-Host "Testing Addition: 20 + 30" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/calculator/add" -Method POST -ContentType "application/json" -Body '{"a": 20, "b": 30}'
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "Testing Multiplication: 7 * 8" -ForegroundColor Cyan
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/calculator/multiply" -Method POST -ContentType "application/json" -Body '{"a": 7, "b": 8}'
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Wait for processing
Write-Host "Waiting 3 seconds for Kafka message processing..." -ForegroundColor Yellow
Start-Sleep -Seconds 3

Write-Host "4. Final Aggregation Results:" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$BaseUrl/aggregation/total" -Method GET
    Write-Host "Response: $($response | ConvertTo-Json)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "=== Test Complete ===" -ForegroundColor Green
Write-Host ""
Write-Host "Expected aggregation calculation:" -ForegroundColor Yellow
Write-Host "Operation 1: ADD(10,5)=15 -> Sum: 10+5+15 = 30" -ForegroundColor White
Write-Host "Operation 2: SUBTRACT(15,3)=12 -> Sum: 15+3+12 = 30" -ForegroundColor White
Write-Host "Operation 3: MULTIPLY(4,6)=24 -> Sum: 4+6+24 = 34" -ForegroundColor White
Write-Host "Operation 4: ADD(20,30)=50 -> Sum: 20+30+50 = 100" -ForegroundColor White
Write-Host "Operation 5: MULTIPLY(7,8)=56 -> Sum: 7+8+56 = 71" -ForegroundColor White
Write-Host "Total expected sum: 30+30+34+100+71 = 265" -ForegroundColor Cyan