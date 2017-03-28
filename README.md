# Capstone_WebApi
This Api is a maven project with Java. You will need Java JDK and Apache Maven to get started.

This is a working prototype that will eventually have a simple bank api that allows users to sign up and have bank accounts to make transactions

This api is built using Spring MVC and uses Cassandra as the database. 

Inorder to get this working you will need to setup a Cassandra keyspace called "cassandrabankapp" as follows:

create keyspace cassandrabankapp WITH replication = {'class':'NetworkTopologyStrategy', 'datacenter1' : 3}; within the cqlsh to create the keyspace.

Two tables have been made so far but may change based on querying, to make those tables in the cqlsh you enter:

create table member (username text primary key, fullname text, email text, password text, accountNumber text);

create table account (accountNumber text primary key, balance double);

That should get you set up and run the Spring boot project with Eclipe or Spring Tool Suite.
