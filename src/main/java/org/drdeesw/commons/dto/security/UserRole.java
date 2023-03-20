/**
 * 
 */
package org.drdeesw.commons.dto.security;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.base.AbstractLongUniqueObject;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gkephart
 *
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class UserRole<U extends User<?>, R extends Role<?>> extends AbstractLongUniqueObject
{
  private static final long serialVersionUID = 1L;
  @ManyToOne
  @JoinColumn(name = "group_id")
  private R                 role;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private U                 user;
  @Column(name = "username")
  private String            username;

  /**
   * Hibernate
   */
  public UserRole()
  {
  }


  /**
   * @return the role
   */
  public R getRole()
  {
    return role;
  }


  /**
   * @return the user
   */
  public U getUser()
  {
    return user;
  }


  /**
   * @return the username
   */
  public String getUsername()
  {
    return username;
  }


  /**
   * @param role the role to set
   */
  public void setRole(
    R role)
  {
    this.role = role;
  }


  /**
   * @param user the user to set
   */
  public void setUser(
    U user)
  {
    this.user = user;
  }


  /**
   * @param username the username to set
   */
  public void setUsername(
    String username)
  {
    this.username = username;
  }

}
