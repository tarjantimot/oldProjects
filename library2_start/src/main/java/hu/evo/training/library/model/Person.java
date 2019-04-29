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
public class Person implements DbTable{
    
    /**
     * TODO create a POJO that represents PERSON
    **/

  public static final String TABLE_NAME = "PERSON";
  public static final String FIELD_ID = "ID";
  public static final String FIELD_ADRESS = "ADRESS";
  public static final String FIELD_NAME = "NAME";
  public static final String ATTR = "Pers.attr";
  
  private int id;
  private String adress;
  private String name;

  public Person() {
  }
  
  

  public Person(int id, String adress, String name) {
    this.id = id;
    this.adress = adress;
    this.name = name;
  }
  
  

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAdress() {
    return adress;
  }

  public void setAdress(String adress) {
    this.adress = adress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", adress=" + adress + ", name=" + name + '}';
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + this.id;
    hash = 97 * hash + Objects.hashCode(this.adress);
    hash = 97 * hash + Objects.hashCode(this.name);
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
    final Person other = (Person) obj;
    if (this.id != other.id) {
      return false;
    }
    if (!Objects.equals(this.adress, other.adress)) {
      return false;
    }
    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    return true;
  }
  
  
  
    @Override
    public String getTableName() {
      return Person.TABLE_NAME;
    }
    
}
