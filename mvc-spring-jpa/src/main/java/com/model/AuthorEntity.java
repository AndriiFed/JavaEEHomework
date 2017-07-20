package com.model;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
public class AuthorEntity {
  @Id
  @Column(name = "ID")
  private int id;

  @Basic
  @Column(name = "NAME")
  private String name;

  public AuthorEntity() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AuthorEntity that = (AuthorEntity) o;

    if (id != that.id) return false;
    return name != null ? name.equals(that.name) : that.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
