package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.mbg.model.PmsBrand;

import java.util.List;

public interface IPmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
