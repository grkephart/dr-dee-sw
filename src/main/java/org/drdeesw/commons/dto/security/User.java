/**
 * 
 */
package org.drdeesw.commons.dto.security;


import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.drdeesw.commons.dto.base.AbstractLongUniqueObject;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@Entity
@Table(schema = "reactrax", name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractLongUniqueObject
{
  private static final long serialVersionUID = 1L;
  private boolean           enabled;
  private String            name;
  private String            roleNames;
  private String            username;
  private Set<UserRole>     userRoles;

  /**
   * Hibernate
   */
  public User()
  {
  }


  public User(Long id)
  {
    super(id);
  }


  /**
   * For testing.
   * 
   * @param name
   * @param email
   */
  public User(String name, String username)
  {
    this.username = username;
    this.name     = name;
  }


  /**
   * For when logging in reveals a new user.
   * 
   * @param name
   * @param username
   * @param enabled
   * @param status   TODO
   */
  public User(String name, String username, boolean enabled)
  {
    this.enabled  = enabled;
    this.username = username;
    this.name     = name;
  }


  @Override
  @Column(name = "user_id")
  public Long getId()
  {
    return super.getId();
  }


  /**
   * @return the name
   */
  @Column(name = "name")
  public String getName()
  {
    return name;
  }


  /**
   * @return the roleNames
   */
  @Formula("(SELECT GROUP_CONCAT(gm.group_name) FROM group_members_v gm WHERE gm.user_id = user_id)")
  public String getRoleNames()
  {
    return roleNames;
  }


  /**
   * The email address is the username
   * 
   * @return
   */
  @Column(name = "username")
  public String getUsername()
  {
    return username;
  }


  /**
   * @return the userRoles
   */
  @JsonIgnore
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  public Set<UserRole> getUserRoles()
  {
    return userRoles;
  }


  /**
   * @return the enabled
   */
  @Column(name = "enabled")
  public boolean isEnabled()
  {
    return enabled;
  }


  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled)
  {
    this.enabled = enabled;
  }



  /**
   * @param name
   */
  public void setName(String name)
  {
    this.name = name;
  }


  /**
   * @param roleNames the roleNames to set
   */
  public void setRoleNames(String roleNames)
  {
    this.roleNames = roleNames;
  }


  public void setUsername(String email)
  {
    this.username = email;
  }


  /**
   * @param userRoles the userRoles to set
   */
  public void setUserRoles(Set<UserRole> userRoles)
  {
    this.userRoles = userRoles;
  }
}
