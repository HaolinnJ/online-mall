package com.github.haolinnj.onlinemall.dto;

import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class UmsMenuNode extends UmsMenu {
    @Schema (description = "childen menu")
    private List<UmsMenuNode> children;

    public List<UmsMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsMenuNode> children) {
        this.children = children;
    }
}
