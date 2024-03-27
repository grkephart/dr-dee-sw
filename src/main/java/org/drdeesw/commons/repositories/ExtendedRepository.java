/**
 * 
 */
package org.drdeesw.commons.repositories;

import java.io.Serializable;

import org.drdeesw.commons.dto.base.UniqueObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author gary_kephart
 *
 */
public interface ExtendedRepository<T extends UniqueObject<ID>, ID extends Serializable> extends JpaRepository<T, ID>, QueryRepository<T, ID>
{

}
