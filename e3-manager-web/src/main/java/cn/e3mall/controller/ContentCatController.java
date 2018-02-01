package cn.e3mall.controller;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.content.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<TreeNode> list = contentCatService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public E3Result createCategory(Long parentId, String name) {
        E3Result result = contentCatService.addContentCategory(parentId, name);
        return result;
    }
}
