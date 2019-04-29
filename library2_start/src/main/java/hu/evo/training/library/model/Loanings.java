/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.training.library.model;

import java.util.Objects;

/**
 *
 * @author pappmico
 */
public class Loanings implements DbTable{

     /**
     * TODO create a POJO that represents LOANINGS
     **/
  
  public static final String TABLE_NAME = "LOANINGS";
  public static final String ID = "ID";
  public static final String LOANING_DATE = "LOANING_DATE";
  public static final String RETURN_DATE = "RETURN_DATE";
  public static final String BOOK_INSTANCE_ID = "BOOK_INSTANCE_ID";
  public static final String PERSON_ID = "PERSON_ID";
  public static final String ATTR = "loanings.attr";
    
  private int id;
  private String loaningDate;
  private String returnDate;
  private int bookInstanceId;
  private int presonId;

  public Loanings() {
  }

  public Loanings(int id, String loaningDate, String returnDate, int bookInstanceId, int presonId) {
    this.id = id;
    this.loaningDate = loaningDate;
    this.returnDate = returnDate;
    this.bookInstanceId = bookInstanceId;
    this.presonId = presonId;
  }

  
  
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLoaningDate() {
    return loaningDate;
  }

  public void setLoaningDate(String loaningDate) {
    this.loaningDate = loaningDate;
  }

  public String getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(String returnDate) {
    this.returnDate = returnDate;
  }

  public int getBookInstanceId() {
    return bookInstanceId;
  }

  public void setBookInstanceId(int bookInstanceId) {
    this.bookInstanceId = bookInstanceId;
  }

  public int getPresonId() {
    return presonId;
  }

  public void setPresonId(int presonId) {
    this.presonId = presonId;
  }

  @Override
  public String toString() {
    return "Loanings{" + "id=" + id + ", loaningDate=" + loaningDate + ", returnDate=" + returnDate + ", bookInstanceId=" + bookInstanceId + ", presonId=" + presonId + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + this.id;
    hash = 97 * hash + Objects.hashCode(this.loaningDate);
    hash = 97 * hash + Objects.hashCode(this.returnDate);
    hash = 97 * hash + this.bookInstanceId;
    hash = 97 * hash + this.presonId;
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
    final Loanings other = (Loanings) obj;
    if (this.id != other.id) {
      return false;
    }
    if (this.bookInstanceId != other.bookInstanceId) {
      return false;
    }
    if (this.presonId != other.presonId) {
      return false;
    }
    if (!Objects.equals(this.loaningDate, other.loaningDate)) {
      return false;
    }
    if (!Objects.equals(this.returnDate, other.returnDate)) {
      return false;
    }
    return true;
  }
  
  
  
    @Override
    public String getTableName() {
      return Loanings.TABLE_NAME;
    }
    
}
