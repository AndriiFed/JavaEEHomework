package com.model;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
public class BookEntity {
  @Id
  @Column(name = "ID")
  private int id;

  @Basic
  @Column(name = "NAME")
  private String name;

  @Basic
  @Column(name = "YEAR")
  private String year;

  @Basic
  @Column(name = "RATING")
  private int rating;

  public BookEntity() {
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

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BookEntity that = (BookEntity) o;

    if (id != that.id) return false;
    if (rating != that.rating) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    return year != null ? year.equals(that.year) : that.year == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (year != null ? year.hashCode() : 0);
    result = 31 * result + rating;
    return result;
  }
}
