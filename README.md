# Create Project
- git init
- gh repo create sdjpa --source=.
- git add .
- git commit -m "....."
- git push --set-upstream origin master

# Repository Interfaces
- Repository
- CrudRepository (extends Repository)
- PagingAndSortingRepository (extends CrudRepository)
- JpaRepository (extends PagingAndSortingRepository and more)

## Annotations
- NoRepositoryBean
  - Not create a repository bean for this interface
- RepositoryDefinition
  - Custom Repository Definition with this annotations

# Hibernate Data Types
- https://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html/ch03.html

# DDL
- DDL is data definition language
- In the context of SQL, data definition or data description language (DDL) is a syntax for creating and modifying database objects such as tables, indices, and users. DDL statements are similar to a computer programming language for defining data structures, especially database schemas. Common examples of DDL statements include CREATE, ALTER, and DROP.
- hbm2ddl.auto
- https://en.wikipedia.org/wiki/Data_definition_language

