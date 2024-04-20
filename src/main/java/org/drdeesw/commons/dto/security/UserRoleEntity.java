/**
 * 
 */
package org.drdeesw.commons.dto.security;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.entities.AbstractLongUniqueEntity;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gkephart
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@Access(AccessType.FIELD)
public class UserRoleEntity extends AbstractLongUniqueEntity implements UserRole
{
  @Column(name = "group_id")
  private Long   roleId;
  @Column(name = "user_id")
  private Long   userId;
  @Column(name = "username")
  private String username;

  /**
   * Hibernate
   */
  public UserRoleEntity()
  {
  }


  /**
   * @return the role
   */
  public Long getRoleId()
  {
    return roleId;
  }


  /**
   * @return the user
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
   * @param roroleIdle the role to set
   */
  public void setRoleId(Long roleId)
  {
    this.roleId = roleId;
  }


  /**
   * @param userId the userId to set
   */
  public void setUserId(Long userId)
  {
    this.userId = userId;
  }


  /**
   * @param username the username to set
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

}
