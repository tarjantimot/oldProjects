/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.training.library.model;

/**
 *
 * @author pappmico
 */
public class BookInstance implements DbTable{
    
    /**
     * TODO create a POJO that represents BOOK_INSTANCE
    **/
  public static final String TABLE_NAME = "BOOK_INSTANCE";
  public static final String FIELD_ID = "ID";
  public static final String FIELD_BOOK_ID = "BOOK_ID";
  public static final String FIELD_SERIAL_NO = "SERIAL_NO";
  public static final String FIELD_LOCKED = "LOCKED";
  public static final String ATTR = "instance.attr";
  
  
  private int id;
  private int bookId;
  private int serialNo;
  private int locked;

  public BookInstance() {
  }
  
  public BookInstance(int id, int bookId, int serialNo, int locked) {
    this.id = id;
    this.bookId = bookId;
    this.serialNo = serialNo;
    this.locked = locked;
  }
  
  public int getId() {
    return id;
  }

  public int getBookId() {
    return bookId;
  }

  public int getSerialNo() {
    return serialNo;
  }

  public int getLocked() {
    return locked;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public void setSerialNo(int serialNo) {
    this.serialNo = serialNo;
  }

  public void setLocked(int locked) {
    this.locked = locked;
  }

  @Override
  public String toString() {
    return "BookInstance{" + "id=" + id + ", bookId=" + bookId + ", serialNo=" + serialNo + ", locked=" + locked + '}';
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 59 * hash + this.id;
    hash = 59 * hash + this.bookId;
    hash = 59 * hash + this.serialNo;
    hash = 59 * hash + this.locked;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final BookInstance other = (BookInstance) obj;
    if (this.id != other.id) {
      return false;
    }
    if (this.bookId != other.bookId) {
      return false;
    }
    if (this.serialNo != other.serialNo) {
      return false;
    }
    if (this.locked != other.locked) {
      return false;
    }
    return true;
  }
  
  

    @Override
    public String getTableName() {
      return BookInstance.TABLE_NAME;
    }
    
}
