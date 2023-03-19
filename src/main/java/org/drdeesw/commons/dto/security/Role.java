package org.drdeesw.commons.dto.security;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.drdeesw.commons.dto.base.AbstractNamedObject;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@Entity
@Table(schema = "reactrax", name = "groups")
public class Role extends AbstractNamedObject<Long>
{
  private static final long serialVersionUID = 1L;
  private Set<UserRole>     userRoles;

  /* (non-Javadoc)
   * @see org.drdeesw.reactrax.dto.general.AbstractRenameableObject#getName()
   */
  @Override
  @Column(name = "group_name")
  public String getName()
  {
    return super.getName();
  }


  /**
   * @return the userRoles
   */
  @JsonIgnore
  @OneToMany(mappedBy = "role")
  public Set<UserRole> getUserRoles()
  {
    return userRoles;
  }


  /**
   * @param userRoles the userRoles to set
   */
  public void setUserRoles(
    Set<UserRole> userRoles)
  {
    this.userRoles = userRoles;
  }
}
