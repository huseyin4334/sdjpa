# Create Project
- git init
- gh repo create sdjpa --source=.
- git add .
- git commit -m "....."
- git push --set-upstream origin master

# Persistence Context
An EntityManager instance is associated with a persistence context. 
A persistence context is a set of entity instances in which for any persistent entity identity there is a unique entity instance. 
Within the persistence context, the entity instances and their lifecycle are managed. 
The EntityManager API is used to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.

The above statement may seem a bit complex right now, but it will make complete sense as we proceed. 
The persistence context is the first-level cache where all the entities are fetched from the database or saved to the database.

>> More: https://www.baeldung.com/jpa-hibernate-persistence-context#:~:text=A%20persistence%20context%20is%20a,and%20their%20lifecycle%20are%20managed.

# Cascade
- Cascade is hibernate feature.
- We are saying with this feature that is what we want to do for mapped objects
- For example, I can say with persist cascade type, if i save main object, save mapped object too.

## JPA Cascade Types
- PERSIST
  - if i save main object, save mapped object too.
- REMOVE
  - If I remove main object. Remove mapped objects too.
  - if we not give this type, hibernate give ConstraintViolationException
- orphanRemoval
  - If i take out any object from
- MERGE
  - if I update (merge function in jpa) main entity, Jpa will update child entities that have this cascade type.
- REFRESH
  - if i refresh(reload) main object, jpa will refresh child object too.
- DETACH
  - If i detach main entity (that mean if i detach an entity, this function is deleting this entity from spring context), jpa detach child object too.

## Hibernate Cascade Types
- LOCK
- REPLICATE
- SAVE_UPDATE
