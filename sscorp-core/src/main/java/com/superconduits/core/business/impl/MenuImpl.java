/*
 * This is the business layer interface and acts as a bridge between the backend
 * layer and the front end application. It sends call to the backend layer and
 * and receives back data in raw format. One major responsiblity of this layer
 * is to transform the data received in the final format that is required.
 */
package com.superconduits.core.business.impl;

import com.superconduits.core.business.interfaces.IMenu;
import com.superconduits.core.dbaccess.MenuDAO;
import com.superconduits.core.to.menu.AdditionalMenuUrlTO;
import com.superconduits.core.to.menu.MenuTO;
import com.superconduits.core.to.menu.SubmenuTO;
import com.superconduits.core.util.SSCorpUtil;
import com.superconduits.core.util.persistence.PersistenceUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bhaskar
 * @since 25th Aug 2010
 * @changeLog 17th Oct 2010
 * @version 1.0, 1.1
 */
public class MenuImpl implements IMenu {

    Integer curMenuId = -1;
    Integer curSubmenuId = -1;
    Integer curAddMenuUrlId = -1;
    MenuTO menuTO = null;
    List<SubmenuTO> submenus = null;
    List<AdditionalMenuUrlTO> urlTOs = null;

    @Override
    public List<MenuTO> fetchMenus(Integer companyId) throws Exception {
        EntityManager em = null;
        em = PersistenceUtil.getEntityManagerFactory().createEntityManager();

        //TODO - Catch a custom Exception - ObjectCreationException
        MenuDAO menuDAO = (MenuDAO) SSCorpUtil.getObject("menuDAO");

        try {
            List<Object[]> menuList = menuDAO.fetch(em, companyId);

            if (menuList != null && menuList.size() > 0) {
                return populateMenuObject(menuList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    /**
     * Populate the final DTO objects for displaying menus that will be sent
     * back to the front end.
     * @param menuList
     * @return
     */
    private List<MenuTO> populateMenuObject(List<Object[]> menuList) {
        
        List<MenuTO> menus = new ArrayList<MenuTO>();

        for (Object[] objArr : menuList) {
            if (curMenuId == -1 || curMenuId !=
                                    Integer.parseInt(objArr[0].toString())) {
                /*This if condition is true when the current menu id being 
                iterated is not equal to the curMenuId variable*/

                //set the curMenuId to the current menu id being iterated.
                curMenuId = Integer.parseInt(objArr[0].toString());

                //create a new instance for the submenu list to be added to the
                //menu object.
                submenus = new ArrayList<SubmenuTO>();

                //create a new instance for the additional menu url list to be
                //added to the menu object.
                urlTOs = new ArrayList<AdditionalMenuUrlTO>();

                //create a new instance of the menu object
                menuTO = (MenuTO) SSCorpUtil.getObject(MenuTO.class);
                //populate the menu object
                setMenus(menuTO, objArr);

                //populate the list with the final menu objects
                menus.add(menuTO);
            }

            processSubmenus(objArr);
            processAddtionalMenuUrls(objArr);
        }

        return menus;
    }

    /**
     * Populate the submenu DTO objects for a given menu object and add this
     * list of submenus to the menu object.
     * @param objArr a single row of all lmenu related information.
     */
    private void processSubmenus(Object[] objArr) {
        if (objArr[4] != null && objArr[5] != null) {
            if(curSubmenuId == -1 || curSubmenuId !=
                                    Integer.parseInt(objArr[3].toString())){

                //set the curSubmenuId to the current submenu id being iterated.
                curSubmenuId = Integer.parseInt(objArr[3].toString());

                //get a new instance of the submenu object
                SubmenuTO submenuTO = (SubmenuTO) SSCorpUtil
                                            .getObject(SubmenuTO.class);

                //populate the submenu object
                setSubmenus(submenuTO, objArr);

                /*add the submenu object to a list for adding the list to a
                /menu object*/
                submenus.add(submenuTO);

                //add the submenu list to the menu object
                menuTO.setSubmenus(submenus);
            }
        }
    }

    /**
     * Populate the additional menu url DTO objects for a given menu object and
     * add this list of additional menu urls to the menu object.
     * @param objArr a single row of all lmenu related information.
     */
    private void processAddtionalMenuUrls(Object[] objArr){
        if (objArr[6] != null && objArr[7] != null) {
            if(curAddMenuUrlId == -1 || curAddMenuUrlId !=
                                        Integer.parseInt(objArr[6].toString())){

                //set the curAddMenuUrlId to the current additional menu url id
                //being iterated.
                curAddMenuUrlId = Integer.parseInt(objArr[6].toString());

                AdditionalMenuUrlTO urlTO = (AdditionalMenuUrlTO)SSCorpUtil
                                        .getObject(AdditionalMenuUrlTO.class);

                //populate the additionalMenuUrl object
                setAdditionalMenuUrls(urlTO, objArr);

                /*add the submenu object to a list for adding the list to a
                /menu object*/
                urlTOs.add(urlTO);

                //add the submenu list to the menu object
                menuTO.setAdditionalMenuUrlTOs(urlTOs);
            }
        }
    }

    /**
     * Helper function to assist in populating the menus in the DTO objects.
     * @param menuTO
     * @param objArr
     */
    private void setMenus(MenuTO menuTO, Object[] objArr) {
        menuTO.setMenuId(Integer.parseInt(objArr[0].toString()));
        menuTO.setMenuName(objArr[1].toString());
        menuTO.setMenuUrl("/" + objArr[2].toString().trim() + ".html");
        menuTO.setMenuImage(objArr[9].toString());
    }

    /**
     * Helper function to assist in populating the submenus in the DTO objects.
     * @param submenuTO
     * @param objArr
     */
    private void setSubmenus(SubmenuTO submenuTO, Object[] objArr) {
        submenuTO.setSubmenuId(Integer.parseInt(objArr[3].toString()));
        submenuTO.setSubmenuName(objArr[4].toString());
        submenuTO.setSubmenuUrl("/" + objArr[5].toString().trim() + ".html");
    }

    /**
     * Helper function to assist in populating the additional menu urls in the
     * DTO objects.
     * @param urlTO
     * @param objArrr
     */
    private void setAdditionalMenuUrls(AdditionalMenuUrlTO urlTO,
                                        Object[] objArr){
        urlTO.setUrlId(Integer.parseInt(objArr[6].toString()));
        urlTO.setUrl("/" + objArr[7].toString() + ".html");
        urlTO.setUrlName(objArr[8].toString());
        urlTO.setSubmenuId(objArr[3] != null ? 
                            Integer.parseInt(objArr[3].toString()) : null);
    }
}
