/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.training.library.db;

import hu.evo.training.library.model.Book;
import hu.evo.training.library.model.BookInstance;
import hu.evo.training.library.model.Loanings;
import hu.evo.training.library.model.Person;

/**
 *
 * @author glevi74
 */
public interface SqlConstants {
  
  final String SQL_GET_BOOKS = "SELECT * FROM "+ Book.TABLE_NAME+" B";
  final String SQL_GET_PERSON = "SELECT * FROM "+ Person.TABLE_NAME;    
  final String SQL_GET_BOOK_INSTANCE = "SELECT * FROM "+BookInstance.TABLE_NAME;
  final String SQL_GET_LOANINGS = "SELECT * FROM "+Loanings.TABLE_NAME;
  final String SQL_LOCK_OPEN_BOOKS_FIRST_PART = "UPDATE BOOK_INSTANCE SET LOCKED =  ";
  final String SQL_LOCK_OPEN_BOOKS_SECOND_PART = " WHERE ID = ";
  final String SQL_INSERT_NEW_MEMBER = "INSERT INTO PERSON (ADDRESS, NAME) VALUES(";
  final String SQL_UPDATE_MEMBER = "UPDATE PERSON SET ";
  final String SQL_INSERT_NEW_LOANING = "INSERT INTO LOANINGS (PERSON_ID, BOOK_INSTANCE_ID, LOANING_DATE) VALUES(";
  final String SQL_UPDATE_LOANING = "UPDATE LOANINGS SET RETURN_DATE = ";
}
