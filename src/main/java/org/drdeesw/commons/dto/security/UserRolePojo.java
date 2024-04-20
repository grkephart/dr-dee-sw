/**
 * 
 */
package org.drdeesw.commons.dto.security;


import org.drdeesw.commons.dto.pojos.AbstractLongUniquePojo;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gkephart
 *
 */
@SuppressWarnings("serial")
public class UserRolePojo extends AbstractLongUniquePojo implements UserRole
{
  private Long   roleId;
  private Long   userId;
  private String username;

  /**
   * Hibernate
   */
  public UserRolePojo()
  {
  }


  /**
   * @return the roleId
   */
  public Long getRoleId()
  {
    return roleId;
  }


  /**
   * @return the userId
   */
  public Long getUserId()
  {
    return userId;
  }


  /**
   * @return the username
   */
  public String getUsername()
  {
    return username;
  }


  /**
   * @param roleId the roleId to set
   */
  public void setRoleId(
    Long role)
  {
    this.roleId = role;
  }


  /**
   * @param userId the userId to set
   */
  public void setUserId(
    Long user)
  {
    this.userId = user;
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
