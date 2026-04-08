$MvnCmd = "C:\Program Files\JetBrains\IntelliJ IDEA 2024.2\plugins\maven\lib\maven3\bin\mvn.cmd"

Write-Host "Starting Config Server..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Config Server'; cd config-server; & '$MvnCmd' spring-boot:run"

Write-Host "Waiting 20 seconds for Config Server to start..."
Start-Sleep -Seconds 20

Write-Host "Starting Eureka Server..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Eureka Server'; cd eureka-server; & '$MvnCmd' spring-boot:run"

Write-Host "Waiting 20 seconds for Eureka Server to start..."
Start-Sleep -Seconds 20

Write-Host "Starting API Gateway..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='API Gateway'; cd api-gateway; & '$MvnCmd' spring-boot:run"

Write-Host "Waiting 15 seconds for API Gateway to start..."
Start-Sleep -Seconds 15

Write-Host "Starting Domain Services..."
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Zone Service'; cd zone-service; & '$MvnCmd' spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Sensor Service'; cd sensor-service; & '$MvnCmd' spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Automation Service'; cd automation-service; & '$MvnCmd' spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "`$Host.UI.RawUI.WindowTitle='Crop Service'; cd crop-service; & '$MvnCmd' spring-boot:run"

Write-Host "All services started in separate windows! Check Eureka Dashboard at http://localhost:8761"
