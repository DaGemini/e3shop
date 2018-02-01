package cn.e3mall.content.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;

import java.util.List;

public interface ContentCatService {
    List<TreeNode> getContentCategoryList(Long parentId);

    E3Result addContentCategory(long parentId, String name);
}
