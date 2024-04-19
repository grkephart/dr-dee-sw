/**
 * 
 */
package org.drdeesw.commons.dto.security;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.entities.AbstractLongUniqueEntity;
import org.hibernate.annotations.Formula;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@Access(AccessType.FIELD)
public class UserEntity extends AbstractLongUniqueEntity implements User
{
  @Column(name = "enabled")
  private boolean enabled;
  @Column(name = "name")
  private String  name;
  private String  roleNames;
  @Column(name = "username")
  private String  username;

  /**
   * Hibernate
   */
  public UserEntity()
  {
  }


  public UserEntity(Long id)
  {
    super(id);
  }


  /**
   * For testing.
   * 
   * @param name
   * @param email
   */
  public UserEntity(String name, String username)
  {
    this.username = username;
    this.name = name;
  }


  /**
   * For when logging in reveals a new user.
   * 
   * @param name
   * @param username
   * @param enabled
   */
  public UserEntity(String name, String username, boolean enabled)
  {
    this.enabled = enabled;
    this.username = username;
    this.name = name;
  }


  /**
   * @param that
   */
  public UserEntity(UserPojo that)
  {
    super(that);
    this.enabled = that.isEnabled();
    this.name = that.getName();
    this.roleNames = that.getRoleNames();
    this.username = that.getUsername();

  }


  @Override
  public Long getId()
  {
    return super.getId();
  }


  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }


  /**
   * @return the roleNames
   */
  @Access(AccessType.PROPERTY)
  @Formula("(SELECT GROUP_CONCAT(gm.group_name) FROM group_members_v gm WHERE gm.user_id = user_id)")
  public String getRoleNames()
  {
    return roleNames;
  }


  /**
   * The email address is the username
   * @return
   */
  public String getUsername()
  {
    return username;
  }


  /**
   * @return the enabled
   */
  public boolean isEnabled()
  {
    return enabled;
  }


  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(
    boolean enabled)
  {
    this.enabled = enabled;
  }


  /**
   * @param name the name to set
   */
  public void setName(
    String name)
  {
    this.name = name;
  }


  /**
   * @param roleNames the roleNames to set
   */
  public void setRoleNames(
    String roleNames)
  {
    this.roleNames = roleNames;
  }


  public void setUsername(
    String email)
  {
    this.username = email;
  }

}
