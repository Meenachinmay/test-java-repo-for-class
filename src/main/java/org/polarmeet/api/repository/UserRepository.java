package org.polarmeet.api.repository;

import org.polarmeet.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA will provide the implementations for
    // the basic DB queries.
    // here you can write your own complex DB queries// here you can write your own complex DB queries// here you can write your own complex DB queries
    // this method is to get a user from the db with email, means with this method we can
    // if a user is present in the db or now.
    Optional<User> findByEmail(String email);
}

// What is database? --> a collection of various data about something.
// What is database query? --> is a way to find something in that collection
// You just query a database to find a specific data or range of data or multiple data
// querying a database is based on a special database query language which we call SQL.
// SQL -> Simplified Query Language to query a database.
// When you ask a question to a database, you need create a query. and then send it to Db.
// Database is nothing but a server running a program which can store / save collections
// more efficiently than human. a well organized data collections in a way that
// saving and reading data from it is efficient and scalable.

// Of course you can save data in normal text files or excel files but storing, reading, deleting
// etc. is not easy and scalable.

// for almost every programming language, we can write sql queries. but they way can be different.
// Sql language does not change, but the way you introduce it the programming language changes all the time.