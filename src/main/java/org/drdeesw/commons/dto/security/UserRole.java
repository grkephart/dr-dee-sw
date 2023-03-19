/**
 * 
 */
package org.drdeesw.commons.dto.security;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.drdeesw.commons.dto.base.AbstractLongUniqueObject;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gkephart
 *
 */
@Entity
@Table(name = "group_members")
public class UserRole extends AbstractLongUniqueObject
{
  private static final long serialVersionUID = 1L;
  private Role   role;
  private User   user;
  private String username;

  /**
   * Hibernate
   */
  public UserRole()
  {
  }



  /**
   * @return the role
   */
  @ManyToOne
  @JoinColumn(name = "group_id")
  public Role getRole()
  {
    return role;
  }


  /**
   * @return the user
   */
  @ManyToOne
  @JoinColumn(name = "user_id")
  public User getUser()
  {
    return user;
  }


  /**
   * @return the username
   */
  @Column(name = "username")
  public String getUsername()
  {
    return username;
  }


  /**
   * @param role the role to set
   */
  public void setRole(
    Role role)
  {
    this.role = role;
  }


  /**
   * @param user the user to set
   */
  public void setUser(
    User user)
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
