./mvnw spring-boot:run    ##chạy dự án 
netstat -ano | findstr :8080   ## kiểm tra cổng chạy 
taskkill /PID 25736 /F      ###nếu bị chiếm cổng 
