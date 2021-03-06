package cn.e3mall.content.service.impl;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;

    public E3Result addContent(TbContent content) {
        //补全属性
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入数据
        contentMapper.insert(content);
        return E3Result.ok();
    }
}
