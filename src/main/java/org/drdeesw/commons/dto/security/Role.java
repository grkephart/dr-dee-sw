package org.drdeesw.commons.dto.security;


import org.drdeesw.commons.dto.base.NamedUniqueObject;


/**
 * Structured to work with JdbcUserDetailsManager.
 * 
 * @author gary_kephart
 *
 */
public interface Role extends NamedUniqueObject<Long>
{
  /* (non-Javadoc)
   * @see org.drdeesw.reactrax.dto.general.AbstractRenameableObject#getName()
   */
  @Override
  public String getName();


}
