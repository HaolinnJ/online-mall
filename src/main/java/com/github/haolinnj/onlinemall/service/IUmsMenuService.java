package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.dto.UmsMenuNode;
import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;

import java.util.List;

/**
 * Menu Management Service
 */
public interface IUmsMenuService {
    /**
     * create menu
     */
    int create(UmsMenu umsMenu);

    /**
     * edit menu
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * get menu by id
     */
    UmsMenu getItem(Long id);

    /**
     * delete menu by id
     */
    int delete(Long id);

    /**
     * query menu list
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * return menu list in tree list
     */
    List<UmsMenuNode> treelist();

    /**
     * change menu status
     */
    int updateHidden(Long id, Integer hidden);
}
