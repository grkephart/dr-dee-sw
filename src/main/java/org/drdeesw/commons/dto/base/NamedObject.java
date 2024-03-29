package org.drdeesw.commons.dto.base;


/**
 * @author gary_kephart
 *
 */
public interface NamedObject extends DataTransferObject
{
  /**
   * Returns the name of the object.
   * 
   * @return the name of the object
   */
  public String getName();


  /**
   * Sets the name of the object.
   * 
   * @param name the name to set
   */
  <NO extends NamedObject> NO setName(
    String name);
}
