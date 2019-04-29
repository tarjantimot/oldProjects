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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pappmico
 */
public class DataManager implements SqlConstants {

  DBUtil util = new DBUtil();

  public List<Book> getBooksFromDatabase() {
    List<Book> bookList = new ArrayList<>();
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_GET_BOOKS);
      ResultSet set = ps.getResultSet();
      while (set.next()) {
        bookList.add(new Book(set.getInt(Book.FIELD_ID), set.getString(Book.FIELD_ISBN),
                set.getString(Book.FIELD_AUTHOR),
                set.getInt(Book.FIELD_YEAR), set.getString(Book.FIELD_TITLE)));
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return bookList;
  }

  public List<Person> getPeopleFromDatabase() {
    List<Person> personList = new ArrayList<>();
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_GET_PERSON);
      ResultSet set = ps.getResultSet();
      while (set.next()) {
        personList.add(new Person(set.getInt(Person.FIELD_ID),
                set.getString(Person.FIELD_ADRESS), set.getString(Person.FIELD_NAME)));
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return personList;
  }

  public List<BookInstance> getBookInstanceFromDatabase(int bookId) {
    List<BookInstance> bookInstanceList = new ArrayList<>();
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_GET_BOOK_INSTANCE + " WHERE B." + BookInstance.FIELD_BOOK_ID + " = " + bookId + ";");
      ResultSet set = ps.getResultSet();
      while (set.next()) {
        bookInstanceList.add(new BookInstance(set.getInt(BookInstance.FIELD_ID),
                set.getInt(BookInstance.FIELD_BOOK_ID), set.getInt(BookInstance.FIELD_SERIAL_NO), set.getInt(BookInstance.FIELD_LOCKED)));
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return bookInstanceList;
  }

  public List<Loanings> getLoaningsFromDatabase() {
    List<Loanings> loaningsList = new ArrayList<>();
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_GET_LOANINGS);
      ResultSet set = ps.getResultSet();
      while (set.next()) {
        loaningsList.add(new Loanings(set.getInt(Loanings.ID), set.getString(Loanings.LOANING_DATE),
                set.getString(Loanings.RETURN_DATE), set.getInt(Loanings.BOOK_INSTANCE_ID), set.getInt(Loanings.PERSON_ID)));
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return loaningsList;
  }

  public void lockBookInstance(int id, int value) {
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_LOCK_OPEN_BOOKS_FIRST_PART + value + SQL_LOCK_OPEN_BOOKS_FIRST_PART + id);
      ps.execute();
    } catch (Exception e) {
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void addMember(String address, String name) {
  Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_INSERT_NEW_MEMBER+"'"+address+"' '"+name+"')");
      ps.execute();
    } catch (Exception e) {
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

  }

  public void updateMember(String name, String address, int id) {
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_MEMBER+"ADDRESS = '"+address+"' NAME = '"+name+"' "
                                                        + "WHERE ID = "+id);
      ps.execute();
    } catch (Exception e) {
    }finally{
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public void rentABook(int personId, int instanceId) {
    Connection connection = null;
    Date CurretntDate = new Date(System.currentTimeMillis());
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_INSERT_NEW_LOANING+personId + " , "+instanceId+" , " + CurretntDate.toString());
      ps.execute();
    } catch (Exception e) {
    }finally{
      try {
        connection.close();
      } catch (SQLException ex) {
      }
    }
  }

  public void returnLoaning(int id) {
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_LOANING+new Date(System.currentTimeMillis()).toString()+"WHERE ID = "+id);
      ps.execute();
    } catch (Exception e) {
    }finally{
      try {
        connection.close();
      } catch (SQLException ex) {
      }
    }
  }

  public List<BookInstance> getAvaiableBooks() {
    List<BookInstance> avaiableBooks = new ArrayList<>();
    Connection connection = null;
    try {
      connection = util.getConnection();
      PreparedStatement ps = connection.prepareStatement(SQL_GET_BOOK_INSTANCE + " WHERE B." + BookInstance.FIELD_LOCKED + " = " + 0 + ";");
      ResultSet set = ps.getResultSet();
      while (set.next()) {
        avaiableBooks.add(new BookInstance(set.getInt(BookInstance.FIELD_ID),
                set.getInt(BookInstance.FIELD_BOOK_ID), set.getInt(BookInstance.FIELD_SERIAL_NO), set.getInt(BookInstance.FIELD_LOCKED)));
      }
    } catch (SQLException ex) {
      Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        connection.close();
      } catch (SQLException ex) {
        Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return avaiableBooks;    
  }

  /**
   * TODO Create functions to get data from db or persist use DBUtil
   */
}
