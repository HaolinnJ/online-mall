package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.mbg.model.UmsResourceCategory;

import java.util.List;

public interface IUmsResourceCategoryService {

    /**
     * get all resource category
     */
    List<UmsResourceCategory> listAll();

    /**
     * create new resource category
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * edit resource category
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * delete resource category
     */
    int delete(Long id);
}
