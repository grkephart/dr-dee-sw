package org.drdeesw.commons.dto.security;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.drdeesw.commons.dto.entities.AbstractNamedUniqueEntity;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@Access(AccessType.FIELD)
@AttributeOverride(name = "name", column = @Column(name = "group_name"))
public class RoleEntity extends AbstractNamedUniqueEntity<Long> implements Role
{

  /* (non-Javadoc)
   * @see org.drdeesw.reactrax.dto.general.AbstractRenameableObject#getName()
   */
  @Override
  public String getName()
  {
    return super.getName();
  }

}
