package com.github.haolinnj.onlinemall.service.impl;

import com.github.haolinnj.onlinemall.dto.UmsMenuNode;
import com.github.haolinnj.onlinemall.mbg.mapper.UmsMenuMapper;
import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.mbg.model.UmsMenuExample;
import com.github.haolinnj.onlinemall.service.IUmsMenuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsMenuServiceImpl implements IUmsMenuService {

    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu); //change menu level
        return menuMapper.insert(umsMenu);
    }

    /**
     * change menu level method
     */
    private void updateLevel(UmsMenu umsMenu){
        if(umsMenu.getParentId() == 0) {
            //has no father menu
            umsMenu.setLevel(0);
        } else {
            //has father menu ,then father menu level +1
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if(parentMenu != null){
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample example = new UmsMenuExample();
        example.setOrderByClause("sort desc");
        example.createCriteria().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<UmsMenuNode> treelist() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode (menu, menuList))
                .collect(Collectors.toList());
        return result;
    }

    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList){
        UmsMenuNode node = new UmsMenuNode();
        // copy the menu into this new node
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }
}
