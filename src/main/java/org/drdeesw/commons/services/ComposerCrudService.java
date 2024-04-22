/**
 * 
 */
package org.drdeesw.commons.services;


import java.io.Serializable;

import org.drdeesw.commons.dto.pojos.UniquePojo;


/**
 * @author gary_kephart
 *
 */
public interface ComposerCrudService<P extends UniquePojo<ID>, ID extends Serializable>
    extends CrudService<P, ID>
{

}
