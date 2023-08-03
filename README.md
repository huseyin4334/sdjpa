# Create Project
- git init
- gh repo create sdjpa --source=.
- git add .
- git commit -m "....."
- git push --set-upstream origin master


# Transaction
- A transaction is a collection of read/write operations.
- That can finish with commit or rollback.

>> More: https://vladmihalcea.com/a-beginners-guide-to-acid-and-database-transactions/  -  https://docs.actian.com/zen/v14/index.html#page/adonet%2Fisolation.htm%23ww509958

# ACID
- Atomicity, Consistency, isolation, durability

# Locking
- Optimistic (Version required)
  - Optimistic (Read)
  - Optimistic_INCREMENT (write)
- Pessimistic
>> More: https://www.baeldung.com/jpa-optimistic-locking

# Transactional Annotation
- transactionManager

## Propagation
- Setting up to transaction usage options
- Create new or use existing transaction


## Isolation
- Setting up to isolation level
- EOT (end of transaction)
- READ_UNCOMMITTED
  - Locks are obtained on modifications to the database and held until EOT. 
  - Reading from the database does not involve any locking.
- READ_COMMITTED
  - Locks are acquired for reading and modifying the database. 
  - Locks are released after reading (so, other transaction can read or update this rows), but locks on modified objects are held until EOT.
- REPEATABLE_READ
  - Locks are obtained for reading and modifying the database. Locks on all modified objects are held until EOT. 
  - Locks obtained for reading data are held until EOT. 
  - Locks on non-modified access structures (such as indexes and hashing structures) are released after reading.
  - So any transactions can't read or update locked rows.
- SERIALIZABLE
  - A lock is placed on the affected rows of the DataSet until EOT. All access structures that are modified, and those used by the query, are locked until EOT.
  - So, other transactions can't any insert, read or update in this table.