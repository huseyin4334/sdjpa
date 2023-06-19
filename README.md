# Create Project
- git init
- gh repo create sdjpa --source=.
- git add .
- git commit -m "....."
- git push --set-upstream origin master

# Dao/Repository Pattern
- Purpose is to isolate persistence operations from the application layer
- **Domain Class:** Simple POJO's, same as JPA entities
- **Dao Api:** Provide interface for CRUD operations (similar to Repository)
- **Dao Implementation:** Implement Persistence Functionality

# Statement Vs PreparedStatement
- Statement is for just one usage.
- PreparedStatement can use many of time. And it has caching mechanism.
- Prepared statement have parameter binding.
- Parameter binding better cause sql injection attacks.