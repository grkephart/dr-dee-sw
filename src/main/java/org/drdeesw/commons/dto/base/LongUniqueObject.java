/**
 * 
 */
package org.drdeesw.commons.dto.base;

/**
 * A unique object that uses a Long as its unique identifier.
 * 
 * @author gary_kephart
 *
 */
public interface LongUniqueObject extends UniqueObject<Long>
{
  void setId(Long id);

}
