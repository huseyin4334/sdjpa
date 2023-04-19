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