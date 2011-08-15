/*
 * This class acts as an interface between the actual database implementation
 * and the application layer. It is used to communicate with the database and
 * pass on the data fetched to the business layer.
 */

package com.superconduits.core.dbaccess;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 17th Oct 2010
 * @version 1.0, 1.1
 */
public class MenuDAO {
    private static final String QUERY = " select " +
                                        " rm.menu_id, " +
                                        " rm.menu_name, " +
                                        " rm.menu_url, " +
                                        " rm.submenu_id, " +
                                        " rm.submenu_name, " +
                                        " rm.submenu_url, " +
                                        " adm.url_id, " +
                                        " adm.url, " +
                                        " adm.url_name, " +
                                        " rm.menu_image " +
                                        " from " +
                                        " (select m.menu_id, m.menu_name, " +
                                        " m.menu_url, m.company_id, " +
                                        " sm.submenu_id, " +
                                        " sm.submenu_name, " +
                                        " sm.submenu_url, " +
                                        " m.menu_image " +
                                        " from sscorp_menu_m m " +
                                        " left outer join " +
                                        " sscorp_submenu_m sm " +
                                        " on m.menu_id = sm.menu_id " +
                                        " order by m.menu_position, " +
                                        " sm.submenu_position " +
                                        " ) rm " +
                                        " left outer join " +
                                        " sscorp_additional_menu_url_m adm " +
                                        " on (rm.menu_id = adm.menu_id " +
                                        " ) " +
                                        " where " +
                                        " rm.company_id= :companyId ";

    /**
     * 
     * @param em
     * @param id
     * @return
     * @throws Exception
     */
    public List<Object[]> fetch(EntityManager em, Integer id) throws Exception{
        return (List<Object[]>)em.createNativeQuery(QUERY)
                .setParameter("companyId", id)
                .getResultList();
    }
}
