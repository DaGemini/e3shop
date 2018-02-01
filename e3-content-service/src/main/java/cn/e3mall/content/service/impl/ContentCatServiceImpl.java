package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.content.service.ContentCatService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    public List<TreeNode> getContentCategoryList(Long parentId) {
        //1、取查询参数id，parentId
        //2、根据parentId查询tb_content_category。查询子节点列表。
        TbContentCategoryExample example = new TbContentCategoryExample();
        //设置查询条件
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        //3、得到List<TbContentCategory>
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        //4、把列表转换成List<TreeNode>
        List<TreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            TreeNode node = new TreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            //添加到列表
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {
        //1、接收两个参数：parentId、name
        //2、向TbContentCategory表中插入数据
        //a)创建一个TbContentCategory对象
        TbContentCategory tbContentCategory = new TbContentCategory();
        //b)补全TbContentCategory对象的属性
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围：大于零的整数
        tbContentCategory.setSortOrder(1);
        //状态。可选值：1（正常），2（删除）
        tbContentCategory.setStatus(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        //c)向tb_content_category表中插入数据
        contentCategoryMapper.insert(tbContentCategory);
        //3、判断父节点的isparent是否为true，不是true需要改为true。
        TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);

        if(!parentNode.getIsParent()) {
            parentNode.setIsParent(true);

            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentNode);
        }
        //4、需要主键返回。
        //5、返回E3Result，其中包装TbContentCategory对象
        return E3Result.ok(tbContentCategory);
    }
}