/*
 * This is the main interface which exposes the functionality of providing
 * menu driven services in the Super Sales Corporation. It gives the menus
 * fetched from the service layer and returns it to the calling function.
 */

package com.superconduits.core.business.interfaces;

import com.superconduits.core.to.menu.MenuTO;
import java.util.List;

/**
 * @author bhaskar
 * @Created on 17th Sep 2010
 * @Version : 1.0
 * @ChangeLog :
 */
public interface IMenu {

    public List<MenuTO> fetchMenus(Integer companyId) throws Exception;
}