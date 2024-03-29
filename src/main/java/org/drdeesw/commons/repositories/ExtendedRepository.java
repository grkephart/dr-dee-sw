/**
 * 
 */
package org.drdeesw.commons.repositories;

import java.io.Serializable;

import org.drdeesw.commons.dto.base.UniqueObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @deprecated Can't create an interface that extends JpaRepository and QueryRepository and then use that as a super-interface.
 * @author gary_kephart
 *
 */
@Deprecated
public interface ExtendedRepository<T extends UniqueObject<ID>, ID extends Serializable> extends JpaRepository<T, ID>, QueryRepository<T, ID>
{

}
