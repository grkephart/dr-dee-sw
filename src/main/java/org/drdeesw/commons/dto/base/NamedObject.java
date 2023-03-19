package org.drdeesw.commons.dto.base;

/**
 * @author gary_kephart
 *
 */
public interface NamedObject extends DataTransferObject
{
  /**
   * @return
   */
  public String getName();

  /**
   * Sets the name of the object.
   * 
   * @param name the name to set
   */
  <T extends NamedObject> T setName(
    String name);
}
